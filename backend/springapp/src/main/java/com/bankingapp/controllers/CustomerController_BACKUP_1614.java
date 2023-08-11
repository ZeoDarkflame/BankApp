package com.bankingapp.controllers;


import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.exceptions.ResourceNotFoundException;
import com.bankingapp.models.Customer;
import com.bankingapp.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	//creating a get mapping that retrieves all the customers detail from the database   
	@GetMapping(path = "/products", produces = {MediaType.APPLICATION_JSON_VALUE})
    List<Customer> products() {
        return customerService.getCustomersFromDatabase();
    }
	
	//creating a get mapping that retrieves the detail of a specific customer  
<<<<<<< HEAD
	@GetMapping("/customer/{customerid}")  
	private Customer getCustomers(@PathVariable("customerid") int customerid) throws ResourceNotFoundException
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
=======
	@GetMapping("/customer/{customerid}") 
	Customer findByCustomerIdFromDBWithException(@PathVariable int id) throws ResourceNotFoundException
	{	Customer customer = customerService.getCustomersById(id)
    		.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + id));
       System.out.println(id);
    return customer;	
	}

	
	
	
	// lets go
	@PostMapping("/customers")
    public Customer createCustomer(@Valid @RequestBody Customer newCustomer) {
        return customerService.createCustomer(newCustomer);
    }
    
    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateEmployee(@PathVariable(value = "id")  Integer customer_id, @Valid @RequestBody Customer newCustomer) throws ResourceNotFoundException 
    {
        return customerService.updateCustomer(customer_id, newCustomer);
    }
    

    @DeleteMapping("/customers/{id}")
    public Map<String,Boolean> deleteCustomer(@PathVariable (value="id") Integer customerId) throws ResourceNotFoundException
    {
    	return customerService.deleteCustomer(customerId);
    }
>>>>>>> edda4d4aa4989979d7c13685b36174af3aa55f26
}
