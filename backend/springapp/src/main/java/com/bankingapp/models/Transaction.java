package com.bankingapp.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name="transactions")
public class Transaction {

	@Id
	@Column(name="transaction_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int transactionId;
	@Column(name="fromAccount")
	private int fromAccount;
	@Column(name="toAccount")
	private int toAccount;
	@Column(name="transactionTime")
	private LocalDateTime transactionTime;
	private int amount;
	private TransactionType transactionType;
	
	

	
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

	public TransactionType gettType() {
		return transactionType;
	}

	public void settType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
}
