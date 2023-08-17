package com.bankingapp.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.exceptions.ResourceNotFoundException;
import com.bankingapp.models.Transaction;
import com.bankingapp.repository.TransactionRepository;
import com.bankingapp.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer")
public class TransactionController {
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private TransactionRepository transactionrepo;
	
	@GetMapping(path = "/transaction", produces = {MediaType.APPLICATION_JSON_VALUE})
	List<Transaction> allTransactions() {
        return transactionService.getTransactionsFromDatabase();
    }
	
	@GetMapping("/transaction/{accountid}") 
	public Transaction retriveTransaction(@PathVariable("accountid") int id) throws ResourceNotFoundException  
	{  
		Transaction transaction= transactionService.getTransactionsById(id);  
	if(transaction==null)  
	//runtime exception  
	throw new ResourceNotFoundException("transactionId not  available:"+id);  
	return transaction;  
	}  
	


	@PostMapping("/transaction")
    public Transaction createTransaction(@Valid @RequestBody Transaction newTransaction) throws Exception {
        return transactionService.createTransaction(newTransaction);
    }

	
}


