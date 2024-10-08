package com.management.gym.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.gym.entity.Customer;
import com.management.gym.repository.CustomerRepository;
import com.management.gym.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerRepository customerRepo;

	@Override
	public List<Customer> saveCustomers(List<Customer> customers) {
		return customerRepo.saveAll(customers);
	}
	
	@Override
	public Optional<Customer> fetchCustomer(Integer id) {
		return customerRepo.findById(id);
	}

	@Override
	public void saveCustomer(Customer customer) {
		customerRepo.save(customer);	
	}

}
