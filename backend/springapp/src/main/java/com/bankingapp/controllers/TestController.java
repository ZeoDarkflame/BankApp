package com.bankingapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.service.TestService;
import com.bankingapp.models.Customer;



@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private TestService testService;

	
	@GetMapping("/message")
	public String test() {
		return "Test Message";
	}
	
	@GetMapping("/message2")
	public String test2() {
		return "Another Test";
	}
	
	@GetMapping("/cdata")
	List<Customer> cdata(){
		return testService.getCustomerFromDatabase();
	}
}
