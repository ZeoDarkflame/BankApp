package com.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankingapp.models.Customer;


public interface TestRepository extends JpaRepository<Customer, Integer> {

}
