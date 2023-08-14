package com.bankingapp.service;

import java.util.List;

import com.bankingapp.exceptions.ResourceNotFoundException;
import com.bankingapp.models.*;

public interface AccountServiceI {
    public List<Account> getAccountFromDatabase();
   
    public Account createAccount(Account account);

	Account getAccountById(int id) throws ResourceNotFoundException;
}