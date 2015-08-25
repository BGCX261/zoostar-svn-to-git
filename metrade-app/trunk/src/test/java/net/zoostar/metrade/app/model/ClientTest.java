package net.zoostar.metrade.app.model;

import junit.framework.Assert;

import net.zoostar.metrade.app.model.Client;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:datasource.xml")
@TransactionConfiguration
@Transactional
public class ClientTest {

	private Client client;
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Before
	public void setUp() throws Exception {
		client = new Client();
		client.setEmail("test@email.com");
		client.setFirstName("firstName");
		client.setLastName("lastName");
		client.setPassword("password");
	}

	@After
	public void tearDown() throws Exception {
		client = null;
	}

	@Test
	public void testGetClientId() {
		Assert.assertNull(client.getClientId());
	}

	@Test
	public void testGetPassword() {
		Assert.assertEquals("password", client.getPassword());
	}

	@Test
	public void testGetFirstName() {
		Assert.assertEquals("firstName", client.getFirstName());
	}

	@Test
	public void testGetLastName() {
		Assert.assertEquals("lastName", client.getLastName());
	}

	@Test
	public void testGetEmail() {
		Assert.assertEquals("test@email.com", client.getEmail());
	}

	@Test
	public void testEqualsObject() {
		Client client = new Client();
		client.setEmail("test@email.com");
		Assert.assertEquals(client, this.client);
	}

	@Test(expected=junit.framework.AssertionFailedError.class)
	public void testNotEqualsObject() {
		Client client = new Client();
		client.setEmail("test2@email.com");
		Assert.assertEquals(client, this.client);
	}

	@Test
	public void testCompareTo() {
		Client client = new Client();
		client.setEmail("test2@email.com");
		Assert.assertTrue(client.compareTo(this.client) < 0);
	}
}
