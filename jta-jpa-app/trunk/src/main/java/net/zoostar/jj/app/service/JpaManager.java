package net.zoostar.jj.app.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.BeanNameAware;

public interface JpaManager<T extends Serializable> extends BeanNameAware {
	EntityManager getEntityManager();
	T find(Class<T> serializable, Long id);
	List<T> findByNamedQueryForList(Class<T> t, String namedQuery, Map<String, Object> params);
	T findByNamedQueryForObject(Class<T> t, String namedQuery, Map<String, Object> params);
	void addEntity(T entity);
}
