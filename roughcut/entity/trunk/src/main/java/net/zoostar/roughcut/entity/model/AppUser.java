package net.zoostar.roughcut.entity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class AppUser extends AbstractPersistableEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static final Logger log = LoggerFactory.getLogger(AppUser.class);
	
	@Column(nullable=false, length=50, unique=true)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	private String username;
	
	@Column(nullable=false, length=50)
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String password;

	@Column(nullable=false, columnDefinition="boolean default false")
	public Boolean isEnabled() {
		return this.enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	private Boolean enabled = false;

	@Column(nullable=false, columnDefinition="boolean default false")
	public Boolean isAccountExpired() {
		return this.accountExpired;
	}
	public void setAccountExpired(Boolean accountExpired) {
		this.accountExpired = accountExpired;
	}
	private Boolean accountExpired = false;

	@Column(nullable=false, columnDefinition="boolean default false")
	public Boolean isAccountLocked() {
		return this.accountLocked;
	}
	public void setAccountLocked(Boolean accountLocked) {
		this.accountLocked = accountLocked;
	}
	private Boolean accountLocked = false;

	@Column(nullable=false, columnDefinition="boolean default false")
	public Boolean isPasswordExpired() {
		return this.passwordExpired;
	}
	public void setPasswordExpired(Boolean passwordExpired) {
		this.passwordExpired = passwordExpired;
	}
	private Boolean passwordExpired = false;
	
	@Column(nullable=false, length=100)
	public String getRoles() {
		return this.roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	private String roles;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [getId()=").append(getId()).append(", username=")
				.append(username).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppUser other = (AppUser) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}
