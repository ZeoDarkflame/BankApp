package com.bankingapp.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;


@Entity
//@IdClass(BeneficiaryId.class)
@Table(name="beneficiaries")
public class Beneficiary {
	
	@EmbeddedId
	private BeneficiaryId id;
//	@Column(name="customer_id")
//	private int customerId;
//	
//	@Column(name="beneficiary_user_id")
//	private int toId;
	
	@Column(name="ben_name")
	private String name;
	
	@Column(name="ben_nickname")
	private String nickname;

//	public int getCustomerId() {
//		return customerId;
//	}
//
//	public void setCustomerId(int customerId) {
//		this.customerId = customerId;
//	}
//
//	public int getToId() {
//		return toId;
//	}
//
//	public void setToId(int toId) {
//		this.toId = toId;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public BeneficiaryId getId() {
		return id;
	}

	public void setId(BeneficiaryId id) {
		this.id = id;
	}
}
