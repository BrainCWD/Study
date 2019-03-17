
/**
 * The University of Melbourne
 * COMP90041 Programming and Software Development
 * Student: Wendong Chen
 * Student ID: 931018    Username: wendongc1
 * Date: May 22th,2018
 */

import java.util.Scanner;

public class NimHumanPlayer extends NimPlayer {
	/*
	 * NimHumanPlayer class inherits all the attributes of the base class
	 * (NimPlayer), and overrides the removeStone method and getIsAI method.
	 */
	private boolean IsAI;

	public NimHumanPlayer(String Username, String Familyname, String Givenname) {
		super(Username, Familyname, Givenname);
		this.IsAI = false;
	}

	public int removeStone(String Playergivenname, int Stonenumber, 
							int Upperbound, Scanner Keyboard) {
		// This method is to perform remove operation for human players.

		int NumberofRemove;
		// This variable is the number of stones player removes once.
		String junk = null;

		System.out.println(Playergivenname + "'s turn - remove how many?");
		

		try {
			NumberofRemove = Keyboard.nextInt();
			junk = Keyboard.nextLine();
			System.out.println();
			if (NumberofRemove > Upperbound || NumberofRemove > Stonenumber || NumberofRemove <= 0) {
				throw new InvalidMoveException();
			}
		} catch (InvalidMoveException e) {
			if (Stonenumber >= Upperbound) {
				System.out.println(e.getMessage() + " You must remove between 1 and " + 
					Upperbound + " stones.");
				System.out.println();
			} else {
				System.out.println(e.getMessage() + " You must remove between 1 and " + 
					Stonenumber + " stones.");
				System.out.println();
			}
			return 0;
		} catch (Exception e) {
			junk = Keyboard.nextLine();
			System.out.println("\nInvalid Move. You must remove between 1 and " + Upperbound + " stones.");
			System.out.println();
			return 0;
		}
		return NumberofRemove;
	}

	public boolean getIsAI() {
		return this.IsAI;
	}

}
