package net.zoostar.metrade.app.service;

import net.zoostar.metrade.app.model.Client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface ActivityManager {
	
	Logger log = LoggerFactory.getLogger(ActivityManager.class);

	Client deposit(Client client, Float amount);
	void withdraw(Client client, Float amount) throws Exception;
	
	void buy(Client client, String symbol, Integer quantity, Float amount) throws Exception;
	void sell(Client client, String symbol, Integer quantity, Float amount) throws Exception;
}
