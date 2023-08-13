package com.bankingapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bankingapp.service.AccountService;
import com.bankingapp.exceptions.ResourceNotFoundException;
import com.bankingapp.models.Account;
import com.bankingapp.models.Customer;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired 
	private AccountService accountService;
	
	@GetMapping("/readall")
	public List<Account> read() {
		return accountService.getAccountFromDatabase();
	}
	
	@GetMapping("/account/{accountid}") 
	public Account retriveUser(@PathVariable("accountid") int id) throws ResourceNotFoundException  
	{  
		Account account = accountService.getAccountById(id);  
		if(account == null)  
			throw new ResourceNotFoundException("Id not  available:"+id);  
		return account;  
	}  
	
	@PostMapping("/add")
	public String add() {
		Account account = new Account();
		accountService.addAccountInDatabase(account);
		return "Account added successfully";
	}
	
	
}


