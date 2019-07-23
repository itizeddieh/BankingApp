package com.revature.bankingapppt1_2;


public class Driver {

	public Driver() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String args[]) {
		
		Dbs.readAccountDBFile();
		Dbs.readUserDBFile();
		Dbs.setInitUserData();
			//MenuOptions.clearConsole();
			MenuOptions.printMainMenu(); 
			MenuOptions.mainMenu();
			
		//Dbs.userData.removeUser("justin");
		
		Dbs.deletAllDenied();
		Dbs.writeAccountFileDB(Dbs.accData);
		Dbs.writeUserFileDB(Dbs.userData);
	}


}
