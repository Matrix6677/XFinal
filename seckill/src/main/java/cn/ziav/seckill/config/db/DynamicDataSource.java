package cn.ziav.seckill.config.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/** @author Ziav */
public class DynamicDataSource extends AbstractRoutingDataSource {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  private static final ThreadLocal<DataSourceKey> CONTEXT_HOLDER =
      ThreadLocal.withInitial(() -> DataSourceKey.SECKILL);

  public static void switchDataSource(DataSourceKey type) {
    CONTEXT_HOLDER.set(type);
  }

  public static DataSourceKey getDataSourceKey() {
    return CONTEXT_HOLDER.get();
  }

  public static void removeDataSource() {
    CONTEXT_HOLDER.remove();
  }

  @Override
  protected Object determineCurrentLookupKey() {
    logger.info("Current DataSource is [{}]", getDataSourceKey());
    return getDataSourceKey();
  }
}
