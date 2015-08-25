package net.zoostar.roughcut.core.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Persistable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class DefaultRepository<T extends Persistable<? extends Serializable>>
		implements net.zoostar.roughcut.core.dao.Repository<T> {

	static final Logger log = LoggerFactory.getLogger(DefaultRepository.class);
	
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		log.debug("setEntityManager({})", entityManager);
		this.entityManager = entityManager;
	}
	public EntityManager getEntityManager() {
		return this.entityManager;
	}
	private EntityManager entityManager;

	@Override
	public void create(T persistable) {
		log.info("Creating new entity: [{}]...", persistable);
		getEntityManager().persist(persistable);
		log.info("Created new entity: [{}].", persistable);
	}

	@Override
	public T retrieve(Class<T> clazz, Serializable id) {
		log.info("Retrieving Entity [{}] for id [{}]...", clazz, id);
		T t = getEntityManager().find(clazz, id);
		if(t == null)
			log.warn("Did not retrieve Entity. Returning <NULL>, might cause NullPointerException later!");
		else
			log.info("Retrieved Entity: [{}].", t);
		return t;
	}

	@Override
	public T update(T persistable) {
		log.info("Updating Entity: [{}]...", persistable);
		T t = getEntityManager().merge(persistable);
		log.info("Updated Entity: [{}].", t);
		return t;
	}

	@Override
	public void delete(T persistable) {
		log.info("Deleting Entity: [{}]", persistable);
		getEntityManager().remove(getEntityManager().contains(persistable) ? persistable : getEntityManager().merge(persistable));
		log.warn("Deleted Entity: [{}]", persistable);
	}

	@Override
	public List<T> findByNamedQueryForList(Class<T> persistable,
			String namedQuery, Map<String, Object> params) {
		log.info("Executing Named Query [{}] with Params:", namedQuery);
		TypedQuery<T> query = getEntityManager().createNamedQuery(namedQuery, persistable);
		for (String key : params.keySet()) {
			query.setParameter(key, params.get(key));
			log.info("Key: [{}]; Value: [{}]", key, params.get(key));
		}
		List<T> results = query.getResultList();
		log.info("Fetched Records: [{}]", results.size());
		return results;
	}

	@Override
	public T findByNamedQueryForObject(Class<T> persistable, String namedQuery,
			Map<String, Object> params) {
		log.info("Executing Named Query [{}] with Params:", namedQuery);
		TypedQuery<T> query = getEntityManager().createNamedQuery(namedQuery, persistable);
		for (String key : params.keySet()) {
			log.info("Key: [{}]; Value: [{}]", key, params.get(key));
			query.setParameter(key, params.get(key));
		}
		T t = query.getSingleResult();
		log.info("Entity Found: [{}]", t);
		return t;
	}
}
