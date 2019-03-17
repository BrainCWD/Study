/**
 * The University of Melbourne
 * COMP90041 Programming and Software Development
 * Student: Wendong Chen
 * Student ID: 931018    Username: wendongc1
 * Date: May 22th,2018
 */

public class InvalidMoveException extends Exception {
/*
 * When the user tries to remove an invalid number of stones from the game,  
 * it will throw an InvalidMoveExcetion.
 */

	public InvalidMoveException() {
		super("Invalid move.");
	}
	
	public InvalidMoveException(String message) {
		super(message);
	}
}
