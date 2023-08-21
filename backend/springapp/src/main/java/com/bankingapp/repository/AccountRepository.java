package com.bankingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bankingapp.models.*;


public interface AccountRepository extends JpaRepository<Account, Integer> {

		public List<Account> findAllByCustomerId(int id);
	
}
