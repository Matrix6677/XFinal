package cn.ziav.xfinal.common.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(isolation = Isolation.READ_COMMITTED)
public class EntityServiceImpl<PK extends Comparable<PK> & Serializable, T extends IEntity<PK>> implements EntityService<PK, T> {
	private EntityManager entityManager;
	private Class<T> entityClz;

	public EntityServiceImpl(EntityManager entityManager, Class<T> entityClz) {
		this.entityManager = entityManager;
		this.entityClz = entityClz;
	}

	@Override
	@Transactional(readOnly = true)
	public T load(PK pk) {
		return entityManager.find(entityClz, pk);
	}

	@Override
	public T loadOrCreate(PK pk, EntityBuilder<PK, T> builder) {
		T t = load(pk);
		if (t == null) {
			t = builder.newInstance(pk);
			entityManager.persist(t);
		}
		return t;
	}

	@Override
	public T update(T t) {
		return entityManager.merge(t);
	}

	@Override
	public T remove(PK pk) {
		T t = entityManager.find(entityClz, pk);
		entityManager.remove(t);
		return t;
	}

	@Override
	public void truncate() {
		Query query = entityManager.createNativeQuery("TRUNCATE TABLE " + entityClz.getSimpleName());
		query.executeUpdate();
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> list() {
		String sql = "from ";
		sql += entityClz.getSimpleName();
		TypedQuery<T> query = entityManager.createQuery(sql, entityClz);
		return query.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> list(int offset, int limit) {
		String sql = "from ";
		sql += entityClz.getSimpleName();
		TypedQuery<T> query = entityManager.createQuery(sql, entityClz);
		query.setFirstResult(offset).setMaxResults(limit);
		return query.getResultList();
	}

}
