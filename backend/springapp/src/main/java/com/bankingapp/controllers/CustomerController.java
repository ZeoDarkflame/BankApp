package com.bankingapp.controllers;


import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.exceptions.IncorrectCredentialsException;
import com.bankingapp.exceptions.ResourceNotFoundException;
import com.bankingapp.models.Account;
import com.bankingapp.models.AuthRequest;
import com.bankingapp.models.Customer;
import com.bankingapp.repository.AccountRepository;
import com.bankingapp.repository.CustomerRepository;
import com.bankingapp.service.CustomerService;

import jakarta.validation.Valid;

@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerRepository customerrepo;
	
	@Autowired
	private AccountRepository accountrepo;
	
	//creating a get mapping that retrieves all the customers detail from the database   
	@GetMapping(path = "/customer", produces = {MediaType.APPLICATION_JSON_VALUE})
    List<Customer> products() {
        return customerService.getCustomersFromDatabase();
    }
	
	//creating a get mapping that retrieves the detail of a specific customer  
	@GetMapping("/customer/{customerid}") 
	public Customer retriveUser(@PathVariable("customerid") int id) throws ResourceNotFoundException  
	{  
	Customer customer= customerService.getCustomersById(id);  
	if(customer==null)  
	//runtime exception  
	throw new ResourceNotFoundException("Id not  available:"+id);  
	return customer;  
	}  
//	Customer findByCustomerIdFromDBWithException(@PathVariable int id) throws ResourceNotFoundException
//	{	Customer customer = customerService.getCustomerById(id)
//    		.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + id));
//       System.out.println(id);
//    return customer;	
//	}

	
	
	
	// lets go
	@PostMapping("/customers")
    public Customer createCustomer(@Valid @RequestBody Customer newCustomer) throws Exception {
		newCustomer.setActiveStatus(true);
        return customerService.createCustomer(newCustomer);
    }
    
    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateEmployee(@PathVariable(value = "id")  Integer customer_id, @Valid @RequestBody Customer newCustomer) throws ResourceNotFoundException 
    {
        return customerService.updateCustomer(customer_id, newCustomer);
    }
    

    @DeleteMapping("/customers/{id}")
    public Map<String,Boolean> deleteCustomer(@PathVariable ("id") int customerId) throws ResourceNotFoundException
    {
    	return customerService.deleteCustomer(customerId);
    }
    
    @PostMapping("/auth")
    public Customer auth(@Valid @RequestBody AuthRequest authreq) throws IncorrectCredentialsException {
    	Customer cust = customerrepo.findByEmail(authreq.getUserName());
    	if(cust == null)
    		throw new IncorrectCredentialsException("Invalid Username and Password");
    	if(cust.getPassword().equals( authreq.getPassword())){
    		System.out.println("Successful Login");
    		return cust;
    	}
    	return null;	
    }
    
    @PostMapping("/getaccount")
    public Optional<Account> getAcc(@Valid @RequestBody Customer cust){
    	return accountrepo.findById(cust.getCustomer_id());
    }
    
    @GetMapping("/bymail/{email}") 
	public Customer getByMail(@PathVariable("email") String email) throws ResourceNotFoundException  
	{  
		Customer customer= customerService.getCustomersByMail(email);	
		if(customer == null)
			throw new ResourceNotFoundException("Invalid email");
		return customer;  
	}  
}
