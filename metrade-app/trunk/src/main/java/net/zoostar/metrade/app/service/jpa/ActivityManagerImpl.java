package net.zoostar.metrade.app.service.jpa;

import static net.zoostar.metrade.app.constants.Constants.SYMBOL_CASH;

import java.util.Date;

import net.zoostar.commons.jpa.AbstractJpaManager;
import net.zoostar.metrade.app.model.Activity;
import net.zoostar.metrade.app.model.Client;
import net.zoostar.metrade.app.model.Stock;
import net.zoostar.metrade.app.service.ActivityManager;
import net.zoostar.metrade.app.service.BalanceManager;
import net.zoostar.metrade.app.service.StockManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=false, propagation=Propagation.MANDATORY)
public class ActivityManagerImpl extends AbstractJpaManager<Activity> implements ActivityManager {
	
	@Autowired
	public void setBalanceManager(BalanceManager balanceManager) {
		this.balanceManager = balanceManager;
	}
	public BalanceManager getBalanceManager() {
		return this.balanceManager;
	}
	private BalanceManager balanceManager;

	@Autowired
	public void setStockManager(StockManager stockManager) {
		this.stockManager = stockManager;
	}
	public StockManager getStockManager() {
		return this.stockManager;
	}
	private StockManager stockManager;
	
	protected void recordActivity(final Client client, String symbol, Integer quantity, Float amount) {
		Stock stock = getStockManager().findBySymbol(symbol);
		Activity transientActivity = new Activity(stock, new Date());
		transientActivity.setQuantity(quantity);
		transientActivity.setAmount(amount);
		client.getActivities().add(transientActivity);
	}

	@Override
	public Client deposit(final Client client, Float amount) {
		recordActivity(client, SYMBOL_CASH, 1, amount); //First record activity,
		return getBalanceManager().deposit(client, amount); //then deposit.
	}
	
	@Override
	public void withdraw(final Client client, Float amount) throws Exception {
		getBalanceManager().withdraw(client, amount); //First withdraw,
		recordActivity(client, SYMBOL_CASH, 1, amount*-1); //then record activity.
	}
	
	@Override
	public void buy(Client client, String symbol, Integer quantity, Float amount) throws Exception {
		withdraw(client, amount); //First withdraw funds,
		recordActivity(client, symbol, quantity, amount); //then record buy activity
		getBalanceManager().buy(client, symbol, quantity, amount); //and finally update stock balance.
	}
	
	@Override
	public void sell(Client client, String symbol, Integer quantity, Float amount) throws Exception {
		getBalanceManager().sell(client, symbol, quantity, amount); //First update stock balance,
		recordActivity(client, symbol, quantity*-1, amount*-1); //then record sell activity,
		deposit(client, amount); //and finally deposit the funds.
	}
}
