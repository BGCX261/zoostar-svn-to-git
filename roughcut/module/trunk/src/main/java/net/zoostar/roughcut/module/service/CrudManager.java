package net.zoostar.roughcut.module.service;

import java.io.Serializable;

import org.springframework.data.domain.Persistable;

import net.zoostar.roughcut.core.dao.Repository;

public interface CrudManager<T extends Persistable<? extends Serializable>> extends Repository<T> {
	
}
