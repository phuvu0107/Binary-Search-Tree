
/**
* CustomerInterface.java
* @author Tan Dung Dong
* @author Truong Phu Vu
* CIS 22C, Lab 5
*/
import java.io.*;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

public class CustomerInterface {
	public static void main(String[] args) {
		BST<MutualFundAccount> account_value = new BST<>();
		BST<MutualFundAccount> account_name = new BST<>();
		List<MutualFund> funds = new List<>();

		String first, last, email, password, mutualName, ticker;
		double cash, sharePrice, numShares, fee;

		File file = new File("mutual_funds.txt");
		Scanner input = null;
		try {
			input = new Scanner(file);
			while (input.hasNext()) {
				String fundName = input.nextLine();
				String ticker1 = input.nextLine();
				double price = input.nextDouble();
				if (input.hasNextLine()) {
					input.nextLine();
				}
				MutualFund temp = new MutualFund(fundName, ticker1, price);
				funds.addLast(temp);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// code to read in from file
		input.close();
		input = new Scanner(System.in);
		System.out.println("Welcome to Mutual Fund InvestorTrack (TM)!\n");
		boolean frag = true;
		do {
			System.out.println("Please select from the following options:\n");
			System.out.println("A. Purchase a Fund");
			System.out.println("B. Sell a Fund");
			System.out.println("C. Display Your Current Funds");
			System.out.println("X. Exit");
			System.out.println("");
			System.out.print("Enter your choice: ");

			String ans = input.nextLine();
			if (ans.charAt(0) != 'X' && ans.charAt(0) != 'A' && ans.charAt(0) != 'B' && ans.charAt(0) != 'C') {
				System.out.println();
				System.out.println("Invalid menu option. Please enter A-C or X to exit.");
				break;
			}
			char ans1 = ans.charAt(0);
			switch (ans1) {
			case 'A':
				System.out.println();
				System.out.println("Please select from the options below:");
				System.out.println();
				funds.printNumberedList();
				System.out.print("Enter your choice: (1-7): ");
				int ans2 = input.nextInt();
				input.nextLine();
				System.out.println();
				System.out.print("Enter the number of shares to purchase: ");
				int numShare = input.nextInt();
				input.nextLine();
				funds.placeIterator();
				System.out.println();
				funds.iteratorToIndex(ans2);
				MutualFund temp1 = funds.getIterator();
				MutualFundAccount temp2 = new MutualFundAccount(temp1, numShare);
				MutualFundAccount temp3 = new MutualFundAccount(temp1, numShare);
				
				MutualFund fundTemp1 = new MutualFund(temp2.getMf().getFundName());
				MutualFundAccount tempAccount1 = new MutualFundAccount(fundTemp1);
				
				if (account_name.search(tempAccount1, true, new NameComparator()) == null ) {
					account_name.insert(temp2, new NameComparator());
					account_value.insert(temp3, new ValueComparator());
				} else {
				tempAccount1 = account_name.search(tempAccount1, true, new NameComparator());
			
				MutualFundAccount tempAccount11 = new MutualFundAccount(tempAccount1.getMf(),
						tempAccount1.getNumShares());
				tempAccount1.updateShares(numShare);
				tempAccount11 = account_value.search(tempAccount11, true, new ValueComparator());
				
				account_value.remove(tempAccount11, new ValueComparator());

				tempAccount11.updateShares(numShare);
				account_value.insert(tempAccount11, new ValueComparator());
				}
				break;
			case 'B':
				if (account_name.isEmpty() == true) {
					System.out.println();
					System.out.println("You don't have any funds to sell at this time");
					System.out.println();
				} else {

					System.out.println();
					System.out.println("You own the follwing funds: ");
					account_value.inOrderPrint();
					System.out.println();
					System.out.print("Enter the name of the fund to sell: ");
					String nameSell = input.nextLine();

					MutualFund fundTemp = new MutualFund(nameSell);
					MutualFundAccount tempAccount = new MutualFundAccount(fundTemp);
					
					System.out.println();
					System.out.print("Enter the number of shares to sell or ''all'' " + "to sell everything: ");
					System.out.println();
					if (input.hasNextDouble()) {
						double numSell = input.nextDouble();
						input.nextLine();

						tempAccount = account_name.search(tempAccount, true, new NameComparator());
						MutualFundAccount tempAccount2 = new MutualFundAccount(tempAccount.getMf(),
								tempAccount.getNumShares());

						tempAccount.updateShares(-numSell);

						tempAccount2 = account_value.search(tempAccount2, true, new ValueComparator());

						account_value.remove(tempAccount2, new ValueComparator());

						tempAccount2.updateShares(-numSell);
						account_value.insert(tempAccount2, new ValueComparator());

					} else {
						input.nextLine();
						tempAccount = account_name.search(tempAccount, true, new NameComparator());
						account_name.remove(tempAccount, new NameComparator());
						tempAccount = account_value.search(tempAccount, true, new ValueComparator());
						account_value.remove(tempAccount, new ValueComparator());

					}

				}
				break;

			case 'C':
				if (account_value.isEmpty() == true) {
					System.out.println();
					System.out.println("You don't have any funds to display at this time!");
					System.out.println();
					break;
				}
				System.out.println();
				System.out.println("View Your Mutual Funds By: ");
				System.out.println();
				System.out.println("1. Name");
				System.out.println("2. Value");
				System.out.println();
				System.out.print("Enter your choice: (1 or 2): ");
				ans2 = input.nextInt();
				input.nextLine();
				System.out.println();

				if (ans2 > 2 || ans2 < 1) {

					System.out.println("Invalid Choice!");
					System.out.println();
					System.out.println("");
					break;
				}

				if (ans2 == 1)
					account_name.inOrderPrint();
				if (ans2 == 2)
					account_value.inOrderPrint();

				break;

			case 'X':
				frag = false;
				break;
			default:
				break;
			}
			
		} while (frag == true);
		System.out.println();
		System.out.println("Goodbye!");
		input.close();
	}

}