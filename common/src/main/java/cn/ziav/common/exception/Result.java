package cn.ziav.common.exception;

import static cn.ziav.common.exception.ResultCode.SUCCESS;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回对象
 *
 * @author Z.avi
 */
public class Result<T> implements Serializable {

  /** */
  private static final long serialVersionUID = -6219092741148462736L;

  public static final String MAP_KEY_CODE = "code";
  public static final String MAP_KEY_CONTENT = "content";

  private int code;
  private T content;

  /**
   * 创建成功的返回对象
   *
   * @return
   */
  public static Result SUCCESS() {
    return new Result<>(SUCCESS);
  }

  /**
   * 创建成功的返回对象
   *
   * @param content 返回内容
   * @return
   */
  public static <T> Result<T> SUCCESS(T content) {
    return new Result<>(SUCCESS, content);
  }

  /**
   * 创建错误的返回对象
   *
   * @param code 错误状态码
   * @return
   */
  public static <T> Result<T> ERROR(int code) {
    return new Result<>(code);
  }

  /**
   * 创建错误的返回对象
   *
   * @param code 错误状态码
   * @param content 返回内容
   * @return
   */
  public static <T> Result<T> ERROR(int code, T content) {
    return new Result<>(code, content);
  }

  public Map<String, Object> toMap() {
    Map<String, Object> result = new HashMap<>(2);
    result.put(MAP_KEY_CODE, code);
    result.put(MAP_KEY_CONTENT, content);
    return result;
  }

  public Result() {}

  public Result(int code) {
    this.code = code;
    this.content = null;
  }

  public Result(int code, T content) {
    this.code = code;
    this.content = content;
  }

  /**
   * 获取状态码
   *
   * @return
   */
  public int getCode() {
    return code;
  }

  /**
   * 获取内容
   *
   * @return
   */
  public T getContent() {
    return content;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public void setContent(T content) {
    this.content = content;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Result<?> result = (Result<?>) o;

    if (code != result.code) {
      return false;
    }
    return content.equals(result.content);
  }

  @Override
  public int hashCode() {
    int result = code;
    result = 31 * result + content.hashCode();
    return result;
  }
}
