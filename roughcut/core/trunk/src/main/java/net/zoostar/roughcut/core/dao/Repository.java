package net.zoostar.roughcut.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Persistable;

public interface Repository<T extends Persistable<? extends Serializable>> {
	void create(T persistable);
	T retrieve(Class<T> clazz, Serializable id);
	T update(T persistable);
	void delete(T persistable);

	EntityManager getEntityManager();
	
	List<T> findByNamedQueryForList(Class<T> persistable, String namedQuery, Map<String, Object> params);
	T findByNamedQueryForObject(Class<T> persistable, String namedQuery, Map<String, Object> params);
}
