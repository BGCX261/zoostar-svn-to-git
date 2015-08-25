package net.zoostar.myweb.service;

import java.util.List;

import net.zoostar.myweb.domain.Customer;

public interface CustomerManager extends MyWebCommonManager {
	public List<Customer> listCustomers();
	public void updateCustomer(Customer customer);
}
