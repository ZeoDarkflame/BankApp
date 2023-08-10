package com.bankingapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.service.AccountService;
import com.bankingapp.models.Account;

@RestController
@RequestMapping("/Account")
public class AccountController {
	
	@Autowired 
	private AccountService accountService;
	
	@GetMapping("/readall")
	public List<Account> read() {
		return accountService.getAccountFromDatabase();
	}
	
	@PostMapping("/add")
	public String add() {
		Account account = new Account();
		accountService.addAccountInDatabase(account);
		return "Account added successfully";
	}
	
	
}


