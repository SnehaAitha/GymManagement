package com.management.gym.service;

import java.util.List;
import java.util.Optional;

import com.management.gym.entity.Customer;

public interface CustomerService {
	
	List<Customer> saveCustomers(List<Customer> customers);
	Optional<Customer> fetchCustomer(Integer id);
	void saveCustomer(Customer customer);

}
