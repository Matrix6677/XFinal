package cn.ziav.seckill;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 秒杀系统启动类
 *
 * @author Z.avi
 */
@SpringBootApplication(scanBasePackages = "cn.ziav")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SeckillLauncher {
  private static final Logger logger = LoggerFactory.getLogger(SeckillLauncher.class);

  public static void main(String[] args) {
    SpringApplication.run(SeckillLauncher.class, args);
    logger.info("启服成功");
  }
}
