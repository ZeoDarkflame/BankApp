package com.bankingapp.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.bankingapp.service.*;
import com.bankingapp.exceptions.*;
import com.bankingapp.models.*;

@CrossOrigin(origins="localhost:3000")
@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired 
	private AccountService accountService;
	
	@GetMapping(path = "/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
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
	
	
	@PostMapping("/accounts")
    public Account createAccount(@Valid @RequestBody Account newAccount) {
        return accountService.createAccount(newAccount);
    }
    
    @PutMapping("/account/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable(value = "id")  Integer account_id, @Valid @RequestBody Account newAccount) throws ResourceNotFoundException 
    {
        return accountService.updateAccount(account_id, newAccount);
    }
    

    @DeleteMapping("/accounts/{id}")
    public Map<String,Boolean> deleteAccount(@PathVariable ("id") int accountId) throws ResourceNotFoundException
    {
    	return accountService.deleteAccount(accountId);
    }
}


