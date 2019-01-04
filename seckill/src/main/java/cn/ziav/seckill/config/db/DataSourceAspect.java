package cn.ziav.seckill.config.db;

import java.lang.reflect.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/** @author Ziav */
@Aspect
@Order(-1) // 保证该AOP在@Transactional之前执行
@Component
public class DataSourceAspect {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  /** 使用空方法定义切点表达式 */
  @Pointcut("@annotation(TargetDataSource)")
  public void dataSourcePointCut() {}

  @Before(value = "dataSourcePointCut()")
  public void setDataSourceKey(JoinPoint joinPoint) {
    // 根据连接点所属的类实例，动态切换数据源
    Object target = joinPoint.getTarget();
    String method = joinPoint.getSignature().getName();
    Class<?> clazz = target.getClass();
    Class<?>[] parameterTypes =
        ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
    try {
      Method m = clazz.getMethod(method, parameterTypes);
      TargetDataSource data = m.getAnnotation(TargetDataSource.class);
      DataSourceKey sourceKey = data.value();
      DynamicDataSource.switchDataSource(sourceKey);
      logger.info("switch to " + sourceKey + " datasource");
    } catch (Exception e) {
      logger.error("switch datasource failed", e);
    }
  }

  // 执行完切面后，将线程共享中的数据源名称清空
  @After("dataSourcePointCut()")
  public void after(JoinPoint joinPoint) {
    DynamicDataSource.removeDataSource();
  }
}
