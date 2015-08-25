package net.zoostar.roughcut.module.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import net.zoostar.roughcut.module.service.HelloWorldMessageGenerator;

@Component
public class HelloWorldMessageGeneratorImpl extends AbstractSchedulerImpl implements
		HelloWorldMessageGenerator {

	static final Logger log = LoggerFactory.getLogger(HelloWorldMessageGeneratorImpl.class);
	
	@Override
	public void generateMessage() {
		System.out.println("Hello World!");
	}

	@Override
	public void execute() {
		generateMessage();
	}
	
	@Override
	@Scheduled(fixedRate=60000)
	public void schedule() {
		super.schedule();
	}

	@Override
	public String getName() {
		return "jobHelloWorld";
	}
}
