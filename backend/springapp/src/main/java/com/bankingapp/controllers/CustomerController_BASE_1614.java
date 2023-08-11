package com.bankingapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.models.Customer;
import com.bankingapp.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	//creating a get mapping that retrieves all the customers detail from the database   
	@GetMapping("/customer")  
	private List<Customer> getAllCustomers()   
	{  
	return customerService.getAllCustomers();  
	}  
	//creating a get mapping that retrieves the detail of a specific customer  
	@GetMapping("/customer/{customerid}")  
	private Customer getCustomers(@PathVariable("customerid") int customerid)   
	{  
	return customerService.getCustomersById(customerid);  
	}  	
	//creating a delete mapping that deletes a specified customer  
	@DeleteMapping("/customer/{customerid}")  
	private void deleteCustomer(@PathVariable("customerid") int customerid)   
	{  
	customerService.delete(customerid);  
	}  
	//creating post mapping that post the customer detail in the database  
	@PostMapping("/customers")  
	private int saveCustomer(@RequestBody Customer customers)   
	{  
	customerService.saveOrUpdate(customers);  
	return customers.getCustomer_id();  
	}  
	//creating put mapping that updates the book detail   
	@PutMapping("/customers")  
	private Customer update(@RequestBody Customer customers)   
	{  
	customerService.saveOrUpdate(customers);  
	return customers;  
	}  
}
