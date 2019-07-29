package com.revature.dao;

public interface UserDao {
	
	public abstract void insertUser(String username, String password, String type);
	
	public abstract void deleteUser(String username);
	
	public abstract void changeUserStatus(String username, String status);
	
	public abstract int getUserId(String username); 
	

}
