package com.revature.bankingapppt1_2;

import com.revature.daoimpl.AccountDaoimpl;
import com.revature.daoimpl.AnUDaoimpl;
import com.revature.daoimpl.UserDaoimpl;
import com.revature.menus.MenuOptions;



public class Driver {
	
	public static void main(String args[]) {
		//UserDaoimpl udi = new UserDaoimpl();
		AnUDaoimpl anu = new AnUDaoimpl();
		AccountDaoimpl adi = new AccountDaoimpl();
		
		//adi.insertAccount("Checking");
		//anu.linkUserAndAccount(7, 3);
		//anu.linkUserAndAccount(7,2);
		
		MenuOptions.printMainMenu();
		MenuOptions.mainMenu();
		
	}


}
