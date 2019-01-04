package cn.ziav.common.exception;

/**
 * 全局的返回状态码定义
 *
 * @author Z.avi
 */
public interface ResultCode {

  /** 成功 */
  int SUCCESS = 0;

  /** 未知错误 */
  int UNKNOWN_ERROR = -255;
  /** 没有这个枚举类型 */
  int NO_ENUM = -256;
}
