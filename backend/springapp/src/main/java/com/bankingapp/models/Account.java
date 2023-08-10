package com.bankingapp.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Account {
	
	@Id
	private int customer_id;
	private int account_id;
    private float balance;
    private String username;
    private String transactionPassword;

    public int getCustomer_id(){
        return this.customer_id;
    }

    public int getAccount_id(){
        return this.account_id;
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

    public void setCustomer_id(int customer_id){
        this.customer_id = customer_id;
    }

    public void setAccount_id(int account_id){
        this.account_id = account_id;
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
