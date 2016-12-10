package cn.ziav.xfinal.module.seckill.facade;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cn.ziav.xfinal.common.exception.Result;
import cn.ziav.xfinal.module.seckill.manager.Seckill;
import cn.ziav.xfinal.module.seckill.model.Exposer;
import cn.ziav.xfinal.module.seckill.service.SeckillService;

@RestController
public class SeckillFacadeImpl implements SeckillFacade {

	@Autowired
	private SeckillService seckillService;

	@Override
	public Result<List<Seckill>> list() {
		// 获取列表页
		return Result.SUCCESS(seckillService.getSeckillList());
	}

	@Override
	public Result<Seckill> detail(@PathVariable Long seckillId) {
		if (seckillId == null) {
			return Result.ERROR(SeckillResult.SECKILL_ID_ILLEGAL);
		}
		return Result.SUCCESS(seckillService.getById(seckillId));
	}

	@Override
	public Result<Exposer> exposer(@PathVariable Long seckillId) {
		Exposer exposer = seckillService.exportSeckillUrl(seckillId);
		return Result.SUCCESS(exposer);
	}

	@Override
	public Result<Integer> execute(@PathVariable Long seckillId, @PathVariable String md5, @CookieValue(value = "killPhone", required = false) Long phone) {
		if (phone == null) {
			return Result.ERROR(SeckillResult.NOT_REGISTER);
		}
		seckillService.executeSeckill(seckillId, phone, md5);
		return Result.SUCCESS();
	}

	@Override
	public Result<Long> time() {
		Date now = new Date();
		return Result.SUCCESS(now.getTime());
	}

}
