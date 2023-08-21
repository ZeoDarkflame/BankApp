package com.bankingapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

import com.bankingapp.exceptions.ResourceNotFoundException;
import com.bankingapp.models.*;
import com.bankingapp.repository.*;


@Service
public class AccountService implements AccountServiceI {
	
	@Autowired
	AccountRepository accountRepo;
	
	public List<Account> getAccountFromDatabase(){
		return accountRepo.findAll();
	}
	
	public Account createAccount(Account newAccount) {
		return accountRepo.save(newAccount);
	}
	
	public Account getAccountById(int id) throws ResourceNotFoundException  
	{  
		return accountRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Account is not available:" + id));  
	}
	
	public Account getAccountByCustomerId(int customer_id){
		return accountRepo.findByCustomerId(customer_id);
	}
	
	public ResponseEntity<Account> updateAccount(Integer account_Id, @Valid @RequestBody Account changedAccount )
	throws ResourceNotFoundException {
		Account updatedAccount = accountRepo.findById(account_Id).orElseThrow(()-> new ResourceNotFoundException("Account is not available:" + account_Id));
		
		updatedAccount.setAccount_id(changedAccount.getAccount_id());
		updatedAccount.setBalance(changedAccount.getBalance());
		updatedAccount.setCustomer_id(changedAccount.getCustomer_id());
		updatedAccount.setTransactionPassword(changedAccount.getTransactionPassword());
		updatedAccount.setUsername(changedAccount.getUsername());
		
		accountRepo.save(updatedAccount);
		return ResponseEntity.ok(updatedAccount);
	}
	
	public Map<String, Boolean> deleteAccount(Integer accountId) throws ResourceNotFoundException {
		Account updatedAccount = accountRepo.findById(accountId)
				.orElseThrow(()-> new ResourceNotFoundException("Account is not avaialble:"+ accountId));
		accountRepo.delete(updatedAccount);
		Map<String,Boolean> response  = new HashMap<>();
		response.put("Account has been Deleted", Boolean.TRUE);
		return response;
	}

}
