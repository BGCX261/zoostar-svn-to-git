package net.zoostar.myweb.service;

import java.util.ArrayList;
import java.util.List;

import net.zoostar.myweb.domain.Customer;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=true)
public class CustomerManagerImpl implements CustomerManager {

	protected List<Customer> instantiateList() {
		return new ArrayList<Customer>();
	}

	@Cacheable("customers")
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
			log.info("Uncached Customers found: {}", customers.size());
		log.debug("CustomerManagerImpl.listCustomers.end");
		
		return customers;
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW)
	public void updateCustomer(Customer customer) {
		throw new UnsupportedOperationException();
	}
}
