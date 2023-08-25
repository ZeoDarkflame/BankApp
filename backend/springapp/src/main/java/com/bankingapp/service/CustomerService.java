package com.bankingapp.service;

import java.time.Duration;
import java.time.LocalDateTime;
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
	
	public Customer getCustomersByMail(String email){
		return customerRepository.findByEmail(email);  
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

	public void LoginAttempt(String username, boolean attemptStatus) {
//		System.out.println("Successfully landed " + attemptStatus);
		Customer updatedCustomer = customerRepository.findByEmail(username);
		if(attemptStatus) {
			updatedCustomer.setLoginAttempt(0);
			LocalDateTime currentTime = LocalDateTime.now();
			updatedCustomer.setLastLogin(currentTime);
//			System.out.println(updatedCustomer.getLastLogin());
//			System.out.println(updatedCustomer.getLoginAttempt());
		}else {
			if(LastActiveSafe(updatedCustomer)) {
				updatedCustomer.setLoginAttempt(1);
			}else {
				updatedCustomer.setLoginAttempt(updatedCustomer.getLoginAttempt() + 1);
				if(updatedCustomer.getLoginAttempt() == 3) {
					blockCustomer(updatedCustomer);
				}
			}
//			System.out.println(updatedCustomer.getLastLogin());
//			System.out.println(updatedCustomer.getLoginAttempt());
//			System.out.println(updatedCustomer.getActiveStatus());
		}
		customerRepository.save(updatedCustomer);
	}

	private void blockCustomer(Customer updatedCustomer) {
		updatedCustomer.setActiveStatus(false);
	}

	private boolean LastActiveSafe(Customer updatedCustomer) {
		return Duration.between(updatedCustomer.getLastLogin(), LocalDateTime.now()).toHours() > 24;
	}

	public boolean CheckActiveCustomer(String userName) {
		Customer customer = customerRepository.findByEmail(userName);
		if(customer.getActiveStatus()) {
			return true;
		}else {
			if(LastActiveSafe(customer)) {
				return true;
			}
		}
		return false;
	}
	
	

}
