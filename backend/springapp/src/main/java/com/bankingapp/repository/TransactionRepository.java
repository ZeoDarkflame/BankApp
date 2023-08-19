package com.bankingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankingapp.models.*;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
	
<<<<<<< HEAD
	public List<Transaction> findAllByFromAccount(int id);
	public List<Transaction> findAllByToAccount(int id);
=======
	public List<Transaction> findByFromAccount(int id);
	public List<Transaction> findByToAccount(int id);
>>>>>>> 1b477f2a8e2dc7c1686b23cd473590bc35435587
}

