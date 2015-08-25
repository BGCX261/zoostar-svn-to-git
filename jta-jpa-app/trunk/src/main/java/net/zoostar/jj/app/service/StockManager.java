package net.zoostar.jj.app.service;

import net.zoostar.jj.app.model.Stock;


public interface StockManager extends JpaManager<Stock> {
	Stock findBySymbol(String symbol);
}
