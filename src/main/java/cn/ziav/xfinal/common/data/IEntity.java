package cn.ziav.xfinal.common.data;

/**
 * 实体标识接口，用于告知锁创建器具体类实例是实体对象
 * 
 * @author Ziav
 */
public interface IEntity<PK> {
  /**
   * 获取实体标识
   * 
   * @return
   */
  PK getId();

}
