package cn.ziav.seckill.manager;

import java.util.Date;
import java.util.List;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SeckillManger {
  @Autowired private SeckillMapper seckillMapper;
  @Autowired private SuccessKilledMapper successKilledMapper;
  @Autowired private RedissonClient redissonClient;

  public List<Seckill> getSeckillList() {
    return seckillMapper.selectAll();
  }

  public Seckill getSeckill(Long seckillId) {
    RMap<Long, Seckill> map = redissonClient.getMap(Seckill.class.getName());
    Seckill seckill = map.get(seckillId);
    if (seckill == null) {
      seckill = seckillMapper.selectByPrimaryKey(seckillId);
      if (seckill != null) {
        map.put(seckillId, seckill);
      }
    }
    return seckill;
  }

  public int insertSuccessKilled(Long seckillId, Long userPhone) {
    SuccessKilled successKilled = SuccessKilled.valueOf(seckillId, userPhone);
    return successKilledMapper.insert(successKilled);
  }

  public int reduceNumber(Long seckillId, Date nowTime) {
    return seckillMapper.reduceNumber(seckillId, nowTime);
  }

  public SuccessKilled queryByIdWithSeckill(Long seckillId, Long userPhone) {
    return successKilledMapper.queryByIdWithSeckill(seckillId, userPhone);
  }
}
