package cn.ziav.xfinal.common.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) {
    logger.error("访问 {} 发生错误，错误信息：{}", request.getRequestURL(), ex.getMessage());
    if (ex instanceof ManagedException) {
      ManagedException managedException = (ManagedException) ex;
      printWrite(Result.ERROR(managedException.getCode(), managedException.getMessage()), response);
    } else {
      printWrite(Result.ERROR(ResultCode.UNKNOWN_ERROR, ex.getMessage()), response);
    }
    return null;
  }

  /**
   * 将错误信息添加到response中
   * 
   * @param result
   * @param response
   */
  public void printWrite(Result<String> result, HttpServletResponse response) {
    try {
      PrintWriter pw = response.getWriter();
      pw.write(JSON.toJSONString(result));
      pw.flush();
      pw.close();
    } catch (IOException e) {
      logger.error("输出异常报错：{}", e.getMessage());
      printWrite(Result.ERROR(ResultCode.UNKNOWN_ERROR, e.getMessage()), response);
    }
  }
}
