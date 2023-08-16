package com.bankingapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.exceptions.ResourceNotFoundException;
import com.bankingapp.models.Customer;
import com.bankingapp.service.ListService;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private ListService lister;

	@GetMapping("/admintest")
	private String check() {
		return "Admin";
	}
	
	@GetMapping("/admin")
	private List<Customer> listCustomers() {
		return lister.getCustomerList();
	}
	
	@PostMapping("/customer")
	private Customer savenew(@Valid @RequestBody Customer newCust) {
		return lister.saveCustomer(newCust);
	}
	
	@PutMapping("/customer")
	private ResponseEntity<Customer> update(@Valid @RequestBody Customer newCust) throws ResourceNotFoundException{
		return lister.updateCustomer(newCust.getCustomer_id(), newCust);
	}
	
}
