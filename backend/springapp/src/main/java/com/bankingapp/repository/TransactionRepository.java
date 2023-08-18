package com.bankingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankingapp.models.*;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
	
	public List<Transaction> findByFromAccount(int id);
	public List<Transaction> findByToAccount(int id);
}

