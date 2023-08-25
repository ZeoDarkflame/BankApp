package com.bankingapp.controllers;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.exceptions.InsufficientBalanceException;
import com.bankingapp.exceptions.ResourceNotFoundException;
import com.bankingapp.models.Account;
import com.bankingapp.models.Transaction;
import com.bankingapp.repository.AccountRepository;
import com.bankingapp.repository.TransactionRepository;
import com.bankingapp.service.TransactionService;

import jakarta.validation.Valid;

@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
@RequestMapping("/customer")
public class TransactionController {
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private AccountRepository accountrepo;
	
	@Autowired
	private TransactionRepository transactionrepo;
	
	@GetMapping(path = "/transaction", produces = {MediaType.APPLICATION_JSON_VALUE})
	List<Transaction> allTransactions() {
        return transactionService.getTransactionsFromDatabase();
    }
	
	public class TransactionPair{	
		public List<Transaction> debits;
		public List<Transaction> credits;
		public List<Transaction> withdrawals;
		
		public TransactionPair(List<Transaction> debits, List<Transaction> credits,List<Transaction> withdrawals) {
			super();
			this.debits = debits;
			this.credits = credits;
			this.withdrawals=withdrawals;
		}
	}
	
	@GetMapping("/transaction/{accountid}") 
	public TransactionPair retrieveTransaction(@PathVariable("accountid") int id) throws ResourceNotFoundException  
	{  
		List<Transaction> debits = transactionService.getDebited(id);
		List<Transaction> credits = transactionService.getCredited(id);
		List<Transaction> withdrawals = transactionService.getWithdrawal(id);
		return new TransactionPair(debits,credits,withdrawals);
	}  
//	@GetMapping("/withdrawal/{accountid}") 
//	public List<Transaction> retrieveWithdrawal(@PathVariable("accountid") int id) throws ResourceNotFoundException  
//	{  
//		List<Transaction> withdrawals = transactionService.getWithdrawal(id);
//		
//		return withdrawals;
//	}
	


	@PostMapping("/transaction")
    public Transaction createTransaction(@Valid @RequestBody Transaction newTransaction) throws Exception {
		newTransaction.setTransaction_time(LocalDateTime.now());
		newTransaction.setTransaction_id(-1);
		Account from_account = accountrepo.findById(newTransaction.getFrom_account()).orElseThrow(() -> new ResourceNotFoundException("Your account is deactivated or non-existent"));
		Account to_account = accountrepo.findById(newTransaction.getTo_account()).orElseThrow(() -> new ResourceNotFoundException("Reciever account is deactivated or non-existent"));
		if(from_account.getBalance() < newTransaction.getAmount())
			throw new InsufficientBalanceException("Not Enough balance");
		
		if(to_account == null)
			throw new ResourceNotFoundException("This account id: ("+newTransaction.getTo_account()+") does not exist");
		
		
		Transaction completedTransaction=transactionService.createTransaction(newTransaction);
//		Account updatedToAccount = accountRepo.findById();
		float change_amount_to=newTransaction.getAmount();
		float change_amount_from=(newTransaction.getAmount())*-1;
		to_account.updateBalance(change_amount_to);
		from_account.updateBalance(change_amount_from);
		accountrepo.save(to_account);
		accountrepo.save(from_account);
        return completedTransaction;
    }
	@PostMapping("/withdrawal")
    public Transaction createWithdrawal(@Valid @RequestBody Transaction newTransaction) throws Exception {
		newTransaction.setTransaction_time(LocalDateTime.now());
		newTransaction.setTransaction_id(-1);
		Account from_account = accountrepo.findById(newTransaction.getFrom_account()).orElseThrow(() -> new ResourceNotFoundException("Your account is deactivated or non-existent"));
		Account to_account = accountrepo.findById(newTransaction.getTo_account()).orElseThrow(() -> new ResourceNotFoundException("Reciever account is deactivated or non-existent"));
		if(from_account.getBalance() < newTransaction.getAmount())
			throw new InsufficientBalanceException("Not Enough balance");
		
		if(to_account == null)
			throw new ResourceNotFoundException("This account id: ("+newTransaction.getTo_account()+") does not exist");
		
		
		Transaction completedTransaction=transactionService.createTransaction(newTransaction);
//		Account updatedToAccount = accountRepo.findById();
//		float change_amount_to=newTransaction.getAmount();
		float change_amount_from=(newTransaction.getAmount())*-1;
//		to_account.updateBalance(change_amount_to);
		from_account.updateBalance(change_amount_from);
//		accountrepo.save(to_account);
		accountrepo.save(from_account);
        return completedTransaction;
    }

	
}


