package com.bankingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bankingapp.models.*;


public interface AccountRepository extends JpaRepository<Account, Integer> {

//	@Query(value = "select * from accounts account where account.customer_id = ?1")
	public List<Account> findByCustomerid(int customer_id);
	
//	public List<Account> findBy
	
}
