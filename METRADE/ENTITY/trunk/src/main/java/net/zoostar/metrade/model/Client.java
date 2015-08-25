package net.zoostar.metrade.model;

import static net.zoostar.metrade.model.NamedQueryConstants.FIND_CLIENT_BY_EMAIL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@NamedQuery(name=FIND_CLIENT_BY_EMAIL, query="SELECT c FROM Client c WHERE c.email = :email")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Client implements Serializable, Comparable<Client> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5513072067522927335L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getClientId() {
		return clientId;
	}
	protected void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	private Long clientId;
	
	
	@Column(nullable=false, unique=true, length=50)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private String email;

	
	@Column(nullable=false, length=20)
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	private String salt;

	@Column(nullable=false, length=100)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String password;

	
	@Column(length=10)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	private String firstName;
	
	
	@Column(nullable=false, length=20)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	private String lastName;
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	private Date dateOfBirth;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="clientId")
	public List<Activity> getActivities() {
		return activities;
	}
	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
	private List<Activity> activities;
	
	@OneToMany(mappedBy="client", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@MapKey(name="symbol")
	public Map<String, Balance> getBalances() {
		return balances;
	}
	public void setBalances(Map<String, Balance> balances) {
		this.balances = balances;
	}
	private Map<String, Balance> balances;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdatedTs() {
		return updatedTs;
	}
	public void setUpdatedTs(Date updatedTs) {
		this.updatedTs = updatedTs;
	}
	private Date updatedTs;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedTs() {
		return createdTs;
	}
	public void setCreatedTs(Date createdTs) {
		setUpdatedTs(createdTs);
		this.createdTs = createdTs;
	}
	private Date createdTs;
	
	@Version
	public Long getVersion() {
		return version;
	}
	protected void setVersion(Long version) {
		this.version = version;
	}
	private Long version;
	
	
	public Client() {
		this(null, null);
	}
	
	public Client(String email, String password) {
		setEmail(email);
		setPassword(password);
		this.activities = new ArrayList<Activity>();
		this.balances = new HashMap<String, Balance>();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Client [clientId=").append(clientId).append(", email=")
				.append(email).append("]");
		return builder.toString();
	}
	
	@Override
	public int compareTo(Client client) {
		return email.compareTo(client.getEmail());
	}
}
