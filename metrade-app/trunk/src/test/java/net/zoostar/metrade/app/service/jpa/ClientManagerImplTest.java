package net.zoostar.metrade.app.service.jpa;

import static net.zoostar.metrade.app.constants.Constants.SYMBOL_CASH;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import junit.framework.Assert;
import net.zoostar.metrade.app.model.Activity;
import net.zoostar.metrade.app.model.Balance;
import net.zoostar.metrade.app.model.Client;
import net.zoostar.metrade.app.model.vo.ClientVO;
import net.zoostar.metrade.app.service.ClientManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:datasource.xml")
public class ClientManagerImplTest {

	static final Logger log = LoggerFactory.getLogger(ClientManagerImplTest.class);
	
	static final String EMAIL = "test@email.com";
	static final String FIRST_NAME = "First";
	static final String LAST_NAME = "Last";
	static final Date DATE_OF_BIRTH = new Date();
	static final String PASSWORD = "password";
	static final Date UPDATE_TS = new Date();
	
	@Autowired
	private ClientManager clientManager;
	
	@Test
	public void testNotNull() {
		System.out.println();
		log.info("Testing ClientManager is NOT NULL...");
		Assert.assertNotNull(clientManager);
		//Assert.assertNotNull(client);
	}
	
	@Test(expected=NoResultException.class)
	public void testRemove() throws Exception {
		System.out.println();
		log.info("Testing client deletion...");
		ClientVO client = clientManager.login(EMAIL, PASSWORD);
		Assert.assertNotNull(client);
		Assert.assertNotNull(client.getClientId());
		clientManager.remove(client.getClientId());
		
		client = clientManager.login(EMAIL, PASSWORD);
	}
	
	@Test
	public void testRegister() throws Exception {
		System.out.println();
		log.info("Testing client registration...");
		Date date = new Date();
		Client client = new Client();
		client.setEmail(EMAIL);
		client.setFirstName(FIRST_NAME);
		client.setLastName(LAST_NAME);
		client.setDateOfBirth(DATE_OF_BIRTH);
		client.setPassword(PASSWORD);
		client.setCreatedTs(date);
		client.setUpdatedTs(date);
		log.info("New Client created: {} at {}", client, client.getCreatedTs());
		Assert.assertNull(client.getClientId()); //Transient
		clientManager.register(client);
		Assert.assertNotNull(client.getClientId()); //Detached
	}
	
	@Test(expected=Exception.class)
	public void testRegisterationWithoutEmail() throws Exception {
		System.out.println();
		log.info("Testing client registration without email...");
		Date date = new Date();
		Client client = new Client();
		client.setFirstName(FIRST_NAME);
		client.setLastName(LAST_NAME);
		client.setDateOfBirth(DATE_OF_BIRTH);
		client.setPassword(PASSWORD);
		client.setCreatedTs(date);
		client.setUpdatedTs(date);
		log.info("New Client created: {} at {}", client, client.getCreatedTs());
		Assert.assertNull(client.getClientId()); //Transient
		clientManager.register(client);
	}
	
	@Test
	public void testLogin() throws Exception {
		System.out.println();
		log.info("Testing client login...");
		ClientVO client = clientManager.login(EMAIL, PASSWORD);
		Assert.assertNotNull(client.getClientId());
		Assert.assertEquals(EMAIL, client.getEmail());
		Assert.assertEquals(FIRST_NAME, client.getFirstName());
		Assert.assertEquals(LAST_NAME, client.getLastName());
		log.info("Client found with email: {}", EMAIL);
	}
	
