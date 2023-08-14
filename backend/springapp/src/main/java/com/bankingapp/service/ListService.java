package com.bankingapp.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.bankingapp.repository.CustomerRepository;
import com.bankingapp.exceptions.ResourceNotFoundException;
import com.bankingapp.models.Customer;

@Service
public class ListService {
	
	@Autowired
	private CustomerRepository customerrepo;
	
	public List<Customer> getCustomerList(){
		return customerrepo.findAll();
	}
	
	public ResponseEntity<Customer> updateCustomer(Integer CustId, @RequestBody Customer downstreamCustomer) throws ResourceNotFoundException {
		Customer updatedCustomer = customerrepo.findById(CustId).orElseThrow(()-> new ResourceNotFoundException("Resource not found Id: "+ CustId));
		updatedCustomer.setCustomer_name(downstreamCustomer.getCustomer_name());
		updatedCustomer.setEmail(downstreamCustomer.getEmail());
		updatedCustomer.setContact(downstreamCustomer.getContact());
		return ResponseEntity.ok(updatedCustomer);
	}
	
	public Customer saveCustomer(Customer newCust) {
		return customerrepo.save(newCust);
	}
	
}
