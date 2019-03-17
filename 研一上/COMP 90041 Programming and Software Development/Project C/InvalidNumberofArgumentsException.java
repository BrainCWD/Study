/**
 * The University of Melbourne
 * COMP90041 Programming and Software Development
 * Student: Wendong Chen
 * Student ID: 931018    Username: wendongc1
 * Date: May 22th,2018
 */

public class InvalidNumberofArgumentsException extends Exception {
/*
 * When the user enters a valid command but does not provide the correct number
 * of arguments, it will throw an InvalidNumberofSrgumentsExcetion.
 */

	public InvalidNumberofArgumentsException() {
		super("Incorrect number of arguments supplied to command.");
	}
	
	public InvalidNumberofArgumentsException(String message) {
		super(message);
	}
}