	@Test
	public void testDeposit() throws Exception {
		System.out.println();
		log.info("Testing client deposit of ${}", 100.50f);
		ClientVO client = clientManager.login(EMAIL, PASSWORD);
		Assert.assertNotNull(client); //Detached
		Assert.assertNotNull(client.getClientId());

		client = clientManager.deposit(client.getClientId(), 100.50f);
		List<Activity> activities = clientManager.fetchActivities(client.getClientId());
		Assert.assertNotNull(activities);
		Assert.assertTrue(activities.size() == 1);
		Activity activity = activities.get(0);
		Assert.assertNotNull(activity.getActivityId());

		Assert.assertNotNull(client.getBalances());
		Assert.assertTrue(client.getBalances().size() == 1);
		Balance balance = client.getBalances().get(SYMBOL_CASH);
		Assert.assertNotNull(balance);
		Assert.assertEquals("Expected a CASH balance.", SYMBOL_CASH, balance.getStock().getSymbol());
		Assert.assertEquals(100.5f, balance.getAmount());
		log.info("Successfully deposited ${}", 100.50f);
		Thread.sleep(1050); //Deliberate pause to generate unique activityDates

		log.info("Testing client deposit of ${}", 112.15f);
		client = clientManager.login(EMAIL, PASSWORD);
		Assert.assertNotNull(client); //Detached
		Assert.assertNotNull(client.getClientId());

		client = clientManager.deposit(client.getClientId(), 112.15f);
		activities = clientManager.fetchActivities(client.getClientId());
		Assert.assertTrue(activities.size() == 2);
		Assert.assertTrue(client.getBalances().size() == 1);
		
		balance = client.getBalances().get(SYMBOL_CASH);
		Assert.assertNotNull(balance);
		Assert.assertEquals("Expected a CASH balance.", SYMBOL_CASH, balance.getStock().getSymbol());
		Assert.assertEquals(212.65f, balance.getAmount());
		log.info("Successfully deposited ${}", 112.15f);
		Thread.sleep(1050); //Deliberate pause to generate unique activityDates
	}

