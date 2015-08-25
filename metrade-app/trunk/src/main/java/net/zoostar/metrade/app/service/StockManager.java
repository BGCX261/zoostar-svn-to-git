package net.zoostar.metrade.app.service;

import net.zoostar.metrade.app.model.Stock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface StockManager {
	
	Logger log = LoggerFactory.getLogger(StockManager.class);
	
	void addStock(Stock stock);
	Stock findBySymbol(String symbol);
	void remove(Long stockId);
}
