package com.bankingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapp.models.*;
import com.bankingapp.repository.*;


@Service
public class AccountService implements AccountServiceI {
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Override 
	public List<Account> getAccountFromDatabase(){
		return accountRepo.findAll();
	}
	
	@Override
	public void addAccountInDatabase(Account account) {
		accountRepo.save(account);
	}
}
