package net.zoostar.myweb.dao;

import java.util.List;

import net.zoostar.myweb.domain.Customer;

public interface CustomerDao extends MyWebCommonDao {
	public List<Customer> fetchCustomers();
}
