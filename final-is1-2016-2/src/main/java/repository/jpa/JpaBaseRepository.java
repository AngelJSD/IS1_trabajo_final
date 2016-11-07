package repository.jpa;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Collections;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import repository.BaseRepository;
import domain.BaseEntity;

@Repository
public abstract class JpaBaseRepository<T extends BaseEntity<K>, K> implements BaseRepository<T, K> {
	Logger logger = Logger.getLogger(getClass());

	@PersistenceContext
	transient EntityManager entityManager;

	protected Class<T> entityClass = getEntityClass();

	protected Class<K> primaryKeyClass = getPrimaryKeyClass();

	public JpaBaseRepository() {
		logger.debug("Creating JPA Respository.");
	}

	@Override
	public void persist(T entity) {
		if (logger.isTraceEnabled()) {
			logger.trace("Persisting: " + entity);
		}
		this.entityManager.persist(entity);
	}

	@Override
	public void bulkPersist(Collection<T> entities) {
		for (T entity : entities) {
			this.entityManager.persist(entity);
		}
	}

	@Override
	public void remove(T entity) {
		if (this.entityManager.contains(entity)) {
			this.entityManager.remove(entity);
		} else {
			T attached = find(entity.getId());
			this.entityManager.remove(attached);
		}
	}

	@Override
	public void removeById(K id) {
		T attached = find(id);
		this.entityManager.remove(attached);
	}

	public int bulkRemoveByIdUsingJQL(Collection<K> ids) {
		if (ids == null || ids.isEmpty()) {
			return 0;
		}
		String jql = "DELETE FROM " + getEntityClass().getSimpleName() + " e WHERE id IN (:ids)";
		Query query = entityManager.createQuery(jql);
		query.setParameter("ids", ids);
		return query.executeUpdate();
	}

	@Override
	public int bulkRemoveById(Collection<K> ids) {
		if (ids == null || ids.isEmpty()) {
			return 0;
		}
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaDelete<T> criteriaDelete = criteriaBuilder.createCriteriaDelete(getEntityClass());
		Root<T> root = criteriaDelete.from(getEntityClass());
		criteriaDelete.where(root.in(ids));
		Query query = entityManager.createQuery(criteriaDelete);
		return query.executeUpdate();
	}

	@Override
	public int removeAll(boolean isConfirmed) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		int countDeleted = entityManager.createQuery("DELETE " + entityClass.getSimpleName())
				.executeUpdate();
		if (isConfirmed)
			tx.commit();
		else
			tx.rollback();
		return countDeleted;
	}

	@Override
	@Transactional
	public void flush() {
		this.entityManager.flush();
	}

	@Override
	@Transactional
	public void clear() {
		this.entityManager.clear();
	}

	@Override
	@Transactional
	public T merge(T entity) {
		if (logger.isDebugEnabled()) {
			logger.debug("Merging: " + entity);
		}
		return this.entityManager.merge(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<T> getEntityClass() {
		if (entityClass == null) {
			ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
			entityClass = (Class<T>) thisType.getActualTypeArguments()[0];
		}
		return entityClass;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<K> getPrimaryKeyClass() {
		if (primaryKeyClass == null) {
			ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
			primaryKeyClass = (Class<K>) thisType.getActualTypeArguments()[1];
		}
		return primaryKeyClass;
	}

	@Override
	public long count() {
		return entityManager.createQuery(
				"SELECT COUNT(o) FROM " + entityClass.getSimpleName() + " o", Long.class)
				.getSingleResult();
	}

	@Override
	public Collection<T> findAll() {
		return entityManager.createQuery("SELECT o FROM " + entityClass.getSimpleName() + " o",
				entityClass).getResultList();
	}

	@Override
	public Collection<T> findByIds(Collection<K> ids) {
		if (ids == null || ids.isEmpty()) {
			return Collections.emptyList();
		}
		String jpaql = "SELECT o FROM " + entityClass.getSimpleName() + " o WHERE o.id IN (:ids)";
		TypedQuery<T> query = entityManager.createQuery(jpaql, entityClass);
		query.setParameter("ids", ids);
		return query.getResultList();
	}

	@Override
	public T find(K id) {
		if (id == null)
			return null;
		return entityManager.find(entityClass, id);
	}

	@Override
	public T find(Class<? extends T> clazz, K id) {
		return entityManager.find(clazz, id);
	};

	@Override
	public Collection<T> findEntries(int firstResult, int maxResults) {
		return entityManager
				.createQuery("SELECT o FROM " + entityClass.getSimpleName() + " o", entityClass)
				.setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
	}

	/**
	 * Return first result if exists else null.
	 * 
	 * @param query
	 * @return
	 */
	protected T getFirstResult(TypedQuery<T> query) {
		Collection<T> results = query.getResultList();
		return results.isEmpty() ? null : results.iterator().next();
	}
}