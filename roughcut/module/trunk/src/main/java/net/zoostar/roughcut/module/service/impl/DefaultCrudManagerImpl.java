package net.zoostar.roughcut.module.service.impl;

import java.io.Serializable;

import org.springframework.data.domain.Persistable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.zoostar.roughcut.core.dao.impl.DefaultRepository;
import net.zoostar.roughcut.module.service.CrudManager;

@Service
@Transactional
public class DefaultCrudManagerImpl<T extends Persistable<? extends Serializable>>
		extends DefaultRepository<T>
		implements CrudManager<T> {

}
