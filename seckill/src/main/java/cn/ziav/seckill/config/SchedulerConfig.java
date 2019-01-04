package cn.ziav.seckill.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * 时间调度任务配置
 *
 * @author Ziav
 */
@Configuration
@EnableAsync
@EnableScheduling
public class SchedulerConfig {

  @Bean
  public ThreadPoolTaskScheduler taskScheduler() {
    ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
    scheduler.setThreadNamePrefix("Scheduler-");
    scheduler.setWaitForTasksToCompleteOnShutdown(true);
    return scheduler;
  }
}
