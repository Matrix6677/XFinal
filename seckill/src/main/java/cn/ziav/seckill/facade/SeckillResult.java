package cn.ziav.seckill.facade;

import cn.ziav.common.exception.ResultCode;

public interface SeckillResult extends ResultCode {

  /** 没有传入seckill_id */
  int NO_SECKILL_ID = -1;
  /** 数据库没有此秒杀商品 */
  int NO_SUCH_SECKILL = -2;
  /** 未注册 */
  int NO_USER_PHONE = -3;
  /** MD5非法 */
  int MD5_INVALID = -4;
  /** 重复秒杀 */
  int REPEATED_SECKILL = -5;
  /** 秒杀结束 */
  int SECKILL_IS_CLOSED = -6;
}
