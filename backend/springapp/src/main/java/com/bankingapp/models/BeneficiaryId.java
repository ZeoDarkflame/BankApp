package com.bankingapp.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class BeneficiaryId implements Serializable{
	
	@Column(name="customer_id")
	private int customerId;
	
	@Column(name="beneficiary_user_id")
	private int toId;
	
	public BeneficiaryId() {
		super();
	}
	
	public BeneficiaryId(int custId,int toid) {
		super();
		this.customerId = custId;
		this.toId = toid;
	}
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getToId() {
		return toId;
	}
	public void setToId(int toId) {
		this.toId = toId;
	}
}