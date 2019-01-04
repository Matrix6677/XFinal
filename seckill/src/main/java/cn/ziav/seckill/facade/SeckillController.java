package cn.ziav.seckill.facade;

import cn.ziav.common.exception.ManagedException;
import cn.ziav.common.exception.Result;
import cn.ziav.seckill.manager.Seckill;
import cn.ziav.seckill.model.Exposer;
import cn.ziav.seckill.model.SeckillExecution;
import cn.ziav.seckill.service.SeckillService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** @author Ziav */
@RestController
@RequestMapping("/seckill")
public class SeckillController {
  @Autowired private SeckillService seckillService;

  @GetMapping("list")
  public Result<List<Seckill>> list() {
    return seckillService.getSeckillList();
  }

  @GetMapping(value = "/{seckillId}/detail")
  public Result<Seckill> detail(@PathVariable("seckillId") Long seckillId) {
    if (seckillId == null) {
      throw new ManagedException(SeckillResult.NO_SECKILL_ID);
    }

    Seckill seckill = seckillService.getById(seckillId);
    if (seckill == null) {
      throw new ManagedException(SeckillResult.NO_SUCH_SECKILL);
    }

    return Result.SUCCESS(seckill);
  }

  // ajax ,json暴露秒杀接口的方法
  @GetMapping("/{seckillId}/exposer")
  public Result<Exposer> exposer(@PathVariable("seckillId") Long seckillId) {
    return Result.SUCCESS(seckillService.exportSeckillUrl(seckillId));
  }

  @GetMapping("/{seckillId}/{md5}/execution")
  public Result<SeckillExecution> execute(
      @PathVariable("seckillId") Long seckillId,
      @PathVariable("md5") String md5,
      @CookieValue(value = "userPhone", required = false) Long userPhone) {
    if (userPhone == null) {
      throw new ManagedException(SeckillResult.NO_USER_PHONE);
    }

    SeckillExecution execution = seckillService.executeSeckill(seckillId, userPhone, md5);
    return Result.SUCCESS(execution);
  }

  // 获取系统时间
  @GetMapping("/time/now")
  public Result<Long> time() {
    return Result.SUCCESS(System.currentTimeMillis());
  }
}
