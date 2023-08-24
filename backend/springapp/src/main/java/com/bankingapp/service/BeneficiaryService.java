package com.bankingapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.bankingapp.exceptions.ResourceNotFoundException;
import com.bankingapp.models.Beneficiary;
import com.bankingapp.models.BeneficiaryId;
import com.bankingapp.models.Customer;
import com.bankingapp.models.Transaction;
import com.bankingapp.repository.AccountRepository;
import com.bankingapp.repository.BeneficiaryRepository;
import com.bankingapp.repository.TransactionRepository;

import jakarta.validation.Valid;

@Service
public class BeneficiaryService {
	@Autowired  
	BeneficiaryRepository beneficiaryRepository;
	
	public List<Beneficiary> getBeneficiary(int id){
		return beneficiaryRepository.findAllByIdCustomerId(id);
	}
	public Beneficiary createBeneficiary(Beneficiary beneficiary) throws Exception {
		
		
        return beneficiaryRepository.save(beneficiary);
    }
	public Map<String, Boolean> deleteBeneficiary(Integer customerId,Integer toId) throws ResourceNotFoundException {
		
		BeneficiaryId key=new BeneficiaryId(customerId,toId);
		Beneficiary beneficiary=beneficiaryRepository.findById(key)
				.orElseThrow(()-> new ResourceNotFoundException("Beneficiary is not avaialble"));
		beneficiaryRepository.delete(beneficiary);
		Map<String,Boolean> response  = new HashMap<>();
		response.put("Beneficiary has been Deleted", Boolean.TRUE);
		return response;
	}
	public ResponseEntity<Beneficiary> updateBeneficiary(Integer customerId,Integer toId, @Valid @RequestBody Beneficiary changedBeneficiary)
			throws ResourceNotFoundException {
		BeneficiaryId key=new BeneficiaryId(customerId,toId);
		Beneficiary updatedBeneficiary = beneficiaryRepository.findById(key)
						.orElseThrow(()-> new ResourceNotFoundException("Beneficiary is not avaialble"));
		updatedBeneficiary.setNickname(changedBeneficiary.getNickname());
		updatedBeneficiary.setName(changedBeneficiary.getName());
		
		
		beneficiaryRepository.save(updatedBeneficiary);
	
		return ResponseEntity.ok(updatedBeneficiary);
	}
	
}


