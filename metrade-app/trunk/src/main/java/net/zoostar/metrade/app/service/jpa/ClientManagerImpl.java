package net.zoostar.metrade.app.service.jpa;

import static net.zoostar.metrade.app.model.NamedQueryConstants.FIND_CLIENT_BY_EMAIL;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.zoostar.commons.aop.annotation.Timeable;
import net.zoostar.commons.jpa.AbstractJpaManager;
import net.zoostar.commons.utils.SecureUser;
import net.zoostar.commons.utils.Security;
import net.zoostar.metrade.app.model.Activity;
import net.zoostar.metrade.app.model.Client;
import net.zoostar.metrade.app.model.vo.ClientVO;
import net.zoostar.metrade.app.service.ActivityManager;
import net.zoostar.metrade.app.service.ClientManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Timeable
@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
public class ClientManagerImpl extends AbstractJpaManager<Client> implements ClientManager {

	@Autowired
	public void setActivityManager(ActivityManager activityManager) {
		this.activityManager = activityManager;
	}
	public ActivityManager getActivityManager() {
		return this.activityManager;
	}
	private ActivityManager activityManager;
	
	
	@Override
	public void register(Client transientClient) throws Exception {
		SecureUser user = Security.generate(transientClient.getEmail(), transientClient.getPassword());
		transientClient.setPassword(user.getPassword());
		transientClient.setSalt(user.getSalt());
		getEntityManager().persist(transientClient);
		log.info("New client registered: {}", transientClient);
	}

	@Override
	@Transactional(readOnly=true)
	public ClientVO login(String email, String password) throws Exception {
		log.info("Login using email {}", email);
		Client client = findByEmail(email);
		if(!Security.authenticate(password, client.getPassword(), client.getSalt()))
			throw new Exception("Authentication Failed!");
		return new ClientVO(client);
	}
	
	protected Client findByEmail(String email) {
		Map<String, Object> params = new HashMap<String, Object>(1);
		params.put("email", email);
		return findByNamedQueryForObject(FIND_CLIENT_BY_EMAIL, params);
	}
	
	@Override
	public ClientVO deposit(Long clientId, Float amount) {
		Client client = getEntityManager().find(Client.class, clientId);
		return new ClientVO(getActivityManager().deposit(client, amount));
	}
	
	@Override
	public ClientVO buy(Long clientId, String symbol, Integer quantity, Float amount)
			throws Exception {
		Client client = getEntityManager().find(Client.class, clientId);
		getActivityManager().buy(client, symbol, quantity, amount);
		return new ClientVO(client);
	}

	@Override
	public ClientVO withdraw(Long clientId, Float amount) throws Exception {
		Client client = getEntityManager().find(Client.class, clientId);
		getActivityManager().withdraw(client, amount);
		return new ClientVO(client);
	}
	
	@Override
	public ClientVO sell(Long clientId, String symbol, Integer quantity, Float amount)
			throws Exception {
		Client client = getEntityManager().find(Client.class, clientId);
		getActivityManager().sell(client, symbol, quantity, amount);
		return new ClientVO(client);
	}
	
	@Override
	public ClientVO updateProfile(Client detachedClient) {
		Client persistentClient = getEntityManager().find(Client.class, detachedClient.getClientId());
		persistentClient.setFirstName(detachedClient.getFirstName());
		persistentClient.setLastName(detachedClient.getLastName());
		persistentClient.setDateOfBirth(detachedClient.getDateOfBirth());
		persistentClient.setUpdatedTs(new Date());
		return new ClientVO(persistentClient);
	}

	@Override
	public void remove(Long clientId) {
		Client client = getEntityManager().find(Client.class, clientId);
		if(client != null) {
			getEntityManager().remove(client);
			log.warn("Removed [{}] from DB.", client);
		} else {
			log.warn("Did not find client with id [{}] in DB to remove!", clientId);
		}
	}
	
	@Override
	public List<Activity> fetchActivities(Long clientId) {
		Client client = getEntityManager().find(Client.class, clientId);
		List<Activity> activities = client.getActivities();
		if(activities == null) {
			log.warn("No activities found for client {}! Returning empty list.", client);
			activities = new ArrayList<Activity>();
		} else {
			log.debug("Fetched {} activities for client {}", activities.size(), client);
		}
		return activities;
	}
}
