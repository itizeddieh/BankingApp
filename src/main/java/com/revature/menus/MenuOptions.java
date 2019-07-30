package com.revature.menus;

import java.util.Scanner;

import com.revature.daoimpl.*;

public class MenuOptions {
	static Scanner in = new Scanner(System.in);
	public static UserDaoimpl udi = new UserDaoimpl();
	public static AccountDaoimpl adi = new AccountDaoimpl();
	public static AnUDaoimpl anu = new AnUDaoimpl();

	// Prints Main menu
	public static void printMainMenu() {
		MenuOptions.clearConsole();
		System.out.println("MAIN MENU");
		System.out.println("Please select an option:Ex// 1");
		System.out.println("1: Create Profile");
		System.out.println("2: Login");
		System.out.println("3: Exit");
	}

	public static void mainMenu() {
		String s;
		do {
			s = in.nextLine().trim();
			switch (s) {
			case "1":
				CreateProfile();
				printMainMenu();
				break;
			case "2":
				clearConsole();
				login();
				printMainMenu();
				break;
			case "3":
				return;

			default:
				System.out.println("Sorry that wasn't a valid option. Please try again.");
				printMainMenu();
				break;
			}
		} while (!s.equals("3"));
	}

	public static void CreateProfile() {
		String u, p, t;
		int test;
		do {
			System.out.println("CREATE PROFILE");
			System.out.println("Please enter desired UserName:");
			u = in.nextLine().trim();
			test = udi.getUserId(u);
		} while (test != -1);

		System.out.println("Please enter desired password:");
		p = in.nextLine().trim();

		do {
			clearConsole();
			System.out.println("Please enter account type:");
			System.out.println("1. Customer");
			System.out.println("2. Employee");
			System.out.println("3. Admin");
			t = in.nextLine().trim();
			switch (t) {
			case "1":
				t = "Customer";
				udi.insertUser(u, p, t);
				break;
			case "2":
				t = "Employee";
				udi.insertUser(u,p,t);
				break;
			case "3":
				t = "Admin";
				udi.insertUser(u, p, t);
				break;

			default:
				System.out.println("Sorry that wasn't a valid option. Please try again.");
				break;
			}
		} while (!t.equals("Customer") && !t.equals("Employee") && !t.equals("Admin"));

	}

	/*
	 * Login screen
	 * 
	 * @param u username
	 * 
	 * @param p password
	 */
	public static void login() {
		String u, p;
		System.out.println("LOGIN");
		System.out.println("Please enter your UserName:");
		u = in.nextLine().trim();
		if (udi.getUserId(u) == -1) {
			clearConsole();
			System.out.println("Username not found");
			return;
		}

		System.out.println("Please enter your Password:");
		p = in.nextLine().trim();
		if (!p.equals(udi.getUserPassword(u))) {
			clearConsole();
			System.out.println("Incorrect Password");
			return;
		}

		if (!udi.getUserStatus(u).equals("Approved")) {
			clearConsole();
			System.out.println("Account is not been approved");
			return;

		}

		switch (udi.getClearance(u)) {
		case "Customer":
			CustomerMenu.printCMM();
			CustomerMenu.run(u);
			
			break;
		case "Employee":
			EmployeeMenu.print();
			EmployeeMenu.run();
			break;
		case "Admin":
			AdminMenu.print();
			AdminMenu.run();
			break;
		}
	}
	public static void clearConsole() {
		System.out.println("\n");
	}
}
