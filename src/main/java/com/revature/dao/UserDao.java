package com.revature.dao;

public interface UserDao {
	
	public abstract void insertUser(String username, String password, String type);
	
	public abstract void deleteUser(int id);
	
	public abstract void changeUserStatus(String status);
	
	public abstract int getUserId(String username); 
	

}
