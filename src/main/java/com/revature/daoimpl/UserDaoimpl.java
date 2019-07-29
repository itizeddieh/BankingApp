package com.revature.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.dao.UserDao;
import com.revature.util.ConnFactory;

public class UserDaoimpl implements UserDao{
	
	public static ConnFactory cf = ConnFactory.getInstance();
	@Override
	public void insertUser(String username, String password, String type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(String username) {
		int id = this.getUserId(username);
		if(id < 0) {
			return;
		}
		Connection conn = cf.getConnection(); 
		String sql = "{ call delete_user(?)";
		
		try {
			CallableStatement call = conn.prepareCall(sql); 
			call.setInt(1, id);
			call.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void changeUserStatus(String status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getUserId(String username) {
		Connection conn = cf.getConnection(); 
		String sql = "SELECT user_id FROM BANK_USERS WHERE user_name = ?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return -1;
	}
	
	
}