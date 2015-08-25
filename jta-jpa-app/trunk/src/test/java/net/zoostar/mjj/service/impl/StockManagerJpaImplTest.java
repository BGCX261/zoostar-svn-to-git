package net.zoostar.mjj.service.impl;

import junit.framework.Assert;
import net.zoostar.jj.app.junit.AbstractJUnitTest;
import net.zoostar.jj.app.model.Stock;
import net.zoostar.jj.app.service.StockManager;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class StockManagerJpaImplTest extends AbstractJUnitTest {

	static final Logger L = LoggerFactory.getLogger(StockManagerJpaImplTest.class);
	
	static final String SYMBOL = "TEST";
	
	@Autowired
	StockManager stockManager;
	
	@Before
	public void setUp() throws Exception {
		System.out.println();
		Assert.assertNotNull(stockManager);
	}

	@Test
	public void testAddStock() {
		Stock stock = new Stock(SYMBOL, "Test");
		Assert.assertNull(stock.getStockId());
		L.info("Adding stock: [{}]", stock);
		stockManager.addEntity(stock);
		Assert.assertNotNull(stock.getStockId());
	}
	
	@Test
	public void testFindBySymbol() {
		testAddStock();
		
		String symbol = SYMBOL;
		L.info("Find stock by symbol: [{}]", symbol);
		Stock stock = stockManager.findBySymbol(symbol);
		Assert.assertNotNull(stock);
		Assert.assertNotNull(stock.getStockId());
	}

}
