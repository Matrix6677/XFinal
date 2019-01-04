package cn.ziav.seckill.config;

import java.io.IOException;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redis客户端配置
 *
 * @author Ziav
 */
@Configuration
public class RedisConfig {

  @Value("${redis.center.config}")
  private String centerConfig;

  @Bean
  public RedissonClient redissonCenter(ApplicationContext context) throws IOException {
    Config config = Config.fromYAML(context.getResource(centerConfig).getInputStream());
    return Redisson.create(config);
  }
}
