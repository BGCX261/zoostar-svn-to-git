package net.zoostar.jj.app.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import net.zoostar.jj.app.service.JpaManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class AbstractJpaManager<T extends Serializable> implements JpaManager<T> {
	
	static final Logger L = LoggerFactory.getLogger(AbstractJpaManager.class);
	
	@PersistenceContext
	protected void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	public EntityManager getEntityManager() {
		return this.entityManager;
	}
	private EntityManager entityManager;
	
	@Override
	public T find(Class<T> serializable, Long id) {
		return getEntityManager().find(serializable, id);
	}
	
	@Override
	public List<T> findByNamedQueryForList(Class<T> t, String namedQuery, Map<String, Object> params) {
		TypedQuery<T> query = getEntityManager().createNamedQuery(namedQuery, t);
		for(String key : params.keySet()) {
			query.setParameter(key, params.get(key));
		}
		return query.getResultList();
	}
	
	@Override
	public T findByNamedQueryForObject(Class<T> t, String namedQuery, Map<String, Object> params) {
		TypedQuery<T> query = getEntityManager().createNamedQuery(namedQuery, t);
		for(String key : params.keySet()) {
			query.setParameter(key, params.get(key));
		}
		return query.getSingleResult();
	}
	
	@Override
	public void addEntity(T entity) {
		getEntityManager().persist(entity);
		L.info("Added Entity: [{}]", entity);
	}	
	
	@Override
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	public String getBeanName() {
		return this.beanName;
	}
	private String beanName;
}
