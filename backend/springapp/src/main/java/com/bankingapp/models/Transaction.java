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
<<<<<<< HEAD
	private int transactionId;
	@Column(name="fromAccount")
	private int fromAccount;
	@Column(name="toAccount")
	private int toAccount;
	@Column(name="transactionTime")
	private LocalDateTime transactionTime;
=======
	private int transaction_id;
	
	@Column(name  = "from_account")
	private int fromAccount;
	
	@Column(name = "to_account")
	private int toAccount;
	
	private LocalDateTime transaction_time;
>>>>>>> 1b477f2a8e2dc7c1686b23cd473590bc35435587
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

<<<<<<< HEAD
	public void setFrom_account(int fromAccount) {
		this.fromAccount = fromAccount;
=======
	public void setFrom_account(int from_account) {
		this.fromAccount = from_account;
>>>>>>> 1b477f2a8e2dc7c1686b23cd473590bc35435587
	}

	public int getTo_account() {
		return toAccount;
	}

<<<<<<< HEAD
	public void setTo_account(int toAccount) {
		this.toAccount = toAccount;
=======
	public void setTo_account(int to_account) {
		this.toAccount = to_account;
>>>>>>> 1b477f2a8e2dc7c1686b23cd473590bc35435587
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
