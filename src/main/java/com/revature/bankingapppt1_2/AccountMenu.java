package com.revature.bankingapppt1_2;

import java.util.ArrayList;

public class AccountMenu {

	public static void createAccount(String u) {
		String t;
		do {
			MenuOptions.clearConsole();
			System.out.println("CREATE ACCOUNT");
			System.out.println("Please enter account type:");
			System.out.println("1: Checking");
			System.out.println("2: Savings");
			t = MenuOptions.in.nextLine().trim();
			switch (t) {
			case "1":
				t = "Customer";
				Dbs.accData.addAccount(Dbs.accData.acctDB.size() + "", "Checking", u);
				Dbs.userData.addAccount(u, Dbs.accData.acctDB.size() - 1 + "");
				System.out.println("Account is pending. Check later to see status.");
				break;
			case "2":
				t = "Employee";
				Dbs.accData.addAccount(Dbs.accData.acctDB.size() + "", "Savings", u);
				Dbs.userData.addAccount(u, Dbs.accData.acctDB.size() - 1 + "");
				System.out.println("Account is pending. Check later to see status.");
				break;

			default:
				System.out.println("Sorry that wasn't a valid option. Please try again.");
				break;
			}
		} while (!t.equals("Customer") && !t.equals("Employee") && !t.equals("Admin"));

	}

	/*
	 * Lists all accounts for the user. Printed as #. |Acc Type | Bal: X.X | Acc
	 * Status | [List of users active on account]
	 */
	public static void selectAccount(String u) {

		ArrayList<String> s = (ArrayList<String>) Dbs.userData.getAccounts(u);
		int i = 1;
		MenuOptions.clearConsole();
		System.out.println("SELECT ACCOUNT");
		System.out.println("Please select account");
		for (String a : s) {
			System.out.println(i + ": " + Dbs.accData.getAccount(a));
			i++;
		}
		String input;

		input = MenuOptions.in.nextLine();

		try {
			int option = Integer.parseInt(input);
			if (option > 0 && option <= Dbs.userData.getAccounts(u).size()) {
				// check if selected account is approved
				if (Dbs.accData.getAccount(Dbs.userData.getAccounts(u).get(option - 1)).getStatus()
						.equals("Approved")) {
					accountMenu(u, Dbs.accData.getAccount(Dbs.userData.getAccounts(u).get(option - 1)));
				} else {
					System.out.println("Sorry that account has not been approved.");
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid input. Try again!");
		}
	}

	public static void accountMenu(String user, Account act) {
		MenuOptions.clearConsole();
		System.out.println("MY ACCOUNT");
		System.out.println("Please choose an option");
		System.out.println("1: Withdraw");
		System.out.println("2: Deposit");
		System.out.println("3: Make this account a joint account with another user");
		System.out.println("4: Transfer From This Accounts");

		String s;
		do {
			s = MenuOptions.in.nextLine();
			switch (s) {
			case "1":
				withdraw(user, act.getUniqueID());
				return;
			case "2":
				deposit(user, act.getUniqueID());
				return;
			case "3":
				grantAccess(user, act.getUniqueID());
				return;
			case "4":
				transferMoney(user, act.getUniqueID());
				return;
			default:
				return;
			}
		} while (s != "1" && s != "2" && s != "3");
	}

	public static void withdraw(String user, String act) {
		System.out.println("Please input an amount");
		String s;

		s = MenuOptions.in.nextLine();

		try {
			double amount = Double.parseDouble(s);
			if (amount <= Dbs.accData.getBalance(act) && amount >=0) {
				Dbs.accData.updateBalance(act, -1 * amount);
			} 
			else if(amount < 0) {
				System.out.println("Value must be positive.");
			}
			else {
				System.out.println("Your balance is insufficient.");

			}
		} catch (NumberFormatException ne) {
			System.out.println("Not a valid amount");

		}

	}

	public static void deposit(String user, String act) {
		System.out.println("Please input an amount");
		String s;
			s = MenuOptions.in.nextLine();
			try {
				double amount = Double.parseDouble(s);
				if(amount > 0)
					Dbs.accData.updateBalance(act, amount);
				else {
					System.out.println("Value must be positive.");
					return;
				}
			} catch (NumberFormatException ne) {
				System.out.println("Not a valid amount.");
				return;
			}
	}

	
	public static void grantAccess(String myUserName, String id) {
		MenuOptions.clearConsole();
		
		System.out.println("You are now making this a Joint account!");
		String username;

		System.out.println("Please enter the user id of the account you want to join");
		username = MenuOptions.in.nextLine();
		if (!Dbs.getUserData().checkIfExists(username)) {
			System.out.println("User does not exist");
			return;
		}
		if(!Dbs.userData.getUser(username).getStatus().equals("Approved")) {
			System.out.println("Sorry, user has not been approved");
			return;
		}

		System.out.println("Please enter the password of " + username);
		String password = MenuOptions.in.nextLine();
		if (!password.equals(Dbs.getUserData().getUser(username).getPassword())) {
			System.out.println("Incorrect Password. User failed to join account.");
			return;
		}
		Dbs.accData.addUser(id, username);
		Dbs.userData.addAccount(username, id);
	}
	
	public static void transferMoney(String username, String source_id) {
		if(Dbs.userData.getAccounts(username).size() < 2) {
			System.out.println("Only one account found.");
			return;
		}
		System.out.println("TRANSFER FROM YOUR ACCOUNTS");
		System.out.println("Please Choose a target account");
		ArrayList<String> s = Dbs.userData.getAccounts(username);
		int i = 1;
		for (String a : s) {
			System.out.println(i + ": " + Dbs.accData.getAccount(a));
			i++;
		}
		String input;
		input = MenuOptions.in.nextLine();
		try {
			int option = Integer.parseInt(input);
			if (option > 0 && option <= Dbs.userData.getAccounts(username).size()) {
				if (!s.get(option - 1).equals(source_id)) {
					System.out.println("Please enter transfer amount.");
					input = MenuOptions.in.nextLine();
					double tranAmt;
					try {
						tranAmt = Double.parseDouble(input);
						if(tranAmt < 0.0) {
							System.out.println("Sorry, value cannot be negative.");
							return;
						}
						if(tranAmt > Dbs.accData.getBalance(source_id)) {
							System.out.println("Sorry, insufficient funds");
							return;
						}
						Dbs.accData.updateBalance(source_id, -1 * tranAmt);
						Dbs.accData.updateBalance(
								Dbs.userData.getAccounts(username).get(option - 1), tranAmt);
						System.out.println("Funds transfered successfully.");
					}catch(NumberFormatException ne) {
						System.out.println("Invalid input.");
					}
				} else {
					System.out.println("Sorry, cannot tranfer to source account");
				}
			}
			else {
				System.out.println("Sorry, not a valid option.");
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid input.");
		}
		
	}

}
