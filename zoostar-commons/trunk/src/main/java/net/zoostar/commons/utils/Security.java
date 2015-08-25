package net.zoostar.commons.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.xml.bind.DatatypeConverter;

public class Security {

	public static final int DEFAULT_ITERATION = 1000;

	/**
	 * From a password, a number of iterations and a salt, returns the
	 * corresponding digest
	 * 
	 * @param iterationNb
	 *            int The number of iterations of the algorithm
	 * @param password
	 *            String The password to encrypt
	 * @param salt
	 *            byte[] The salt
	 * @return byte[] The digested password
	 * @throws NoSuchAlgorithmException
	 *             If the algorithm doesn't exist
	 * @throws UnsupportedEncodingException
	 */
	private static byte[] getHash(int iteration, String password, byte[] salt)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		if(iteration <= 0)
			throw new IllegalArgumentException("Iteration value has to be greater than 0. Value passed " + iteration);
		
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		digest.reset();
		digest.update(salt);
		byte[] input = digest.digest(password.getBytes("UTF-8"));
		for (int i = 0; i < iteration; i++) {
			digest.reset();
			input = digest.digest(input);
		}
		return input;
	}

	public static boolean authenticate(String actual /*User Supplied Clear Text Password*/,
			String expected /*DB Supplied Encrypted Password*/, String salt, int iteration)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		byte[] bExpected = DatatypeConverter.parseBase64Binary(expected);
		byte[] bSalt = DatatypeConverter.parseBase64Binary(salt);

		byte[] proposedDigest = getHash(iteration, actual, bSalt);
		return Arrays.equals(proposedDigest, bExpected);
	}

	public static boolean authenticate(String actual /*User Supplied Clear Text Password*/,
			String expected /*DB Supplied Encrypted Password*/, String salt)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return authenticate(actual, expected, salt, DEFAULT_ITERATION);
	}
	
	public static SecureUser generate(int iteration, String username, String password)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		SecureUser user = new SecureUser();
		user.setUsername(username);
		
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		byte[] bSalt = new byte[8];
		random.nextBytes(bSalt);
		byte[] bDigest = getHash(iteration, password, bSalt);
		user.setPassword(DatatypeConverter.printBase64Binary(bDigest));
		user.setSalt(DatatypeConverter.printBase64Binary(bSalt));
		return user;
	}
	
	public static SecureUser generate(String username, String password)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return generate(DEFAULT_ITERATION, username, password);
	}
}
