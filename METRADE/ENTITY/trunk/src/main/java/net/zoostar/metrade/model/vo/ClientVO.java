package net.zoostar.metrade.model.vo;

import java.util.Date;
import java.util.Map;

import net.zoostar.metrade.model.Balance;
import net.zoostar.metrade.model.Client;

public class ClientVO {
	
	private Client client;
	protected Client getClient() {
		return this.client;
	}
	protected void setClient(Client client) {
		this.client = client;
	}
	
	public ClientVO(Client client) {
		this.client = client;
	}
	
	public Long getClientId() {
		return client.getClientId();
	}

	public String getEmail() {
		return client.getEmail();
	}

	public void setEmail(String email) {
		client.setEmail(email);
	}

	public String getPassword() {
		return client.getPassword();
	}

	public void setPassword(String password) {
		client.setPassword(password);
	}

	public String getFirstName() {
		return client.getFirstName();
	}

	public void setFirstName(String firstName) {
		client.setFirstName(firstName);
	}

	public String getLastName() {
		return client.getLastName();
	}

	public void setLastName(String lastName) {
		client.setLastName(lastName);
	}

	public Date getDateOfBirth() {
		return client.getDateOfBirth();
	}

	public void setDateOfBirth(Date dateOfBirth) {
		client.setDateOfBirth(dateOfBirth);
	}

	public Map<String, Balance> getBalances() {
		return client.getBalances();
	}

	public Date getUpdatedTs() {
		return client.getUpdatedTs();
	}

	public void setUpdatedTs(Date updatedTs) {
		client.setUpdatedTs(updatedTs);
	}

	public Date getCreatedTs() {
		return client.getCreatedTs();
	}

	public void setCreatedTs(Date createdTs) {
		client.setCreatedTs(createdTs);
	}

	public Long getVersion() {
		return client.getVersion();
	}

	public int hashCode() {
		return client.hashCode();
	}

	public boolean equals(Object obj) {
		return client.equals(obj);
	}

	public String toString() {
		return client.toString();
	}
}
