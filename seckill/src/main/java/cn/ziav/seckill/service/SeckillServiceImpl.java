package cn.ziav.seckill.service;

import cn.ziav.common.exception.ManagedException;
import cn.ziav.common.exception.Result;
import cn.ziav.seckill.facade.SeckillResult;
import cn.ziav.seckill.manager.Seckill;
import cn.ziav.seckill.manager.SeckillManger;
import cn.ziav.seckill.manager.SuccessKilled;
import cn.ziav.seckill.model.Exposer;
import cn.ziav.seckill.model.SeckillExecution;
import cn.ziav.seckill.model.SeckillStatEnum;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

@Service
public class SeckillServiceImpl implements SeckillService {
  public static final String SALT = "o1EuFDOpgj#TdEUw";
  @Autowired private SeckillManger seckillManger;

  @Override
  public Result<List<Seckill>> getSeckillList() {
    return Result.SUCCESS(seckillManger.getSeckillList());
  }

  @Override
  public Seckill getById(Long seckillId) {
    return seckillManger.getSeckill(seckillId);
  }

  @Override
  public Exposer exportSeckillUrl(Long seckillId) {
    Seckill seckill = getById(seckillId);
    if (seckill == null) {
      throw new ManagedException(SeckillResult.NO_SUCH_SECKILL);
    }

    // 若是秒杀未开启
    Date startTime = seckill.getStartTime();
    Date endTime = seckill.getEndTime();
    // 系统当前时间
    Date nowTime = new Date();
    if (startTime.getTime() > nowTime.getTime() || endTime.getTime() < nowTime.getTime()) {
      return new Exposer(
          false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
    }

    // 秒杀开启，返回秒杀商品的id、用给接口加密的md5
    String md5 = getMD5(seckillId);
    return new Exposer(true, md5, seckillId);
  }

  private String getMD5(long seckillId) {
    String base = seckillId + "/" + SALT;
    return DigestUtils.md5DigestAsHex(base.getBytes());
  }

  /**
   * 秒杀是否成功，成功:减库存，增加明细；失败:抛出异常，事务回滚
   *
   * @param seckillId
   * @param userPhone
   * @param md5
   * @return
   */
  @Override
  @Transactional
  public SeckillExecution executeSeckill(Long seckillId, Long userPhone, String md5) {
    if (md5 == null || !md5.equals(getMD5(seckillId))) {
      // 秒杀数据被重写了
      throw new ManagedException(SeckillResult.MD5_INVALID);
    }
    // 执行秒杀逻辑:减库存+增加购买明细
    Date nowTime = new Date();

    // 否则更新了库存，秒杀成功,增加明细
    int insertCount = seckillManger.insertSuccessKilled(seckillId, userPhone);
    // 看是否该明细被重复插入，即用户是否重复秒杀
    if (insertCount <= 0) {
      throw new ManagedException(SeckillResult.REPEATED_SECKILL);
    } else {
      // 减库存,热点商品竞争
      int updateCount = seckillManger.reduceNumber(seckillId, nowTime);
      if (updateCount <= 0) {
        // 没有更新库存记录，说明秒杀结束 rollback
        throw new ManagedException(SeckillResult.SECKILL_IS_CLOSED);
      } else {
        // 秒杀成功,得到成功插入的明细记录,并返回成功秒杀的信息 commit
        SuccessKilled successKilled = seckillManger.queryByIdWithSeckill(seckillId, userPhone);
        return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, successKilled);
      }
    }
  }
}
