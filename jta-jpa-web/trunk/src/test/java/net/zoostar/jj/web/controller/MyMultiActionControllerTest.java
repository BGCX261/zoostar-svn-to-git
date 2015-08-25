package net.zoostar.jj.web.controller;

import static org.junit.Assert.fail;
import net.zoostar.jj.app.junit.AbstractJUnitTest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.servlet.ModelAndView;

@ContextConfiguration(locations="/applicationContext-web.xml")
public class MyMultiActionControllerTest extends AbstractJUnitTest {

	@Autowired
	MyMultiActionController myMultiActionController;
	
	@Before
	public void setUp() throws Exception {
		System.out.println();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetStockManager() {
		Assert.assertNotNull(myMultiActionController.getStockManager());
	}

	@Test
	public void testDoIndex() {
		ModelAndView modelAndView = myMultiActionController.doIndex(null, null);
		Assert.assertNotNull(modelAndView);
	}

	@Ignore
	@Test
	public void testDoFindStockBySymbol() {
		fail("Not yet implemented");
	}

}
