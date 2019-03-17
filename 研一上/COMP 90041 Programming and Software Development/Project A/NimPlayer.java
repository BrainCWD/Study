import java.util.Scanner;

public class NimPlayer{
/**
 * This class defines players of the game and describes some methods of the game including 
 * remove of stones, setting up of the upper bound and initial number of stones, judgement
 * of the game result and printing stones on the screen.
 */
	private String NameofPlayer;
	//This variable stores players' name.

	public int UpperBound, NumberofStones;
	/*
	 * These variables are the upper bound of stones player can remove once and
	 * the number of stones, respectively.
	*/

	
	public NimPlayer(Scanner keyboard){
		String Name=keyboard.nextLine();
		this.NameofPlayer=Name;
	}

	public String getName(){
		return this.NameofPlayer;
	}

	public int RemoveStone(String player, Scanner keyboard){
	//This method is to perform remove operation.
		int NumberofRemove;
		//This variable is the number of stones player removes once.

		System.out.println(player + "'s turn - remove how many?");
		NumberofRemove=keyboard.nextInt();
		System.out.println();
		return NumberofRemove;
	}

	public void setInitial(Scanner keyboard){
	//This method sets the upper bound and the initial number of stones.
	                       
		System.out.println();
		System.out.println("Please enter upper bound of stone removal:");
		UpperBound = keyboard.nextInt();
		//Here we set the upper bound of stones that players can remove once.
		System.out.println();

		System.out.println("Please enter initial number of stones:");
		NumberofStones = keyboard.nextInt();
		//Here we set the initial number of stones of the game.
		System.out.println();
	}

	public void IsGameOver(int numberofremove, String winner){
	/*
	 * This method is to calculate the remaining stones and print them on the screen by asterisks.
	 * If the last stone is removed, it will announce the winner.
	 */
	
		NumberofStones=NumberofStones - numberofremove;

		if(NumberofStones==0) {
			System.out.println("Game Over");
			System.out.println(winner + " wins!");
			System.out.println();
		}
		else {
			printStones(NumberofStones);
		}
	}

	public void printStones(int stoneNumber) {
	//This method is to print stones using asterisk "*".
		
		System.out.print(stoneNumber + " stones left:");
		int i=0;
		while (i<stoneNumber) {
			System.out.print(" *");
			i++;
		}
		System.out.print("\n");
	}
}