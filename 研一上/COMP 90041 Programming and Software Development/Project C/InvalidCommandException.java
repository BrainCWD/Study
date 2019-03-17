/**
 * The University of Melbourne
 * COMP90041 Programming and Software Development
 * Student: Wendong Chen
 * Student ID: 931018    Username: wendongc1
 * Date: May 22th,2018
 */

public class InvalidCommandException extends Exception {
/*
 * When the user enters an invalid command, it will throw an 
 * InvalidCommandExcetion.
 */
	
	public InvalidCommandException() {
		super("Invalid command");
	}
	
	public InvalidCommandException(String message) {
		super(message);
	}
}
