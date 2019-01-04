package cn.ziav.common.exception;

import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.authz.UnauthenticatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 *
 * @author Z.avi
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @ExceptionHandler
  @ResponseBody
  public Result handleManagedException(ManagedException ex) {
    return Result.ERROR(ex.getCode(), ex.getMessage());
  }

  @ExceptionHandler
  public void handleRuntimeException(HttpServletRequest request, RuntimeException ex) {
    if (ex instanceof ManagedException) {
      return;
    }
    logger.error("访问 {} 发生错误，参数：{}", request.getRequestURL(), request.getQueryString());
    logger.error("", ex);
  }

  @ExceptionHandler(UnauthenticatedException.class)
  @ResponseBody
  public Result handleUnauthException(HttpServletRequest request) {
    logger.warn("访问没有权限的地址：{}", request.getRequestURL());
    return Result.ERROR(ResultCode.UNKNOWN_ERROR, "没有访问" + request.getRequestURL() + "的权限");
  }
}
