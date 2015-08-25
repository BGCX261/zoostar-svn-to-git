package net.zoostar.roughcut.module.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import net.zoostar.roughcut.module.AbstractJUnitTest;
import net.zoostar.roughcut.module.service.HelloWorldMessageGenerator;

public class HelloWorldMessageGeneratorImplTest extends AbstractJUnitTest {

	@Autowired
	HelloWorldMessageGenerator helloWorldMessageGenerator;
	
	@Test
	public void testGenerateMessage() {
		helloWorldMessageGenerator.generateMessage();
	}

	@Test
	public void testGetName() {
		Assert.assertEquals("jobHelloWorld", helloWorldMessageGenerator.getName());
	}

	@Test
	public void testIsActive() {
		Assert.assertFalse(helloWorldMessageGenerator.isActive());
	}

}
