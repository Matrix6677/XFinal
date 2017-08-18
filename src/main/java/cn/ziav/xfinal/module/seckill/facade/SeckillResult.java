package cn.ziav.xfinal.module.seckill.facade;

import cn.ziav.xfinal.common.exception.ResultCode;

/**
 * 秒杀异常码
 * 
 * @author Z.avi
 */
public interface SeckillResult extends ResultCode {

  /** 未找到秒杀实体 */
  int SECKILL_NOT_FOUNT = -1;
  /** 过期 */
  int OUT_OF_TIME = -2;
  /** 秒杀数据重写 */
  int SECKILL_DATA_REWRITE = -3;
  /** 重复秒杀 */
  int SECKILL_REPEATED = -4;
  /** 手机未注册 */
  int NOT_REGISTER = -5;
  /** 秒杀id错误 */
  int SECKILL_ID_ILLEGAL = -6;

}
