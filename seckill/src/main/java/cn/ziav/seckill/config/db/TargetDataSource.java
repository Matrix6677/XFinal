package cn.ziav.seckill.config.db;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 指定数据源
 *
 * @author Ziav
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface TargetDataSource {

  /**
   * 数据源类型，默认为{@link DataSourceKey#SECKILL}
   *
   * @return
   */
  DataSourceKey value() default DataSourceKey.SECKILL;
}
