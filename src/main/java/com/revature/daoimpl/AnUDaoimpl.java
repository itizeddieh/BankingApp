package com.revature.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.beans.Account;
import com.revature.beans.User;
import com.revature.dao.AnUDao;
import com.revature.util.ConnFactory;

public class AnUDaoimpl implements AnUDao{
	public static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public void linkUserAndAccount(int uId, int aId) {
		
		Connection conn = cf.getConnection(); 
		String sql = "{ call LINK_USER_ACCOUNT(?,?)";
		CallableStatement call;
		try {
			call = conn.prepareCall(sql);
			call.setInt(1, uId);
			call.setInt(2, aId);
			call.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	@Override
	public void unlinkAllUsersAccounts(int uId) {
		Connection conn = cf.getConnection(); 
		String sql = "{ call UNLINK_USER(?)";
		CallableStatement call;
		try {
			call = conn.prepareCall(sql);
			call.setInt(1, uId);
			call.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	@Override
	public void unlinkUserAndAccount(int uId, int aId) {
		Connection conn = cf.getConnection(); 
		String sql = "{ call UNLINK_USER_ACCOUNT(?, ?)";
		CallableStatement call;
		try {
			call = conn.prepareCall(sql);
			call.setInt(1, uId);
			call.setInt(2, aId);
			call.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	@Override
	public ArrayList<Integer> allAccounts(int uId) {
		ArrayList<Integer> accountIdList = new ArrayList<Integer>(); 
		Connection conn = cf.getConnection();
		String sql = "SELECT account_id FROM ANULOOKUP WHERE user_id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, uId);
			ResultSet rs;
			rs = ps.executeQuery();
			
			while (rs.next()) {
				accountIdList.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return accountIdList; 
	}

}
