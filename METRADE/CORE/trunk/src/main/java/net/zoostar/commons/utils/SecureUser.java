package net.zoostar.commons.utils;

import java.io.Serializable;

public class SecureUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1410585225219892047L;

	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	private String username;
	
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String password;
	
	public String getSalt() {
		return this.salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	private String salt;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SecureUser [username=").append(username)
				.append(", password=").append(password).append(", salt=")
				.append(salt).append("]");
		return builder.toString();
	}
}
