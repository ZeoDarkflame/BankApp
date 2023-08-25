package com.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankingapp.models.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {

		public Customer findByEmail(String userName);
//		public Customer findByCustomerName(int cid);
}
