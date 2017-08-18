package cn.ziav.xfinal.module.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cn.ziav.xfinal.common.data.DataAccess;
import cn.ziav.xfinal.common.data.EntityService;
import cn.ziav.xfinal.common.exception.Result;
import cn.ziav.xfinal.module.seckill.manager.Seckill;
import cn.ziav.xfinal.module.seckill.manager.SeckillManager;

@RestController
public class HelloFacadeImpl implements HelloFacade {
  @DataAccess
  private EntityService<Long, Seckill> seckillService;
  @Autowired
  private SeckillManager seckillManager;

  @Override
  @Cacheable(cacheNames = "hello", key = "#param")
  public Result<String> hello(@PathVariable String param) {
    return Result.SUCCESS("hello: " + param);
  }

  @Override
  public Result<Seckill> getSeckill() {
    return Result.SUCCESS(seckillManager.testModel());
  }

}
