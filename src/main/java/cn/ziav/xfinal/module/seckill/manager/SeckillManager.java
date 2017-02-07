package cn.ziav.xfinal.module.seckill.manager;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.ziav.xfinal.common.data.DataAccess;
import cn.ziav.xfinal.common.data.EntityService;
import cn.ziav.xfinal.common.exception.ManagedException;
import cn.ziav.xfinal.module.seckill.facade.SeckillResult;

@Component
public class SeckillManager {
	@DataAccess
	private EntityService<Long, Seckill> seckillEntityService;

	@Transactional
	public void reduceNum(long seckillId, Date killTime) {
		Seckill seckill = seckillEntityService.load(seckillId);
		if (seckill == null) {
			throw new ManagedException(SeckillResult.SECKILL_NOT_FOUNT);
		}
		if (killTime.after(seckill.getEndTime()) || killTime.before(seckill.getStartTime())) {
			throw new ManagedException(SeckillResult.OUT_OF_TIME);
		}
		seckill.reduce();
		seckillEntityService.update(seckill);
	}

	public Seckill queryById(long seckillId) {
		return seckillEntityService.load(seckillId);
	}

	public List<Seckill> queryAll(int offset, int limit) {
		return seckillEntityService.list(offset, limit);
	}
}
