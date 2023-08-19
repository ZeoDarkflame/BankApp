package com.bankingapp.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="accounts")
public class Account {
	
	@Id
	@Column(name="account_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int accountId;

	@Column(name="customer_id")
	private int customerId;
    private float balance;
    private String username;
    private String transactionPassword;

    public int getCustomer_id(){
        return this.customerId;
    }

    public int getAccount_id(){
        return this.accountId;
    }

    public float getBalance(){
        return this.balance;
    }

    public String getUsername(){
        return this.username;
    }

    public String getTransactionPassword(){
        return this.transactionPassword;
    }

    public void setCustomer_id(int customerId){
        this.customerId = customerId;
    }

    public void setAccount_id(int accountId){
        this.accountId = accountId;
    }

    public void setBalance(float balance){
        this.balance = balance;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setTransactionPassword(String transactionPassword){
        this.transactionPassword = transactionPassword;
    }
}
