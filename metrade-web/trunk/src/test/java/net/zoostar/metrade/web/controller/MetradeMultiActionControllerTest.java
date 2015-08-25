package net.zoostar.metrade.web.controller;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:datasource.xml")
public class MetradeMultiActionControllerTest {

	@Autowired
	MetradeMultiActionController controller;
	
	@Test
	public void testControllerNotNull() {
		Assert.assertNotNull(controller);
	}
	
	@Test
	public void testLoadHomePage() {
		ModelAndView modelAndView = controller.loadHomePage(new MockHttpServletRequest(), new MockHttpServletResponse());
		ModelAndViewAssert.assertViewName(modelAndView, "index");
	}

}
