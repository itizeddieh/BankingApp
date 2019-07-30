package com.revature.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.beans.Account;
import com.revature.dao.AccountDao;
import com.revature.util.ConnFactory;

public class AccountDaoimpl implements AccountDao{
	public static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public void insertAccount(String type) {
		Connection conn = cf.getConnection(); 
		String sql = "{ call INSERT_ACCOUNT(?)";
		CallableStatement call;
		try {
			call = conn.prepareCall(sql);
			call.setString(1, type);
			call.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	@Override
	public void deleteAccount(int accountID) {
		Connection conn = cf.getConnection(); 
		String sql = "{ call INSERT_ACCOUNT(?)";
		CallableStatement call;
		try {
			call = conn.prepareCall(sql);
			call.setInt(1, accountID);
			call.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	@Override
	public void changeAccountStatus(int accountID, int status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeAccountBalance(int accountID, double val) {
		Connection conn = cf.getConnection(); 
		String sql = "{ call UPDATE_BALANCE(?,?)";
		CallableStatement call;
		try {
			call = conn.prepareCall(sql);
			call.setInt(1, accountID);
			call.setDouble(2, val);
			call.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	@Override
	public void setAccountBalance(int accountID, double val) {
		Connection conn = cf.getConnection(); 
		String sql = "{ call SET_BALANCE(?,?)";
		CallableStatement call;
		try {
			call = conn.prepareCall(sql);
			call.setInt(1, accountID);
			call.setDouble(2, val);
			call.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public int getAccountId(String username) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAccountStatus(int accountID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Account getAccount(int accountID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Account> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
