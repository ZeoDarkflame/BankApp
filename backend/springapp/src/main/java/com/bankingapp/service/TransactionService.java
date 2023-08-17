package com.bankingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapp.models.Transaction;
import com.bankingapp.repository.TransactionRepository;

@Service
public class TransactionService {
	@Autowired  
	TransactionRepository transactionRepository;  
	
	public Transaction getTransactionsById(int id){
		return transactionRepository.findById(id).get();  
	}  
	public List<Transaction> getTransactionsFromDatabase() {
		return transactionRepository.findAll();
	}
	public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
