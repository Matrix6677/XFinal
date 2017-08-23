package cn.ziav.xfinal.common.exception;

/**
 * 受管理异常
 * 
 * @author Z.avi
 */
public class ManagedException extends RuntimeException {
  /** */
  private static final long serialVersionUID = -770105656824906425L;

  /** 错误代码 */
  private final int code;

  public ManagedException(int code) {
    super();
    this.code = code;
  }

  public ManagedException(int code, String message, Throwable cause) {
    super(message, cause);
    this.code = code;
  }

  public ManagedException(int code, String message) {
    super(message);
    this.code = code;
  }

  public ManagedException(int code, Throwable cause) {
    super(cause);
    this.code = code;
  }

  /**
   * 获取错误代码
   * 
   * @return
   */
  public int getCode() {
    return code;
  }
}
