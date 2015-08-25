package net.zoostar.metrade.app.service;

import java.util.List;

import net.zoostar.metrade.app.model.Activity;
import net.zoostar.metrade.app.model.Client;
import net.zoostar.metrade.app.model.vo.ClientVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface ClientManager extends IGenericManager {
	
	Logger log = LoggerFactory.getLogger(ClientManager.class);
	
	ClientVO updateProfile(Client detachedClient);
	
	void register(Client client) throws Exception;
	ClientVO login(String email, String password) throws Exception;
	ClientVO remove(Long clientId);
	
	ClientVO deposit(Long clientId, Float amount);
	ClientVO withdraw(Long clientId, Float amount) throws Exception;
	
	ClientVO buy(Long clientId, String symbol, Integer quantity, Float amount) throws Exception;
	ClientVO sell(Long clientId, String symbol, Integer quantity, Float amount) throws Exception;
	
	List<Activity> fetchActivities(Long clientId);
}
