package com.bankingapp.service;

import java.util.List;

import com.bankingapp.exceptions.ResourceNotFoundException;
import com.bankingapp.models.*;

public interface AccountServiceI {
    public List<Account> getAccountFromDatabase();
   
    public Account createAccount(Account account);
    
//    public Account getAccountById(int id) throws ResourceNotFoundException  ;
//    
//    public ResponseEntity<Account> updateAccount(Integer account_Id, @Valid @RequestBody Account changedAccount )
//    		throws ResourceNotFoundException ;
//    
//    public Map<String, Boolean> deleteAccount(Integer accountId) throws ResourceNotFoundException;

//    void addAccountInDatabase(Account account);

	Account getAccountById(int id) throws ResourceNotFoundException;
}