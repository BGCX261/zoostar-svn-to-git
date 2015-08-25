package net.zoostar.myweb.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.zoostar.myweb.domain.Customer;
import net.zoostar.myweb.service.CustomerManager;

import org.apache.commons.lang.StringUtils;
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
		String action = request.getServletPath();
		try {
			if(StringUtils.isBlank(action)) {
				log.warn("request.getServletPath() is blank!");
			} else if(action.contains("listCustomers")) {
				List<Customer> customers = customerManager.listCustomers();
				log.info("Customers found: {}", customers.size());
				model.put("customers", customers);
			} else if(action.contains("updateCustomer")) {
				Customer customer = new Customer();
				customerManager.updateCustomer(customer);
				log.info("Customers Updated");
				model.put("customer", customer);
			} else {
				log.warn("Unmapped Action: {}!", action);
			}
		} catch(RuntimeException e) {
			log.error(e.getMessage(), e.fillInStackTrace());
		}
	}
	protected String getViewName(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {

		String view = "error";
		String action = request.getServletPath();
		if("listCustomers".contains(action))
			view = "listcustomers";
		else if("updateCustomer".contains(action))
			view = "updateCustomer";
		log.info("Returning view: {}", view);
		return view;
	}

	public final ModelAndView updateCustomer(HttpServletRequest request, HttpServletResponse response) {
		return super.handleMyWebRequest(request, response);
	}
}
