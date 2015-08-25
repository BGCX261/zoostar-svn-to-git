package net.zoostar.jj.app.service.impl;

import java.util.HashMap;
import java.util.Map;

import net.zoostar.jj.app.model.Stock;
import net.zoostar.jj.app.service.StockManager;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class StockManagerJpaImpl extends AbstractJpaManager<Stock> implements StockManager {
	
	static final Logger L = LoggerFactory.getLogger(StockManagerJpaImpl.class);
	
	@Override
	@Transactional(readOnly=true)
	public Stock findBySymbol(String symbol) {
		Stock stock = null;
		Map<String, Object> params = new HashMap<String, Object>(1);
		params.put(Stock.PARAM_SYMBOL, symbol);
		stock = findByNamedQueryForObject(Stock.class, Stock.FIND_STOCK_BY_SYMBOL, params);
		if(stock == null)
			L.warn("No stock found for symbol [{}]. Returning <NULL>", symbol);
		else
			L.info("Found stock: [{}]", stock);
		return stock;
	}
}
