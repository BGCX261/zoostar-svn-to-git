package net.zoostar.roughcut.web.controller;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:META-INF/applicationContext-*.xml"})
@ActiveProfiles({"standalone"})
public class CompanyControllerTest {

	@Autowired
	CompanyController companyController;
	
	@Before
	public void setUp() throws Exception {
		Assert.assertNotNull("Company Controller could not be autowired!", companyController);
		Assert.assertNotNull("CRUD Manager is null!", companyController.getCrudManager());
	}

	@Test
	public void testRetrieve() {
		Model model = new ExtendedModelMap();
		String view = companyController.retrieve(model);
		Assert.assertNotNull(view);
		Assert.assertEquals("company.retrieve", view);
		
		Map<String, Object> records = model.asMap();
		Assert.assertNotNull(records);
	}

}
