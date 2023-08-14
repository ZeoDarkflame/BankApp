package com.bankingapp.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int customer_id;
	private String customer_name;
	private String password;
	
	@Column(name = "email",nullable = false)
	private String email;
	private int contact;
	
	public int getCustomer_id() {
		return customer_id;
	}
	
	public void setCustomer_id(int cid) {
		this.customer_id = cid;
	}
	
	public String getCustomer_name() {
		return customer_name;
	}
	
	public void setCustomer_name(String name) {
		this.customer_name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String pass) {
		this.password = pass;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String mail) {
		this.email = mail;
	}
	
	public int getContact() {
		return contact;
	}
	
	public void setContact(int num) {
		this.contact = num;
	}
}
