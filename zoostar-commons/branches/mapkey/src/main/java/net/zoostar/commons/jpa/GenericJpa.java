package net.zoostar.commons.jpa;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

public interface GenericJpa<T extends Serializable> {
	EntityManager getEntityManager();
	T find(Class<T> serializable, Long id);
	List<T> findByNamedQueryForList(String namedQuery, Map<String, Object> params);
	T findByNamedQueryForObject(String namedQuery, Map<String, Object> params);
}
