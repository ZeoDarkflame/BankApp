package com.bankingapp.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.exceptions.ResourceNotFoundException;
import com.bankingapp.models.Beneficiary;
import com.bankingapp.models.Customer;
import com.bankingapp.repository.BeneficiaryRepository;
import com.bankingapp.repository.CustomerRepository;
import com.bankingapp.service.BeneficiaryService;
import com.bankingapp.service.CustomerService;

import jakarta.validation.Valid;

@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
@RequestMapping("/customer")
public class BeneficiaryController {
	
	@Autowired
	private BeneficiaryService beneficiaryService;
	
	@Autowired
	private CustomerService customerService;
	@Autowired  
	private CustomerRepository customerRepo;
	
	@GetMapping("/beneficiary/{customerid}") 
	public List<Beneficiary> retriveBeneficiary(@PathVariable("customerid") int id) throws ResourceNotFoundException  
	{  
		Customer customer= customerService.getCustomersById(id);  
		if(customer==null) 
		//runtime exception  
		throw new ResourceNotFoundException("Id not  available:"+id);
		
		return beneficiaryService.getBeneficiary(id);  
	}
	@PostMapping("/beneficiary")
    public Beneficiary createBeneficiary(@Valid @RequestBody Beneficiary newBeneficiary) throws Exception {
		Customer beneficiary_user_id=customerRepo.findById(newBeneficiary.getId().getToId()).orElseThrow(() -> new ResourceNotFoundException("BeneficiaryId  is deactivated or non-existent"));
		
		Beneficiary completedBeneficiary= beneficiaryService.createBeneficiary(newBeneficiary);
        return completedBeneficiary;
    }
	
	 	@DeleteMapping("/beneficiary/{cid}/{bid}")
	    public Map<String,Boolean> deleteBeneficiary(@PathVariable ("cid") int customer_id,@PathVariable ("bid") int beneficiary_user_id) throws ResourceNotFoundException
	    {
	    	return beneficiaryService.deleteBeneficiary(customer_id,beneficiary_user_id);
	    }
	 	@PutMapping("/beneficiary/{cid}/{bid}")
	    public ResponseEntity<Beneficiary> updateBeneficiary(@PathVariable(value = "cid")  Integer customer_id,@PathVariable(value = "bid")  Integer beneficiary_user_id, @Valid @RequestBody Beneficiary newBeneficiary) throws ResourceNotFoundException 
	    {
	        return beneficiaryService.updateBeneficiary(customer_id,beneficiary_user_id, newBeneficiary);
	    }

}





	
	
	
	
	

	


	
	

	



