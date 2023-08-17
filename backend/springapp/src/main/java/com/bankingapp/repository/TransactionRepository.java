package com.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankingapp.models.*;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
	
}

