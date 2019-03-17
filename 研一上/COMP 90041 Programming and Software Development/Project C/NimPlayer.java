
/**
 * The University of Melbourne
 * COMP90041 Programming and Software Development
 * Student: Wendong Chen
 * Student ID: 931018    Username: wendongc1
 * Date: May 22th,2018
 */

import java.util.Scanner;

public abstract class NimPlayer {
	/**
	 * In this abstract class difines all the attributes of each player and also has
	 * lots of accssors and mutators due to the private type. The abstract methods
	 * will be overrided in the derived classes, i.e., NimHumanPlayer and
	 * NimAIPlayer.
	 */
	private String username, family_name, given_name;
	/*
	 * These variables are the username, familyname and givenname of the players,
	 * respectively.
	 */

	private int gamenumber = 0, winnumber = 0;
	/*
	 * These variables are the game number and win number statistics that players
	 * played, respectively.
	 */

	private double percentage;
	// This variable is the players' winning rate.

	private String percentageFormat;
	// This variable is the players' winning rate writting in '%' format.

	public NimPlayer(String Username, String Familyname, String Givenname) {
		/*
		 * The constuctor of every player, in which defines players' username,
		 * familyname and givenname.
		 */
		this.username = Username;
		this.family_name = Familyname;
		this.given_name = Givenname;
	}

	public String getName() {
		// The accessor of players' username.
		return this.username;
	}

	public String getFamilyname() {
		// The accessor of players' familyname.
		return this.family_name;
	}

	public void setFamilyname(String Familyname) {
		// The mutator of players' familyname.
		this.family_name = Familyname;
	}

	public String getGivenname() {
		// The accessor of players' givenname.
		return this.given_name;
	}

	public void setGivenname(String Givenname) {
		// The mutator of players' givenname.
		this.given_name = Givenname;
	}

	public int getGamenumber() {
		// The accessor of players' gamenumber.
		return this.gamenumber;
	}

	public void setGamenumber(int Number) {
		// The mutator of players' gamenumber.
		if (Number != 0) {
			this.gamenumber = gamenumber + Number;
		} else {
			this.gamenumber = 0;
		}
	}

	public int getWinnumber() {
		// The accessor of players' winnumber.
		return this.winnumber;
	}

	public void setWinnumber(int Number) {
		// The mutator of players' winnumber.
		if (Number != 0) {
			this.winnumber = winnumber + Number;
		} else {
			this.winnumber = 0;
		}
	}

	public double getPercentage() {
		// The accessor of players' winning rate.
		return this.percentage;
	}

	public void setPercentage() {
		// The mutator of players' winning rate.
		if (this.winnumber == 0) {
			this.percentage = 0;
		} else {
			this.percentage = (double) this.winnumber * 100 / (double) this.gamenumber;
		}
	}

	public String getPercentageformat() {
		// Format the players' winning rate.
		return this.percentageFormat = Math.round(this.percentage) + "%";
	}

	public abstract int removeStone(String Playergivenname, int Stonenumber, 
										int Upperbound, Scanner Keyboard);

	public abstract boolean getIsAI();
}