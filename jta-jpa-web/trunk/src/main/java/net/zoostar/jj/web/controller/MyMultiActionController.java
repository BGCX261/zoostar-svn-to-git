package net.zoostar.jj.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.zoostar.jj.app.model.Stock;
import net.zoostar.jj.app.service.StockManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

@Controller
public class MyMultiActionController extends MultiActionController {

	static final Logger L = LoggerFactory.getLogger(MyMultiActionController.class);
	
	@Autowired
	public void setStockManager(StockManager stockManager) {
		this.stockManager = stockManager;
	}
	public StockManager getStockManager() {
		return this.stockManager;
	}
	StockManager stockManager;
	
	@RequestMapping("/index.do")
	public ModelAndView doIndex(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("index");
	}
	
	@RequestMapping("/findStockBySymbol.do")
	public ModelAndView doFindStockBySymbol(HttpServletRequest request, HttpServletResponse response) {
		String symbol = request.getParameter("symbol");
		if(symbol == null) {
			L.warn("No symbol provided for finding stock!");
		}

		L.info("Finding stock by symbol: [{}]...", symbol);
		Stock stock = getStockManager().findBySymbol(symbol);
		if(stock == null) {
			L.warn("No stock found for symbol: [{}]", symbol);
		}
		
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.getModel().put("stock", stock);
		return modelAndView;
	}
	
	@RequestMapping("/addStock.do")
	public ModelAndView doAddStock(HttpServletRequest request, HttpServletResponse response) {
		String symbol = request.getParameter("symbol");
		String name = request.getParameter("name");
		
		Stock stock = new Stock(symbol, name);
		L.info("Adding Stock: [{}]:[{}]", symbol, name);
		getStockManager().addEntity(stock);
		
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.getModel().put("stock", stock);
		return modelAndView;
	}
}
