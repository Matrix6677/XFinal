package cn.ziav.seckill.config.db;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * 数据库配置
 *
 * @author Ziav
 */
@Configuration
public class DBConfig {
  /**
   * 事务管理器
   *
   * @param dataSource
   * @return
   */
  @Bean
  public DataSourceTransactionManager transactionManager(HikariDataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }
}
