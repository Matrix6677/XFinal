package cn.ziav.xfinal.common.exception;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回对象
 * @author Frank
 */
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 8855012718532767136L;

	/** 成功的返回码 */
	public static final int SUCCESS = 0;

	public static final String MAP_KEY_CODE = "code";
	public static final String MAP_KEY_CONTENT = "content";

	private int code;
	private T content;

	/**
	 * 创建成功的返回对象
	 * @param code 成功的状态吗
	 * @return
	 */
	public static <T> Result<T> SUCCESS() {
		return new Result<T>(SUCCESS);
	}

	/**
	 * 创建成功的返回对象
	 * @param content 返回内容
	 * @return
	 */
	public static <T> Result<T> SUCCESS(T content) {
		return new Result<T>(SUCCESS, content);
	}

	/**
	 * 创建错误的返回对象
	 * @param code 错误状态码
	 * @return
	 */
	public static <T> Result<T> ERROR(int code) {
		return new Result<T>(code);
	}

	/**
	 * 创建错误的返回对象
	 * @param code 错误状态码
	 * @param content 返回内容
	 * @return
	 */
	public static <T> Result<T> ERROR(int code, T content) {
		return new Result<T>(code, content);
	}

	public Map<String, Object> toMap() {
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put(MAP_KEY_CODE, code);
		result.put(MAP_KEY_CONTENT, content);
		return result;
	}

	public Result() {
	}

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
	 * @return
	 */
	public int getCode() {
		return code;
	}

	/**
	 * 获取内容
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Result<?> other = (Result<?>) obj;
		if (code != other.code)
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		return true;
	}

}
