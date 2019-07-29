package com.revature.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6621601378735547224L;
	private String userName;
	private String password;
	private String clearanceLevel;
	private ArrayList<String> accounts = new ArrayList<String>();
	private String status;
	
	public void approve() {
		this.status = "Approved";
	}
	public void deny() {
		this.status = "Denied";
	}
	
	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void addAccount(String id) {
		this.accounts.add(id);
	}
	public boolean removeAccount(String id) {
		if(!this.accounts.contains(id))
			return false;
		this.accounts.remove(id);
		return true;
	}
	public void changePassword(String newPass) {
		this.password = newPass;
	}
	
	public void printUser() {
		System.out.println(this.toString());
	}
	

	@Override
	public String toString() {
		return "| " + userName + " | " + password + " | " + clearanceLevel
				+ " | " + status + " | " + accounts;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getClearanceLevel() {
		return this.clearanceLevel;
	}
	public void setClearanceLevel(String clearanceLevel) {
		this.clearanceLevel = clearanceLevel;
	}
	public ArrayList<String> getAccounts() {
		return this.accounts;
	}
	public void setAccounts(ArrayList<String> accounts) {
		this.accounts = accounts;
	}
	public User() {
		
	}
	public User(String name, String pass, String clearance) {
		this.userName = name;
		this.password = pass;
		this.clearanceLevel = clearance;
		this.status = "Pending";
		
	}
}