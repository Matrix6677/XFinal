package cn.ziav.xfinal.common;

import org.springframework.data.redis.core.StringRedisTemplate;

public class EntityCacheServiceImpl<T> implements EntityCacheService<T> {

	private Class<T> entityClz;
	private StringRedisTemplate redisTemplate;

	public EntityCacheServiceImpl(StringRedisTemplate redisTemplate, Class<T> entityClz) {
		this.entityClz = entityClz;
		this.redisTemplate = redisTemplate;
	}
}
