package com.bankingapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bankingapp.models.Beneficiary;
import com.bankingapp.models.BeneficiaryId;


public interface BeneficiaryRepository extends JpaRepository<Beneficiary,BeneficiaryId>{
	public List<Beneficiary> findAllByIdCustomerId(int id);
	
	
//	@Query("SELECT b FROM Beneficiary b where b.customerId= ?1 AND t.toId = ?2")
	public Optional<Beneficiary> findById(Beneficiary id);
}
