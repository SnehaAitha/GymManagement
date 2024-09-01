package com.management.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.gym.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer>{

}
