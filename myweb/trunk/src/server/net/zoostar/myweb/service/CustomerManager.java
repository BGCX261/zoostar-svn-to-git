package net.zoostar.myweb.service;

import java.util.List;

import net.zoostar.myweb.domain.Customer;

public interface CustomerManager extends MyWebManager {
	public List<Customer> listCustomers();
}
