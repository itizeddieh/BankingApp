package com.revature.bankingapppt1_2;

import com.revature.daoimpl.UserDaoimpl;

public class Dbs {
	private static final String userFile = "user.txt";
	public static UserDatabase userData;
	private static final String accountFile = "account.txt";
	public static AccountDatabase accData;
	

	public static UserDatabase getUserData() {
		return userData;
	}
	
//	public static void emptyAccounts() {
//		for(String u: user)
//	}
	public static void deletAllDenied() {
		for(String u: userData.getAll("Denied")) {
			for(String a : userData.getAccounts(u)) {
				
				accData.removeUser(a, u);
			}
			userData.removeUser(u);
		}
		for(String a : accData.getAll("Denied")) {
			for(String u : accData.getUsers(a)) {
				userData.removeAccount(u, a);
			}
			accData.removeAccount(a);
		}	
		
	}
	

	public static AccountDatabase getAccData() {
		return accData;
	}




}
