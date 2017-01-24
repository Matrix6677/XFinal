package cn.ziav.xfinal.module.seckill.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import cn.ziav.xfinal.common.exception.ManagedException;
import cn.ziav.xfinal.module.seckill.facade.SeckillResult;
import cn.ziav.xfinal.module.seckill.manager.Seckill;
import cn.ziav.xfinal.module.seckill.manager.SeckillManager;
import cn.ziav.xfinal.module.seckill.manager.SuccessKilled;
import cn.ziav.xfinal.module.seckill.manager.SuccessKilledManager;
import cn.ziav.xfinal.module.seckill.model.Exposer;

@Service
public class SeckillServiceImpl implements SeckillService {

	// md5盐值字符串，用于混淆MD5
	private final String slat = "~!@#$%^&*()";

	@Autowired
	private SeckillManager seckillManager;
	@Autowired
	private SuccessKilledManager successKilledManager;

	@Override
	public List<Seckill> getSeckillList() {
		return seckillManager.queryAll(0, 4);
	}

	@Override
	public Seckill getById(long seckillId) {
		return seckillManager.queryById(seckillId);
	}

	@Override
	public Exposer exportSeckillUrl(long seckillId) {
		Seckill seckill = seckillManager.queryById(seckillId);
		if (seckill == null) {
			return new Exposer(false, seckillId);
		}
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		// 系统当前时间
		Date nowTime = new Date();
		if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
			return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
		}
		// 转化特定字符串的过程，不可逆
		String md5 = getMD5(seckillId);
		return new Exposer(true, md5, seckillId);
	}

	private String getMD5(long seckillId) {
		String base = seckillId + "/" + slat;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

	@Override
	public void executeSeckill(long seckillId, long userPhone, String md5) {
		if (!StringUtils.hasText(md5) || !md5.equals(getMD5(seckillId))) {
			throw new ManagedException(SeckillResult.SECKILL_DATA_REWRITE);
		}
		// 执行秒杀逻辑：减库存 + 记录购买行为
		Date now = new Date();
		SuccessKilled successKilled = successKilledManager.queryByIdWithSeckill(seckillId, userPhone);
		// 唯一：seckillId,userPhone
		if (successKilled != null) {
			// 重复秒杀
			throw new ManagedException(SeckillResult.SECKILL_REPEATED);
		}
		// 记录购买行为
		successKilledManager.insertSuccessKilled(seckillId, userPhone);
		// 减库存，热点商品竞争
		seckillManager.reduceNum(seckillId, now);
	}

}
