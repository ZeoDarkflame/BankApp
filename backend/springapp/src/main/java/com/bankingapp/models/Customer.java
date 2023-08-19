package com.bankingapp.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name ="customers")
public class Customer {
	
	@Id
	@Column(name="customer_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int customerId;
	@Column(name="customer_name")
	private String customerName;
	private String password;
	
	@Column(name = "email",nullable = false)
	private String email;
	private int contact;
	
	public int getCustomer_id() {
		return customerId;
	}
	
	public void setCustomer_id(int cid) {
		this.customerId = cid;
	}
	
	public String getCustomer_name() {
		return customerName;
	}
	
	public void setCustomer_name(String name) {
		this.customerName = name;
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
