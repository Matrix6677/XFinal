package cn.ziav.xfinal.module.seckill.service;

import java.util.List;

import cn.ziav.xfinal.module.seckill.manager.Seckill;
import cn.ziav.xfinal.module.seckill.model.Exposer;

public interface SeckillService {
	List<Seckill> getSeckillList();

	Seckill getById(long seckillId);

	Exposer exportSeckillUrl(long seckillId);

	void executeSeckill(long seckillId, long userPhone, String md5);

}
