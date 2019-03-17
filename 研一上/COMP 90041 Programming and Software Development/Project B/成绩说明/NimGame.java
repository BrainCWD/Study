import java.util.Scanner;

public class NimGame {
	private int stonenumber, upperbound;
	/*
	 * These variables are the stone number and upperbound of the stone players can
	 * remove once, repectively.
	 */
	private String player1, player2;
	// These variables are two players' name, respectively.

	private int player1index, player2index;
	// These variables are the index of two players in the player array.

	public NimGame(int Initialnumber, int Upperbound, String Player1, String Player2) {
		/*
		 * The constructor of the Nim game defines the initial number of stones, the
		 * upperbound of the stones which players can remove once and two players.
		 */
		this.stonenumber = Initialnumber;
		this.upperbound = Upperbound;
		this.player1 = Player1;
		this.player2 = Player2;
	}

	public void Gameprocess(NimPlayer[] Player, Scanner Keyboard, int Index) {
		/*
		 * This method contains all the process of the Nim game. First determine if two
		 * players exist and then process the game.
		 */
		player1index = exist(player1, Index, Player);
		player2index = exist(player2, Index, Player);

		if (-1 != player1index && -1 != player2index) {
			Player[player1index].setGamenumber(1);
			Player[player2index].setGamenumber(1);
			System.out.println();
			System.out.println("Initial stone count: " + stonenumber);
			System.out.println("Maximum stone removal: " + upperbound);
			System.out.println(
					"Player 1: " + Player[player1index].getGivenname() + " " + Player[player1index].getFamilyname());
			System.out.println(
					"Player 2: " + Player[player2index].getGivenname() + " " + Player[player2index].getFamilyname());
			System.out.println();

			printStones(stonenumber);
			while (stonenumber > 0) {
				int validRemoval = stonenumber;
				while (validRemoval == stonenumber) {
					IsGameOver(removeStone(Player[player1index].getGivenname(), Keyboard), Player[player2index]);
				}

				if (stonenumber > 0) {
					validRemoval = stonenumber;
					while (validRemoval == stonenumber) {
						IsGameOver(removeStone(Player[player2index].getGivenname(), Keyboard), Player[player1index]);
					}
				}

			}
			String junk = Keyboard.nextLine();
		}
	}

	private int exist(String Playername, int index, NimPlayer[] Player) {
		// This method is to determine if the players exist.
		if (index != 0) {
			for (int i = 0; i < index; i++) {
				if (Playername.equals(Player[i].getName())) {
					return i;
				}
				if (i == index - 1) {
					System.out.println("One of the players does not exist");
				}
			}

		} else {
			System.out.println("One of the players does not exist");
		}

		return -1;
	}

	private int removeStone(String playergivenname, Scanner Keyboard) {
		// This method is to perform remove operation.

		int NumberofRemove;
		// This variable is the number of stones player removes once.

		System.out.println(playergivenname + "'s turn - remove how many?");
		NumberofRemove = Keyboard.nextInt();
		System.out.println();

		if (NumberofRemove > upperbound || NumberofRemove > stonenumber || NumberofRemove <= 0) {
			if (stonenumber >= upperbound) {
				System.out.println("Invalid move. You must remove between 1 and " + upperbound + " stones.");
				System.out.println();
			} else {
				System.out.println("Invalid move. You must remove between 1 and " + stonenumber + " stones.");
				System.out.println();
			}
			return 0;
		}
		return NumberofRemove;
	}

	private void IsGameOver(int numberofremove, NimPlayer Player) {
		/*
		 * This method is to calculate the remaining stones and print them on the screen
		 * by asterisks. If the last stone is removed, it will announce the winner.
		 */

		stonenumber = stonenumber - numberofremove;

		if (stonenumber == 0) {
			System.out.println("Game Over");
			System.out.println(Player.getGivenname() + " " + Player.getFamilyname() + " wins!");
			Player.setWinnumber(1);
		} else if (numberofremove != 0) {
			printStones(stonenumber);
		}
	}

	private void printStones(int stoneNumber) {
		// This method is to print stones using asterisk "*".

		System.out.print(stoneNumber + " stones left:");
		for (int i = 0; i < stoneNumber; i++) {
			System.out.print(" *");
		}
		System.out.println();
	}
}
