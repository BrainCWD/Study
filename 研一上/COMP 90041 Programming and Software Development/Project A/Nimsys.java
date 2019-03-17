/**
 * The University of Melbourne
 * COMP90041 Programming and Software Development
 * Student: Chen Wendong
 * Student ID: 931018    Username: wendongc1
 * Date: Mar 27th,2018
 * This program implement a simple variant of the game of Nim.
 * It's a two player game which begins with a number of stones. Two players remove
 * some stones alternatively until the last stone is removed. The player who
 * removes the last stone loses.
 */
import java.util.Scanner;   
//This "Scanner" class have different methods which can get values typed in at the keyboard. 

public class Nimsys {
/* 
 * This class with main method manages the process of the game.
 * We can use its getName() method to type players' name.
 * Then we set the initial number of stones and upper bound of stones
 * players can remove once. By using RemoveStone() method implements
 * the game step by step until game over. The "do_while" loop is set to
 * restart the game if you want to.
 */
	
	public static void main(String[] args){
		Scanner Keyboard = new Scanner(System.in);  
		//The "Keyboard" object is created to get user's input.

		String AnotherGame;  
		//This variable is to get user's input of whether replays the game.

		boolean TureofFalse; 
		//This variable is to judge if the game need to replay.

		System.out.println("Welcome to Nim");
		System.out.println();
		System.out.println("Please enter Player 1's name:");
		NimPlayer Playerone=new NimPlayer(Keyboard);
		//The "Playerone" object is created which represents the first player.

		System.out.println();

		System.out.println("Please enter Player 2's name:");
		NimPlayer Playertwo=new NimPlayer(Keyboard);
		//The "Playertwo" object is created which represents the second player.

		

		do{
			Playerone.setInitial(Keyboard);
			//Here we set the upper bound of each remove and the initial number of stones.

			Playerone.printStones(Playerone.NumberofStones);
			//Here we print "*" as one stone on the screen by using printStones() method.

			while(Playerone.NumberofStones>0) {
			//This while loop means the game will be continually running until the number of stones is 0.
		
				Playerone.IsGameOver(Playerone.RemoveStone(Playerone.getName(), Keyboard), Playertwo.getName());
				//Play one removes stones and judge if the game is over.

				if(Playerone.NumberofStones>0){
					Playertwo.NumberofStones=Playerone.NumberofStones;
					Playertwo.IsGameOver(Playertwo.RemoveStone(Playertwo.getName(), Keyboard), Playerone.getName());
					//Player two removes stones and judge if the game is over.

					Playerone.NumberofStones=Playertwo.NumberofStones;
				}	
			}

			System.out.print("Do you want to play again (Y/N):");
			AnotherGame=Keyboard.next();
			//If player types "Y", the game will replay again, otherwise the game over.

			TureofFalse="Y".equals(AnotherGame);

		}while (TureofFalse==true);
		//This "do_while" loop can run the game again depending on the Y/N answer.
	}
}