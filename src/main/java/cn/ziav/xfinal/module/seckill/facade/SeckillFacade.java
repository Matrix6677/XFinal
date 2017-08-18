package cn.ziav.xfinal.module.seckill.facade;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.ziav.xfinal.common.exception.Result;
import cn.ziav.xfinal.module.Modules;
import cn.ziav.xfinal.module.seckill.manager.Seckill;
import cn.ziav.xfinal.module.seckill.model.Exposer;

@RequestMapping(Modules.SECKILL_MODULE)
public interface SeckillFacade {
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  Result<List<Seckill>> list();

  @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
  Result<Seckill> detail(Long seckillId);

  @RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.GET)
  Result<Exposer> exposer(Long seckillId);

  @RequestMapping(value = "/{seckillId}/{md5}/execution", method = RequestMethod.POST)
  Result<Integer> execute(Long seckillId, String md5, Long phone);

  @RequestMapping(value = "/time/now", method = RequestMethod.GET)
  Result<Long> time();
}
