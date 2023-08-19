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
	
	public List<Transaction> getTransactionsByCustId(int id){
		Account acc = accountrepo.findByCustomerId(id);
		List<Transaction> minus_transactions = transactionRepository.findAllByFromAccount(acc.getAccount_id());
		List<Transaction> plus_transactions = transactionRepository.findAllByToAccount(acc.getAccount_id());
		minus_transactions.addAll(plus_transactions); 
		return minus_transactions;
	}  
	public List<Transaction> getTransactionsFromDatabase() {
		return transactionRepository.findAll();
	}
	public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
