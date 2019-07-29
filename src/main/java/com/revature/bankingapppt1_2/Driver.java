package com.revature.bankingapppt1_2;

import java.sql.SQLException;

import com.revature.daoimpl.UserDaoimpl;

public class Driver {

	public Driver() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String args[]) {
		
		//Dbs.readAccountDBFile();
		//Dbs.readUserDBFile();
		//Dbs.setInitUserData();
		UserDaoimpl udi = new UserDaoimpl(); 
		//udi.insertUser("Bigboi1", "wordpass", "Pending");
		udi.deleteUser("Bigboi1");
		System.out.println(udi.getUserId("eheredia"));
			
		//Dbs.deletAllDenied();
		//Dbs.writeAccountFileDB(Dbs.accData);
		//Dbs.writeUserFileDB(Dbs.userData);
	}


}
