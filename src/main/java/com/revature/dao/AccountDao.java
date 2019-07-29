package com.revature.dao;

public interface AccountDao {

	public abstract void insertAccount(String type);
	
	public abstract void deleteAccount(int id);
	
	public abstract void updateAccount(int id, double amount);
	
	public abstract void setBalance(int id, double amount);
	
}
