package net.zoostar.roughcut.module.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import net.zoostar.roughcut.module.service.Scheduler;

public abstract class AbstractSchedulerImpl implements Scheduler {

	static final Logger log = LoggerFactory.getLogger(AbstractSchedulerImpl.class);
	
	@Override
	public boolean isActive() {
		return this.active;
	}
	@Value(value="${scheduler.active}")
	public void setActive(boolean active) {
		log.debug("setActive({})", active);
		this.active = active;
	}
	private boolean active = false;
	
	public abstract void execute();

	@Override
	public void schedule() {
		if(isActive()) {
			log.info("Executing scheduled job: [{}]", this);
			execute();
		} else {
			log.warn("Skipping scheduled job: [{}]", this);
		}
	}
}
