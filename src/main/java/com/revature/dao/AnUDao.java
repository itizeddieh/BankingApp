package com.revature.dao;

import java.util.ArrayList;


public interface AnUDao {
	
	public abstract void linkUserAndAccount(int uId, int aId);
	
	public abstract void unlinkAllUsersAccounts(int uId);
	
	public abstract void unlinkUserAndAccount(int uId, int aId);

	public abstract ArrayList<Integer> allAccounts(int uId);
}
