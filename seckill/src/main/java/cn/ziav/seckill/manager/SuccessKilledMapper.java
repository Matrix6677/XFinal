package cn.ziav.seckill.manager;

import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface SuccessKilledMapper extends Mapper<SuccessKilled> {

  @Select(
      "SELECT sk.seckill_id, sk.user_phone, sk.create_time, sk.state, s.seckill_id, s.name, s.number, s.start_time, s.end_time, s.create_time FROM success_killed sk INNER JOIN seckill s ON sk.seckill_id=s.seckill_id WHERE sk.seckill_id=#{seckillId} and sk.user_phone=#{userPhone}")
  SuccessKilled queryByIdWithSeckill(Long seckillId, Long userPhone);
}
