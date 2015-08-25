package net.zoostar.metrade.app.service.jpa;

import static net.zoostar.metrade.app.constants.Constants.SYMBOL_CASH;

import java.util.Date;

import net.zoostar.metrade.app.model.Balance;
import net.zoostar.metrade.app.model.Client;
import net.zoostar.metrade.app.model.Stock;
import net.zoostar.metrade.app.service.BalanceManager;
import net.zoostar.metrade.app.service.StockManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=false, propagation=Propagation.MANDATORY)
public class BalanceManagerImpl implements BalanceManager {

	@Autowired
	public void setStockManager(StockManager stockManager) {
		this.stockManager = stockManager;
	}
	public StockManager getStockManager() {
		return this.stockManager;
	}
	private StockManager stockManager;
	
	@Override
	public Client deposit(final Client client, final Float amount) {
		return buy(client, SYMBOL_CASH, 0, amount);
	}
	
	@Override
	public Client buy(final Client client, final String symbol,
			final Integer quantity, final Float amount) {
		Balance balance = client.getBalances().get(symbol);
		if(balance == null) {
			synchronized(client) {
				balance = client.getBalances().get(symbol);
				if(balance == null) {
					Stock stock = stockManager.findBySymbol(symbol);
					balance = new Balance(client, stock);
					client.getBalances().put(symbol, balance);
					log.warn("Created new {} balance for {} ", symbol, client);
				}
			}
		}
		updateBalance(balance, quantity, amount);
		return client;
	}
	
	protected void updateBalance(Balance balance, Integer quantity, Float amount) {
		balance.setQuantity(balance.getQuantity() + quantity);
		balance.setAmount(balance.getAmount() + amount);
		balance.setUpdatedTs(new Date());
	}
	
	@Override
	public void withdraw(Client client, Float amount) throws Exception {
		Balance balance = client.getBalances().get(SYMBOL_CASH);
		if(balance == null || balance.getAmount() - amount < 0)
			throw new Exception("Insufficient funds for withdrawal!");
		updateBalance(balance, 0, amount*-1);
	}
	
	@Override
	public void sell(Client client, String symbol, Integer quantity, Float amount) throws Exception {
		Balance balance = client.getBalances().get(symbol);
		if(balance == null || balance.getQuantity() - quantity < 0)
			throw new Exception("Insufficient quantity to sell!");
		updateBalance(balance, quantity*-1, amount*-1);
	}
}
