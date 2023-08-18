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
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int account_id;

	@Column(name="customer_id")
	private int customerid;
    private float balance;
    private String username;
    private String transactionPassword;

    public int getCustomer_id(){
        return this.customerid;
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
        this.customerid = customer_id;
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
