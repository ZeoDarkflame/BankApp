package com.bankingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bankingapp.models.*;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
	@Query("SELECT t FROM Transaction t where t.fromAccount= ?1 AND t.toAccount <> ?1")
	public List<Transaction> customfindAllByFromAccount(int id);
	@Query("SELECT t FROM Transaction t where t.toAccount= ?1 AND t.fromAccount <> ?1")
	public List<Transaction> customfindAllByToAccount(int id);
	@Query("SELECT t FROM Transaction t where t.fromAccount= ?1 AND t.toAccount = ?1")
	public List<Transaction> findByTransaction(int id);
	
}

