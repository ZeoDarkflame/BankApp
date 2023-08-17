package com.bankingapp.models;

public class AuthResponse {
	private String username;
	private boolean isValid;

	public AuthResponse() {
		super();
	}
	
	public AuthResponse(String username,boolean isValid) {
		super();
		this.username = username;
		this.isValid = isValid;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public boolean getIsValid() {
		return isValid;
	}
	
	public void setIsValid(boolean isValid) {
		this.isValid = isValid;
	}
	
}
