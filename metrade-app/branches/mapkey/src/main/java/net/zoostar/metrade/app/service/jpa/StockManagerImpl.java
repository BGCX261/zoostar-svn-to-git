package net.zoostar.metrade.app.service.jpa;

import static net.zoostar.metrade.app.constants.Constants.STOCK_CACHE;
import static net.zoostar.metrade.app.model.NamedQueryConstants.ARG_SYMBOL;
import static net.zoostar.metrade.app.model.NamedQueryConstants.FIND_STOCK_BY_SYMBOL;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.NoResultException;

import net.zoostar.commons.aop.annotation.Timeable;
import net.zoostar.commons.jpa.AbstractJpaImpl;
import net.zoostar.metrade.app.model.Stock;
import net.zoostar.metrade.app.service.StockManager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Timeable
@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW)
public class StockManagerImpl extends AbstractJpaImpl<Stock> implements StockManager {
	
	@Override
	public void addStock(final Stock stock) {
		getEntityManager().persist(stock);
		log.warn("Added new stock {}", stock);
	}

	protected Stock findBySymbol(final String symbol, Map<String, Object> params, boolean synchronous) {
		Stock stock = null;
		if(synchronous) {
			synchronized(symbol) {
				try {
					log.debug("Synchronized find stock by symbol {}", symbol);
					stock = findByNamedQueryForObject(FIND_STOCK_BY_SYMBOL, params);
				} catch(NoResultException e) {
					stock = new Stock(symbol);
					addStock(stock);
				}
			}
		} else {
			try {
				log.debug("Find stock by symbol {}", symbol);
				stock = findByNamedQueryForObject(FIND_STOCK_BY_SYMBOL, params);
			} catch(NoResultException e) {
				return findBySymbol(symbol, params, true);
			}
		}
		return stock;
	}

	@Override
	public Stock findBySymbol(final String symbol) {
		Map<String, Object> params = new HashMap<String, Object>(1);
		params.put(ARG_SYMBOL, symbol);
		return findBySymbol(symbol, params, false);
	}

	@Override
	@Transactional(propagation=Propagation.NEVER)
	public void clearCache() {
		log.warn("Cleared cache: " + STOCK_CACHE);
	}

}
