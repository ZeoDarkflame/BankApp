package com.bankingapp.service;

import java.util.List;
import com.bankingapp.models.*;

public interface AccountServiceI {
    List<Account> getAccountFromDatabase();
    
    void addAccountInDatabase(Account account);

	Account getAccountById(int id);
}