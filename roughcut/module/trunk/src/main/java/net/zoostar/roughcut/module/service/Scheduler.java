package net.zoostar.roughcut.module.service;

public interface Scheduler {
	boolean isActive();
	String getName();
	public void schedule();
}
