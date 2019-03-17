
/**
 * The University of Melbourne
 * COMP90041 Programming and Software Development
 * Student: Wendong Chen
 * Student ID: 931018    Username: wendongc1
 * Date: May 22th,2018
 */

import java.util.Scanner;

public class NimAIPlayer extends NimPlayer implements Testable{
	/*
	 * NimAIPlayer class inherits all the attributes of the base class (NimPlayer),
	 * and overrides the removeStone method and getIsAI method. Also, in the
	 * removeStone method defines a victory guaranteed strategy.
	 */

	private boolean IsAI;

	public NimAIPlayer(String Username, String Familyname, String Givenname) {
		super(Username, Familyname, Givenname);
		this.IsAI = true;
	}

	public int removeStone(String Playergivenname, int Stonenumber, 
							int Upperbound, Scanner Keyboard) {
		int NumberofRemove;

		System.out.println(Playergivenname + "'s turn - remove how many?");

		if (Stonenumber % (Upperbound + 1) == 0) {
			NumberofRemove = Upperbound;
		} else if (Stonenumber % (Upperbound + 1) != 1 ) {
			NumberofRemove = Stonenumber % (Upperbound + 1) - 1;
		} else if (Stonenumber >= Upperbound) {
			NumberofRemove = (int) (Math.random() * (Upperbound - 1)) + 1;
		} else {
			NumberofRemove = 1;
		}
		System.out.println();

		return NumberofRemove;
	}

	public boolean getIsAI() {
		return this.IsAI;
	}

	public String advancedMove(boolean[] available, String lastMove) {
		String move = "";
		return move;
	}
}
