package cn.ziav.xfinal.common.data;

import java.io.Serializable;
import java.util.List;

/**
 * 实体服务
 * @param <PK> 主键类型
 * @param <T> 实体类型
 * @author Z.avi
 */
public interface EntityService<PK extends Comparable<PK> & Serializable, T extends IEntity<PK>> {
	/**
	 * 加载实体
	 * @param pk
	 * @return
	 */
	T load(PK pk);

	/**
	 * 加载或创建
	 * @param pk
	 * @param builder
	 * @return
	 */
	T loadOrCreate(PK pk, EntityBuilder<PK, T> builder);

	/**
	 * 更新
	 * @param t
	 * @return
	 */
	T update(T t);

	/**
	 * 删除
	 * @param pk
	 */
	T remove(PK pk);

	/**
	 * 清空
	 */
	void truncate();

	List<T> list(int offset, int limit);

	List<T> list();

}