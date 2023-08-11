package com.bankingapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapp.exceptions.ResourceNotFoundException;
import com.bankingapp.models.*;
import com.bankingapp.repository.TestRepository;

@Service
public class CustomerService {

	@Autowired  
	TestRepository customerRepository;  
	//getting all customer record by using the method findaAll() of CrudRepository  
	public List<Customer> getAllCustomers()   
	{  
	List<Customer> customers = new ArrayList<Customer>();  
	customerRepository.findAll().forEach(customers1 -> customers.add(customers1));  
	return customers;  
	}  
	//getting a specific record by using the method findById() of CrudRepository  
	public Customer getCustomersById(int id) throws ResourceNotFoundException  
	{  
		Customer acqcust = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found id: " + id));  
		return acqcust;
	}  
	//saving a specific record by using the method save() of CrudRepository  
	public void saveOrUpdate(Customer customers)   
	{  
	customerRepository.save(customers);  
	}
	//deleting a specific record by using the method deleteById() of CrudRepository  
	public void delete(int id)   
	{  
	customerRepository.deleteById(id);  
	}  
	//updating a record  
	public void update(Customer customers, int customerid)   
	{  
	customerRepository.save(customers);  
	}
	public Customer createCustomer(Customer cutomer) {
        return customerRepository.save(cutomer);
    }
	

}
