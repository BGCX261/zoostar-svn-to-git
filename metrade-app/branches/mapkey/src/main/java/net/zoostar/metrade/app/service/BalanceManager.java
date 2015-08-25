package net.zoostar.metrade.app.service;

import net.zoostar.metrade.app.model.Client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface BalanceManager {
	final Logger log = LoggerFactory.getLogger(BalanceManager.class);
	
	Client deposit(Client client, Float amount);
	void withdraw(Client client, Float amount) throws Exception;
	Client buy(Client client, String symbol, Integer quantity, Float amount);
	void sell(Client client, String symbol, Integer quantity, Float amount) throws Exception;
}
