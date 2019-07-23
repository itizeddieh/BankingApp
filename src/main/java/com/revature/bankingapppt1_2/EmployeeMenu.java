package com.revature.bankingapppt1_2;

import java.util.ArrayList;

public class EmployeeMenu {
	
	public static void print() {
		MenuOptions.clearConsole();
		System.out.println("Employee Options");
		System.out.println("1: ViewUser Accounts");
		System.out.println("2: Logout");
	}
	public static void run() {
		String s;
		do {
			s = MenuOptions.in.nextLine().trim();
			switch (s) {
			case "1":
				viewUsers();
				print();
				break;
			case "2":
				return;
			default:
				System.out.println("Sorry that wasn't a valid option. Please try again.");
				print();
				break;
			}
		} while (!s.equals("2"));
	}
	
	public static void viewUsers() {
		String u;
		System.out.println("Please type a user name.");
		u = MenuOptions.in.nextLine().trim();
		if (!Dbs.userData.checkIfExists(u)) {
			System.out.println("Username not found");
			return;
		}
		else {
			ArrayList<String> acctList;
			acctList = Dbs.userData.getAccounts(u);
			int i = 1;
			System.out.println("View user profile and accounts below.");
			Dbs.userData.getUser(u).printUser();
			System.out.println();
			for(String s4 : acctList) {
				System.out.println(i + ": " + Dbs.accData.getAccount(s4).toString());
				i++;
			}
			System.out.println("\n\n\n");
		}	
	}	
}