package cn.ziav.xfinal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisConfig {
	@Bean
	public StringRedisTemplate redisTemplate(RedisConnectionFactory factory) {
		StringRedisTemplate redisTemplate = new StringRedisTemplate();
		redisTemplate.setConnectionFactory(factory);
		redisTemplate.setEnableTransactionSupport(true);
		return redisTemplate;
	}

}
