package com.bankingapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.models.Customer;
import com.bankingapp.service.ListService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private ListService lister;

	@GetMapping("/admin")
	private String check() {
		return "Admin";
	}
	
	@PostMapping("/customer")
	private int savenew(@RequestBody Customer newCust) {
		lister.saveCustomer(newCust);
		return newCust.getCustomer_id();
	}
	
	@PutMapping("/customer")
	private ResponseEntity<Customer> update(@RequestBody Customer newCust) throws Exception{
		return lister.updateCustomer(newCust.getCustomer_id(), newCust);
	}
	
}
