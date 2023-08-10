package com.bankingapp.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.bankingapp.repository.TestRepository;
import com.bankingapp.models.Customer;

@Service
public class ListService {
	
	@Autowired
	private TestRepository customerrepo;
	
	public List<Customer> getCustomerList(){
		return customerrepo.findAll();
	}
	
	public ResponseEntity<Customer> updateCustomer(Integer CustId, @RequestBody Customer downstreamCustomer) throws Exception {
		Customer updatedCustomer = customerrepo.findById(CustId).orElseThrow(()-> new IOException("Errror"));
		updatedCustomer.setCustomer_name(downstreamCustomer.getCustomer_name());
		updatedCustomer.setEmail(downstreamCustomer.getEmail());
		updatedCustomer.setContact(downstreamCustomer.getContact());
		
		return ResponseEntity.ok(updatedCustomer);
	}
	
	public void saveCustomer(Customer newCust) {
		customerrepo.save(newCust);
	}
	
}
