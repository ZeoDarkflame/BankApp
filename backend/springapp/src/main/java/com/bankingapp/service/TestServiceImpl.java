package com.bankingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapp.models.Customer;
import com.bankingapp.repository.CustomerRepository;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private CustomerRepository testRepo;
	
	@Override
	public List<Customer> getCustomerFromDatabase(){
		return testRepo.findAll();
	}
}
