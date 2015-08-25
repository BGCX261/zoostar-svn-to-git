package net.zoostar.myweb.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.zoostar.myweb.domain.Customer;
import net.zoostar.myweb.service.CustomerManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

public class CustomerController extends MyWebAbstractController {
	
	@Autowired
	private CustomerManager customerManager;
	
	public final ModelAndView listCustomers(HttpServletRequest request, HttpServletResponse response) {
		return super.handleMyWebRequest(request, response);
	}
	protected void processModel(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		List<Customer> customers = customerManager.listCustomers();
		model.put("customers", customers);
	}
	protected String getViewName(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		return "listcustomers";
	}
}
