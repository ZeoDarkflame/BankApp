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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int transaction_id;
	
	@Column(name  = "from_account")
	private int fromAccount;
	
	@Column(name = "to_account")
	private int toAccount;
	
	private LocalDateTime transaction_time;
	private int amount;
	private TransactionType tType;
	
	

	
	public int getTransaction_id() {
		return transaction_id;
	}
	
	public void setTransaction_id(int tid) {
		this.transaction_id = tid;
	}

	public int getFrom_account() {
		return fromAccount;
	}

	public void setFrom_account(int from_account) {
		this.fromAccount = from_account;
	}

	public int getTo_account() {
		return toAccount;
	}

	public void setTo_account(int to_account) {
		this.toAccount = to_account;
	}

	public LocalDateTime getTransaction_time() {
		return transaction_time;
	}

	public void setTransaction_time(LocalDateTime transaction_time) {
		this.transaction_time = transaction_time;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public TransactionType gettType() {
		return tType;
	}

	public void settType(TransactionType tType) {
		this.tType = tType;
	}
}
