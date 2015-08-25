package net.zoostar.metrade.app.service.jpa;

import junit.framework.Assert;
import net.zoostar.metrade.app.model.Stock;
import net.zoostar.metrade.app.service.StockManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:datasource.xml")
public class StockManagerImplTest {

	static final Logger log = LoggerFactory.getLogger(StockManagerImplTest.class);
	
	static final String SYMBOL = "INTX";
	static final String NAME = "Intex";
	
	@Autowired
	private StockManager stockManager;

	@Test
	public void testStockManager() {
		System.out.println();
		log.info("Testing stockManager is not null...");
		Assert.assertNotNull(stockManager);
		log.info("Stock manager [{}]", stockManager);
	}
	
	@Test
	public void testRemove() {
		System.out.println();
		log.info("Testing Stock Removal...");
		Stock stock = stockManager.findBySymbol(SYMBOL);
		Assert.assertNotNull(stock);
		Assert.assertNotNull(stock.getStockId());
		log.info("Found [{}] with Id [{}]", stock, stock.getStockId());
		
		stockManager.remove(stock.getStockId());
		log.info("Found [{}] with Id [{}]", stock, stock.getStockId());		
	}
	
	@Test
	public void testAddStock() {
		System.out.println();
		log.info("Testing new stock insertion...");
		Stock stock = new Stock(SYMBOL, NAME);
		Assert.assertNull(stock.getStockId());
		stockManager.addStock(stock);
		Assert.assertNotNull(stock.getStockId());
		Assert.assertEquals(SYMBOL, stock.getSymbol());
		Assert.assertEquals(NAME, stock.getName());
		log.info("Inserted new [{}]", stock);
		log.info("Inserted new Stock with id [{}]", stock.getStockId());
	}

	@Test
	public void testFindBySymbol() {
		System.out.println();
		log.info("Testing FindBySymbol...");
		Stock stock = stockManager.findBySymbol(SYMBOL);
		Assert.assertNotNull(stock.getStockId());
		Assert.assertEquals(SYMBOL, stock.getSymbol());
		Assert.assertEquals(NAME, stock.getName());
		log.info("Inserted [{}] with Id [{}]", stock, stock.getStockId());
	}
}
