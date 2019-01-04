package cn.ziav.seckill.service;

import cn.ziav.common.exception.Result;
import cn.ziav.seckill.manager.Seckill;
import cn.ziav.seckill.model.Exposer;
import cn.ziav.seckill.model.SeckillExecution;
import java.util.List;

public interface SeckillService {

  Result<List<Seckill>> getSeckillList();

  Seckill getById(Long seckillId);

  Exposer exportSeckillUrl(Long seckillId);

  SeckillExecution executeSeckill(Long seckillId, Long userPhone, String md5);
}
