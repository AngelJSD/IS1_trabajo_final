package repository;

import java.util.Collection;

import domain.BaseEntity;

public interface BaseRepository<T extends BaseEntity<K>, K> {

	void persist(T entity);

	void remove(T entity);

	void removeById(K id);

	int removeAll(boolean isConfirmed);

	void flush();

	void clear();

	T merge(T entity);

	long count();

	Collection<T> findAll();

	T find(K id);

	T find(Class<? extends T> clazz, K id);

	Collection<T> findByIds(Collection<K> ids);

	Collection<T> findEntries(int firstResult, int maxResults);

	Class<K> getPrimaryKeyClass();

	Class<T> getEntityClass();

	int bulkRemoveById(Collection<K> ids);

	void bulkPersist(Collection<T> entities);
}