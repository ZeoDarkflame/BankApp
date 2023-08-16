package com.bankingapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.bankingapp.exceptions.ResourceNotFoundException;
import com.bankingapp.models.*;
import com.bankingapp.repository.CustomerRepository;

import jakarta.validation.Valid;

@Service
public class CustomerService {

	@Autowired  
	CustomerRepository customerRepository;  
	
	public Customer getCustomersById(int id){
		return customerRepository.findById(id).get();  
	}  
	public List<Customer> getCustomersFromDatabase() {
		return customerRepository.findAll();
	}


	public Customer createCustomer(Customer cutomer) {
        return customerRepository.save(cutomer);
    }
	public ResponseEntity<Customer> updateCustomer(Integer customerId, @Valid @RequestBody Customer changedCustomer)
			throws ResourceNotFoundException {
		Customer updatedCustomer = customerRepository.findById(customerId)
						.orElseThrow(()-> new ResourceNotFoundException("Customer is not avaialble:"+ customerId));
		updatedCustomer.setCustomer_name(changedCustomer.getCustomer_name());
		updatedCustomer.setContact(changedCustomer.getContact());
		updatedCustomer.setCustomer_id(changedCustomer.getCustomer_id());
		updatedCustomer.setEmail(changedCustomer.getEmail());
		
	customerRepository.save(updatedCustomer);
	
		return ResponseEntity.ok(updatedCustomer);
	}
	public Map<String, Boolean> deleteCustomer(Integer customerId) throws ResourceNotFoundException {
		Customer updatedCustomer = customerRepository.findById(customerId)
				.orElseThrow(()-> new ResourceNotFoundException("Customer is not avaialble:"+ customerId));
		customerRepository.delete(updatedCustomer);
		Map<String,Boolean> response  = new HashMap<>();
		response.put("Customer has been Deleted", Boolean.TRUE);
		return response;
	}
	
	

}
