package cn.ziav.xfinal.module.seckill.manager;

import org.springframework.stereotype.Repository;

import cn.ziav.xfinal.common.data.DataAccess;
import cn.ziav.xfinal.common.data.EntityService;
import cn.ziav.xfinal.module.seckill.manager.SuccessKilled.PK;

@Repository
public class SuccessKilledManager {
	@DataAccess
	private EntityService<PK, SuccessKilled> successKilledService;

	public SuccessKilled insertSuccessKilled(long seckillId, long userPhone) {
		return successKilledService.loadOrCreate(PK.valueOf(seckillId, userPhone), pk -> SuccessKilled.valueOf(pk));
	}

	public SuccessKilled queryByIdWithSeckill(long seckillId, long userPhone) {
		return successKilledService.load(PK.valueOf(seckillId, userPhone));
	}
}
