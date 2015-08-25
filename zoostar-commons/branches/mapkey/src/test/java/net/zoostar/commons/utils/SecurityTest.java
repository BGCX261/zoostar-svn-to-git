package net.zoostar.commons.utils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityTest extends TestCase {

	static final Logger log = LoggerFactory.getLogger(SecurityTest.class);
	
	public SecurityTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testAuthenticate()
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String password = "password";
		SecureUser user = Security.generate("test@zoostar.net", password);
		log.debug(user.toString());
		Assert.assertTrue(Security.authenticate(password, user.getPassword(), user.getSalt()));
	}
	
	public void testAuthenticateFailure()
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String password = "password";
		SecureUser user = Security.generate("test@zoostar.net", password);
		Assert.assertFalse(Security.authenticate("PASSWORD", user.getPassword(), user.getSalt()));
		Assert.assertFalse(Security.authenticate("password1", user.getPassword(), user.getSalt()));
	}
}