	@Test
	public void testWithdrawal() throws Exception {
		System.out.println();
		log.info("Testing client withdrawal of ${}", 100.50f);
		ClientVO client = clientManager.login(EMAIL, PASSWORD);
		Assert.assertNotNull(client);
		Assert.assertNotNull(client.getClientId());
		
		client = clientManager.withdraw(client.getClientId(), 100.50f);
		List<Activity> activities = clientManager.fetchActivities(client.getClientId());
		Assert.assertNotNull(activities);
		Assert.assertTrue(activities.size() == 3);
		Activity activity = activities.get(2);
		Assert.assertNotNull(activity.getActivityId());

		Assert.assertNotNull(client.getBalances());
		Assert.assertTrue(client.getBalances().size() == 1);
		Balance balance = client.getBalances().get(SYMBOL_CASH);
		Assert.assertNotNull(balance);
		Assert.assertEquals("Expected a CASH balance.", SYMBOL_CASH, balance.getStock().getSymbol());
		Assert.assertTrue(balance.getAmount() < 212.65f);
		log.info("Successfully withdrew ${}", 100.50f);
		Thread.sleep(1050); //Deliberate pause to generate unique activityDates

		client = clientManager.withdraw(client.getClientId(), 112.15f);
		activities = clientManager.fetchActivities(client.getClientId());
		Assert.assertTrue(activities.size() == 4);
		activity = activities.get(3);
		Assert.assertNotNull(activity.getActivityId());

		Assert.assertNotNull(client.getBalances());
		Assert.assertTrue(client.getBalances().size() == 1);
		balance = client.getBalances().get(SYMBOL_CASH);
		Assert.assertNotNull(balance);
		Assert.assertEquals("Expected a CASH balance.", SYMBOL_CASH, balance.getStock().getSymbol());
		Assert.assertTrue(balance.getAmount() <= 0.0f);
		log.info("Successfully withdrew ${}", 112.15f);
		Thread.sleep(1050); //Deliberate pause to generate unique activityDates
		System.out.println();
	}
/*	
	@Test
	public void testBuy() throws Exception {
		Assert.assertNull(client.getClientId());  //Transient
		Assert.assertTrue(client.getActivities().size() <= 0);
		Assert.assertTrue(client.getBalances().size() <= 0);

		log.info("Testing client buy $1000 stocks of INTX");
		clientManager.register(client);
		Assert.assertNotNull(client.getClientId());
		client = clientManager.deposit(client.getClientId(), 1000.0f);
		Assert.assertTrue(client.getActivities().size() == 1);
		Thread.sleep(1050); //Deliberate pause to generate unique activityDates
		
		Activity activity = client.getActivities().get(0);
		Assert.assertNotNull(activity);
		Assert.assertNotNull(activity.getActivityId());
		Assert.assertTrue(client.getBalances().size() == 1);
		
		Balance balance = client.getBalances().get(SYMBOL_CASH);
		Assert.assertNotNull(balance);
		Assert.assertEquals("Expected a CASH balance.", SYMBOL_CASH, balance.getSymbol());
		Assert.assertEquals(1000.0f, balance.getAmount());
		log.info("Successfully deposited ${}", 1000.0f);
		
		client = clientManager.login(EMAIL, PASSWORD);
		Assert.assertNotNull(client.getClientId());
		client = clientManager.buy(client.getClientId(), "INTX", 10, 1000.0f);
		Assert.assertTrue(client.getActivities().size() == 3);
		Assert.assertTrue(client.getBalances().size() == 2);
		Thread.sleep(1050); //Deliberate pause to generate unique activityDates
		
		balance = client.getBalances().get("INTX");
		Assert.assertNotNull(balance);
		Assert.assertEquals("Expected an INTX balance.", "INTX", balance.getSymbol());
		Assert.assertEquals(1000.0f, balance.getAmount());
		
		balance = client.getBalances().get(SYMBOL_CASH);
		Assert.assertNotNull(balance);
		Assert.assertEquals("Expected a CASH balance.", SYMBOL_CASH, balance.getSymbol());
		Assert.assertEquals(0.0f, balance.getAmount());
		log.info("Successfully bought 1000 stock of INTX");
	}

	@Test(expected=InsufficientFundsException.class)
	public void testInsufficientFundsException() throws InsufficientFundsException, Exception {
		Assert.assertNull(client.getClientId());  //Transient
		Assert.assertTrue(client.getActivities().size() <= 0);
		Assert.assertTrue(client.getBalances().size() <= 0);

		clientManager.register(client);
		Client detachedClient = client;
		Assert.assertNotNull(detachedClient.getClientId());
		clientManager.withdraw(detachedClient.getClientId(), 100.50f); //Throws InsufficientFundsException
	}
	
	@Test
	public void testLoginCaching() throws Exception {
		Assert.assertNull(client.getClientId()); //Transient
		log.info("Testing client login...");
		clientManager.register(client);
		Assert.assertNotNull(client.getClientId()); //Persistent

		for(int i=0; i<2; i++) {
			log.info("Calling {}", i+1);
			client = new Client();
			client.setEmail(EMAIL);
			client.setFirstName(FIRST_NAME);
			client.setLastName(LAST_NAME);
			client.setPassword(PASSWORD);
			Assert.assertNull(client.getClientId());  //Transient
			client = clientManager.login(client.getEmail(), client.getPassword());
			Assert.assertNotNull(client.getClientId());
			Assert.assertEquals(EMAIL, client.getEmail());
			Assert.assertEquals(FIRST_NAME, client.getFirstName());
			Assert.assertEquals(LAST_NAME, client.getLastName());
			log.info("Client found with email: {}", client.getEmail());
		}
	}
	
	@Test
	public void testUpdateProfile() throws Exception {
		Assert.assertNull(client.getClientId()); //Transient
		log.info("Testing update to client profile...");
		clientManager.register(client);
		Assert.assertNotNull(client.getClientId()); //Detached
		
		Assert.assertEquals("First", client.getFirstName());
		Assert.assertEquals("Last", client.getLastName());
		Date createdTs = client.getUpdatedTs();
		client.setFirstName("First2");
		clientManager.updateProfile(client);
		client = clientManager.login(EMAIL, PASSWORD);
		Assert.assertEquals("First2", client.getFirstName());
		Assert.assertEquals("Last", client.getLastName());
		Assert.assertNotSame(createdTs, client.getUpdatedTs());
	}
	*/
}
