package com.bankingapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Customer getCustomersById(int id)   
	{  
	return customerRepository.findById(id).get();  
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
	
}
