package com.revature.menus;

import java.util.Scanner;

import com.revature.bankingapppt1_2.Dbs;

public class MenuOptions {
	static Scanner in = new Scanner(System.in);

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
		do {
			System.out.println("CREATE PROFILE");
			System.out.println("Please enter desired UserName:");
			u = in.nextLine().trim();
		} while (Dbs.userData.checkIfExists(u));

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
				Dbs.userData.addUser(u, p, t);
				break;
			case "2":
				t = "Employee";
				Dbs.userData.addUser(u, p, t);
				break;
			case "3":
				t = "Admin";
				Dbs.userData.addUser(u, p, t);
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
		if (!Dbs.userData.checkIfExists(u)) {
			clearConsole();
			System.out.println("Username not found");
			return;
		}

		System.out.println("Please enter your Password:");
		p = in.nextLine().trim();
		if (!p.equals(Dbs.userData.getUser(u).getPassword())) {
			clearConsole();
			System.out.println("Incorrect Password");
			return;
		}

		if (!Dbs.userData.getUser(u).getStatus().equals("Approved")) {
			clearConsole();
			System.out.println("Account is not been approved");
			return;

		}

		switch (Dbs.userData.getClearanceLevel(u)) {
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
