package com.ef.interview.service;

import java.util.List;
import java.util.Optional;
import com.ef.interview.entity.Customer;

public interface CustomerService {
	
	List<Customer> saveCustomers(List<Customer> customers);
	Optional<Customer> fetchCustomer(Integer id);
	void saveCustomer(Customer customer);

}
