package net.zoostar.roughcut.module.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import net.zoostar.roughcut.entity.model.Company;
import net.zoostar.roughcut.module.AbstractJUnitTest;

public class CompanyCrudManagerTest extends AbstractJUnitTest {

	static final Logger log = LoggerFactory.getLogger(CompanyCrudManagerTest.class);
	
	static final String COMPANY_NAME = "Test Company";
	static final String USER_NAME = "junit";
	
	@Autowired
	CrudManager<Company> companyManager;
	
	public void setUp() throws Exception {
		super.setUp();
		Assert.assertNotNull(companyManager);
	}
	
	@Test
	public void testCreate() {
		Company company = null;
		boolean isTrue = true;
		
		while(isTrue) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("name", COMPANY_NAME.toLowerCase());
			
			try {
				company = companyManager.findByNamedQueryForObject(Company.class, "company.retrieveByName", params);
			} catch(NoResultException e) {
				log.warn(e.getMessage());
			}
			
			if(company != null) {
				companyManager.delete(company);
				company = null;
			} else {
				isTrue = false;
				company = new Company();
				company.setName(COMPANY_NAME);
				company.setCreatedBy(USER_NAME);
				Assert.assertNull(company.getId());
				companyManager.create(company);
				Assert.assertNotNull(company.getId());
				Assert.assertEquals(COMPANY_NAME.toLowerCase(), company.getLowerName());
			}
		}
	}
	
	@Test(expected=PersistenceException.class)
	public void testDuplicateCreate() {
		testCreate();

		Company company = new Company();
		company.setName(COMPANY_NAME);
		Assert.assertNull(company.getId());
		
		companyManager.create(company);
		companyManager.getEntityManager().flush();
	}
	
	@Test
	public void testRetrieveByName() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", COMPANY_NAME.toLowerCase());
		boolean isTrue = true;
		Company company = null;
		
		while(isTrue) {
			try {
				company = companyManager.findByNamedQueryForObject(Company.class, "company.retrieveByName", params);
				isTrue = false;
				Assert.assertNotNull(company.getId());
				Assert.assertEquals(COMPANY_NAME, company.getName());
				Assert.assertEquals(COMPANY_NAME.toLowerCase(), company.getLowerName());
				Assert.assertEquals(USER_NAME, company.getCreatedBy());
			} catch(NoResultException e) {
				log.warn(e.getMessage());
				company = new Company();
				company.setName(COMPANY_NAME);
				company.setCreatedBy(USER_NAME);
				companyManager.create(company);
			}
		}
	}

	@Test
	public void testUpdate() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", COMPANY_NAME.toLowerCase());
		Company company = null;
		boolean isTrue = true;
		
		while(isTrue) {
			try {
				company = companyManager.findByNamedQueryForObject(Company.class, "company.retrieveByName", params);
				isTrue = false;
				
				Assert.assertNotNull(company.getId());
				String id = company.getId();
				Assert.assertEquals(COMPANY_NAME, company.getName());
				Assert.assertEquals(COMPANY_NAME.toLowerCase(), company.getLowerName());
				Assert.assertEquals(USER_NAME, company.getCreatedBy());
				
				company.setName(COMPANY_NAME + " 2");
				company.setLastModifiedBy(USER_NAME + " 2");
				
				company = companyManager.update(company);
				Assert.assertNotNull(company);
				Assert.assertNotNull(company.getId());
				Assert.assertEquals(id, company.getId());
				Assert.assertNotEquals(COMPANY_NAME, company.getName());
				Assert.assertEquals(COMPANY_NAME + " 2", company.getName());
				Assert.assertEquals(COMPANY_NAME.toLowerCase() + " 2", company.getLowerName());
				Assert.assertEquals(USER_NAME + " 2", company.getLastModifiedBy());
			} catch(NoResultException e) {
				log.warn(e.getMessage());
				company = new Company();
				company.setName(COMPANY_NAME);
				company.setCreatedBy(USER_NAME);
				companyManager.create(company);
			}
		}
	}
	
	@Test
	public void testDelete() {
		Company company = null;
		boolean isTrue = true;
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", COMPANY_NAME.toLowerCase());

		while(isTrue) {
			try {
				company = companyManager.findByNamedQueryForObject(Company.class, "company.retrieveByName", params);
				isTrue = false;
				
				companyManager.delete(company);
				companyManager.getEntityManager().flush();
				company = null;

				try {
					company = companyManager.findByNamedQueryForObject(Company.class, "company.retrieveByName", params);
				} catch(NoResultException e) {
					log.warn(e.getMessage());
					Assert.assertNull(company);
				}
			} catch(NoResultException e) {
				log.warn(e.getMessage());
				testCreate();
			}
		}
	}

	@Test
	public void testRetrieveAll() {
		List<Company> companies = companyManager.findByNamedQueryForList(Company.class, "company.retrieveAllByName", new HashMap<String, Object>(0));
		Assert.assertNotNull(companies);
		for(Iterator<Company> it = companies.iterator(); it.hasNext();) {
			Company company = it.next();
			Assert.assertNotNull(company);
			Assert.assertNotNull(company.getId());
			log.info("Company: [{}]", company);
		}
	}
}
