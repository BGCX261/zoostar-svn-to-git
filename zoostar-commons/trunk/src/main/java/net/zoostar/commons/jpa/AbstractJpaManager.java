package net.zoostar.commons.jpa;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public abstract class AbstractJpaManager<T extends Serializable> implements JpaManager<T> {
	@PersistenceContext
	protected void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	public EntityManager getEntityManager() {
		return this.entityManager;
	}
	private EntityManager entityManager;
	
	public T find(Class<T> serializable, Long id) {
		return getEntityManager().find(serializable, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByNamedQueryForList(String namedQuery, Map<String, Object> params) {
		Query query = getEntityManager().createNamedQuery(namedQuery);
		for(String key : params.keySet()) {
			query.setParameter(key, params.get(key));
		}
		return (List<T>)query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public T findByNamedQueryForObject(String namedQuery, Map<String, Object> params) {
		Query query = getEntityManager().createNamedQuery(namedQuery);
		for(String key : params.keySet()) {
			query.setParameter(key, params.get(key));
		}
		return (T)query.getSingleResult();
	}
}
