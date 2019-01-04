package cn.ziav.seckill.manager;

import java.util.Date;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface SeckillMapper extends Mapper<Seckill> {

  @Update(
      "UPDATE seckill SET number = number-1 WHERE seckill_id=#{seckillId} AND start_time <= #{killTime} AND end_time >= #{killTime} AND number > 0")
  int reduceNumber(Long seckillId, Date killTime);
}
