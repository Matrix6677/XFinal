package cn.ziav.xfinal.common.cache;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.alibaba.fastjson.JSON;

public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {
	private Class<T> type;
	static final byte[] EMPTY_ARRAY = new byte[0];

	public FastJsonRedisSerializer(Class<T> type) {
		this.type = type;
	}

	@Override
	public byte[] serialize(T t) throws SerializationException {
		if (t == null) {
			return EMPTY_ARRAY;
		}
		return JSON.toJSONBytes(t);
	}

	@Override
	public T deserialize(byte[] bytes) throws SerializationException {
		if (isEmpty(bytes)) {
			return null;
		}
		return JSON.parseObject(bytes, type);
	}

	private boolean isEmpty(byte[] data) {
		return (data == null || data.length == 0);
	}
}
