package com.bankingapp.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;



@Entity
@Table(name="transactions")
public class Transaction {

	
	@Id
	@Column(name="transaction_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int transactionId;
	@Column(name="from_account")
	private int fromAccount;
	@Column(name="to_account")
	private int toAccount;
	@Column(name="date")
	private LocalDateTime transactionTime;
	
	@Min(1)
	@Max(Integer.MAX_VALUE)
	private int amount;
	
	@Column(name="transaction_type")
	private int transactionType;
	
	

	
	public Transaction(int transactionId, int fromAccount, int toAccount, LocalDateTime transactionTime, int amount, int transaction_type) {
		// TODO Auto-generated constructor stub
		this.transactionId = transactionId;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.transactionTime = transactionTime;
		this.amount = amount;
		this.transactionType = transaction_type;
	}

	public int getTransaction_id() {
		return transactionId;
	}
	
	public void setTransaction_id(int tid) {
		this.transactionId = tid;
	}

	public int getFrom_account() {
		return fromAccount;
	}


	public void setFrom_account(int fromAccount) {
		this.fromAccount = fromAccount;
	}

	public int getTo_account() {
		return toAccount;
	}


	public void setTo_account(int toAccount) {
		this.toAccount = toAccount;
	}

	public LocalDateTime getTransaction_time() {
		return transactionTime;
	}

	public void setTransaction_time(LocalDateTime transactionTime) {
		this.transactionTime = transactionTime;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public int getTType() {
		return this.transactionType;
	}

//	public String gettType() {
//		if(this.transactionType ==3)
//			return "Withdrawal";
//		else if(this.transactionType == 2)
//			return "IMPS";
//		else if (this.transactionType == 1)
//			return "RTGS";
//		else
//			return "NEFT";
//	}

	public void settType(int transactionType) {
		this.transactionType = transactionType;
	}
}
