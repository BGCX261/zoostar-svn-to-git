package net.zoostar.myweb.service;

import java.util.ArrayList;
import java.util.List;

import net.zoostar.myweb.domain.Customer;

import org.springframework.stereotype.Service;

@Service
public class CustomerManagerImpl implements CustomerManager {

	public List<Customer> listCustomers() { 
		log.debug("CustomerManagerImpl.listCustomers.begin");
		List<Customer> customers = instantiateList();

		Customer customer = new Customer();
		customer.setCustomerNumber(1);
		customer.setCustomerName("Foo");
		customers.add(customer);
		
		customer = new Customer();
		customer.setCustomerNumber(2);
		customer.setCustomerName("Bar");
		customers.add(customer);
		
		if(customers != null)
			log.info("Customers found: " + customers.size());
		log.debug("CustomerManagerImpl.listCustomers.end");
		return customers;
	}

	protected List<Customer> instantiateList() {
		return new ArrayList<Customer>();
	}
}
