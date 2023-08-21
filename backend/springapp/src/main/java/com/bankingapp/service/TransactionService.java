package com.bankingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapp.models.Account;
import com.bankingapp.models.Transaction;
import com.bankingapp.repository.AccountRepository;
import com.bankingapp.repository.TransactionRepository;

@Service
public class TransactionService {
	@Autowired  
	TransactionRepository transactionRepository;  
	
	@Autowired
	AccountRepository accountrepo;
	 
	
	public List<Transaction> getDebited(int id){
		return transactionRepository.findAllByFromAccount(id);
	}
	
	public List<Transaction> getCredited(int id){
		return transactionRepository.findAllByToAccount(id);
	}
	
	public List<Transaction> getTransactionsByAccountId(int id){
		List<Transaction> transactions = getDebited(id);
		transactions.addAll(getCredited(id));
		return transactions;
	} 
	
	public List<Transaction> getTransactionsFromDatabase() {
		return transactionRepository.findAll();
	}
	
	public Transaction createTransaction(Transaction transaction) throws Exception {
		//deduct amount from from_account
		
		//add amount to to_account
		
        return transactionRepository.save(transaction);
    }
}
