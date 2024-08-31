package com.ef.interview.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ef.interview.entity.Customer;
import com.ef.interview.repository.CustomerRepository;
import com.ef.interview.service.CustomerService;

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
