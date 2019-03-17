/**
 * The University of Melbourne
 * COMP90041 Programming and Software Development
 * Student: Chen Wendong
 * Student ID: 931018    Username: wendongc1
 * Date: Apr 26th,2018
 *
 * This program is an implementation of the game of Nim.
 * It's a two player game which begins with a number of stones. Two players remove
 * some stones alternatively until the last stone is removed. The player who
 * removes the last stone loses.
 * In Project B, the Nim program adds more functions than Project A, which making
 * full use of JAVA's OOP. In addition to playing games, the system can add players, delete
 * players and edit players. Futhermore, the system can make game statistics and allow it to
 * be edited, displayed and ranked.
 */
import java.util.Scanner;
import java.util.StringTokenizer;

public class Nimsys {
	/**
	 * This class with main method manages the process of the whole program. When
	 * user text commands on the screen, the program executes commands until an
	 * "exit" command is texted. We have Addplayer, Removeplayer, Editplayer,
	 * Resetstats, Displayplayer, Rankings Exit methods in this class, which improve
	 * functionality of Nim.
	 */
	private int playerindex = 0;

	private Scanner keyboard = new Scanner(System.in);
	// The "Keyboard" object is created to get user's input.

	private NimPlayer[] player = new NimPlayer[101];
	// This is an array of players.

	private NimGame game;

	private StringTokenizer command;

	public static void main(String[] args) {

		String order = null;
		// This variable is to get user's command.

		Nimsys call = new Nimsys();
		// This object is to invoke/call the methods in Nimsys class.

		System.out.println("Welcome to Nim");

		do {
			System.out.println();
			System.out.print("$");
			// All the input is texted after this prompt "$".

			call.command = new StringTokenizer(call.keyboard.nextLine());
			// This is to recover the words or tokens in a multi-word String.

			order = call.command.nextToken();

			if (order.equals("addplayer")) {
				/*
				 * When the order is "addplayer", excute the addplayer command, i.e., call the
				 * Addplayer method.
				 */
				call.Addplayer(call.command.nextToken(", "), call.command.nextToken(", "), call.command.nextToken(", "),
						call.player);

			} else if (order.equals("removeplayer")) {
				/*
				 * When the order is "removeplayer", excute the removeplayer command, i.e., call
				 * the Removeplayer method.
				 */
				call.Removeplayer(call.command, call.player, call.keyboard);

			} else if (order.equals("editplayer")) {
				/*
				 * When the order is "editplayer", excute the editplayer command, i.e., call the
				 * Editplayer method.
				 */
				call.Editplayer(call.command.nextToken(", "), call.command.nextToken(", "),
						call.command.nextToken(", "), call.player);

			} else if (order.equals("displayplayer")) {
				/*
				 * When the order is "displayplayer", excute the displayplayer command, i.e.,
				 * call the Displayplayer method.
				 */
				call.Displayplayer(call.command, call.player);

			} else if (order.equals("startgame")) {
				/*
				 * When the order is "startgame", excute the startgame command, i.e., call the
				 * Startgame method.
				 */
				call.Startgame(call.command, call.player, call.keyboard);

			} else if (order.equals("resetstats")) {
				/*
				 * When the order is "resetstats", excute the resetstats command, i.e., call the
				 * Resetstats method.
				 */
				call.Resetstats(call.command, call.player, call.keyboard);

			} else if (order.equals("rankings")) {
				/*
				 * When the order is "rankings", excute the rankings command, i.e., call the
				 * Rankings method.
				 */
				call.Rankings(call.command, call.player);
			}

		} while (!order.equals("exit"));
		System.out.println();
		/*
		 * When the order is "exit", exit the while loop and excute the exit command,
		 * i.e., call the Exit method.
		 */
		call.Exit();
	}

	private void Addplayer(String userName, String Familyname, String Givenname, NimPlayer[] Player) {
		// This method is to perform addplayer operation.

		if (playerindex != 0) {
			for (int i = 0; i < playerindex; i++) {
				if (userName.equals(Player[i].getName())) {
					System.out.println("The player already exists.");
					break;
				}

				if (i == playerindex - 1) {
					Player[playerindex] = new NimPlayer(userName, Familyname, Givenname);
					playerindex = playerindex + 1;
					break;
				}
			}
		} else {
			Player[playerindex] = new NimPlayer(userName, Familyname, Givenname);
			playerindex = playerindex + 1;
		}
	}

	private void Removeplayer(StringTokenizer Command, NimPlayer[] Player, Scanner Keyboard) {
		// This method is to perform removeplayer operation.

		if (Command.hasMoreElements()) {
			String userName = Command.nextToken();
			NimPlayer[] Temporary = new NimPlayer[100];

			int a = 0;
			if (playerindex != 0) {
				for (int i = 0; i < playerindex; i++) {
					if (!userName.equals(Player[i].getName())) {
						Temporary[a] = Player[i];
						a = a + 1;
					}

					if (a == playerindex) {
						System.out.println("The player does not exist.");
					}
				}

				for (int i = 0; i < a; i++) {
					Player[i] = Temporary[i];
				}
				playerindex = a;
			} else {
				System.out.println("The player does not exist.");
			}
		} else {
			System.out.println("Are you sure you want to remove all players? (y/n)");
			if ("y".equals(Keyboard.nextLine())) {
				playerindex = 0;
			}
		}
	}

	private void Editplayer(String userName, String Familyname, String Givenname, NimPlayer[] Player) {
		// This method is to perform editplayer operation.

		if (playerindex != 0) {
			for (int i = 0; i < playerindex; i++) {
				if (userName.equals(Player[i].getName())) {
					Player[i].setFamilyname(Familyname);
					Player[i].setGivenname(Givenname);
					break;
				}

				if (i == playerindex - 1) {
					System.out.println("The player does not exist.");
				}
			}
		} else {
			System.out.println("The player does not exist.");
		}
	}

	private void Displayplayer(StringTokenizer Command, NimPlayer[] Player) {
		// This method is to perform display operation.

		if (Command.hasMoreElements()) {
			String userName = Command.nextToken();
			if (playerindex != 0) {
				for (int i = 0; i < playerindex; i++) {
					if (userName.equals(Player[i].getName())) {
						System.out.println(Player[i].getName() + "," + Player[i].getGivenname() + ","
								+ Player[i].getFamilyname() + "," + Player[i].getGamenumber() + " games,"
								+ Player[i].getWinnumber() + " wins");
						break;
					}

					if (i == playerindex - 1) {
						System.out.println("The player does not exist.");
					}
				}
			} else {
				System.out.println("The player does not exist.");
			}
		} else {
			if (playerindex != 0) {
				if (playerindex != 1) {
					rankings(Player);
				}
				for (int i = 0; i < playerindex; i++) {
					System.out.println(
							Player[i].getName() + "," + Player[i].getGivenname() + "," + Player[i].getFamilyname() + ","
									+ Player[i].getGamenumber() + " games," + Player[i].getWinnumber() + " wins");
				}
			}

		}

	}

	private void Resetstats(StringTokenizer Command, NimPlayer[] Player, Scanner Keyboard) {
		// This method is to perform resetstats operation.

		if (Command.hasMoreElements()) {
			String userName = Command.nextToken();
			if (playerindex != 0) {
				for (int i = 0; i < playerindex; i++) {
					if (userName.equals(Player[i].getName())) {
						Player[i].setGamenumber(0);
						Player[i].setWinnumber(0);
						break;
					}

					if (i == playerindex - 1) {
						System.out.println("The player does not exist.");
					}
				}
			} else {
				System.out.println("The player does not exist.");
			}
		} else {
			System.out.println("Are you sure you want to reset all player statistics? (y/n)");
			if ("y".equals(Keyboard.nextLine())) {
				for (int i = 0; i < playerindex; i++) {
					Player[i].setGamenumber(0);
					Player[i].setWinnumber(0);
				}
			}
		}
	}

	private void Rankings(StringTokenizer Command, NimPlayer[] Player) {
		// This method is to perform ranking operation.

		if (playerindex != 0) {
			for (int i = 0; i < playerindex; i++) {
				Player[i].setPercentage();
			}
			NimPlayer[] Temp = new NimPlayer[101];
			/*
			 * This array is the same as the player array, but it will be ranked in
			 * ascending order.
			 */

			if (playerindex > 1) {
				/*
				 * First, rank the player array in alpabetical order and then rank the winning
				 * rate in descending order for player array and in ascending order for the Temp
				 * array..
				 */
				rankings(Player);
				// Rank the player array in alpabetical order.

				for(int i=0; i < playerindex; i++){
					Temp[i] = Player[i];
				}

				for (int i = 0; i <= playerindex - 2; i++) {
					int max = i;
					for (int j = i + 1; j <= playerindex - 1; j++) {
						if (Player[max].getPercentage() < Player[j].getPercentage()) {
							max = j;
						}
					}
					Player[100] = Player[max];
					Player[max] = Player[i];
					Player[i] = Player[100];
				}

				for (int i = 0; i <= playerindex - 2; i++) {
					int min = i;
					for (int j = i + 1; j <= playerindex - 1; j++) {
						if (Temp[min].getPercentage() > Temp[j].getPercentage()) {
							min = j;
						}
					}
					Temp[100] = Temp[min];
					Temp[min] = Temp[i];
					Temp[i] = Temp[100];
				}
			}

			if (Command.hasMoreElements()) {
				String nextorder = Command.nextToken();
				if (nextorder.equals("asc")) {
					for (int i = 0; i < playerindex && i < 10; i++) {
						System.out.printf("%-4s %s %02d %s %s %s", Temp[i].getPercentageformat(), "|",
								Temp[i].getGamenumber(), "games |", Temp[i].getGivenname(), Temp[i].getFamilyname());
						System.out.println();

					}
				} else if (nextorder.equals("desc")) {
					for (int i = 0; i < playerindex && i < 10; i++) {
						System.out.printf("%-4s %s %02d %s %s %s", Player[i].getPercentageformat(), "|",
								Player[i].getGamenumber(), "games |", Player[i].getGivenname(),
								Player[i].getFamilyname());
						System.out.println();
					}
				}
			} else {
				for (int i = 0; i < playerindex && i < 10; i++) {
					System.out.printf("%-4s %s %02d %s %s %s", Player[i].getPercentageformat(), "|",
							Player[i].getGamenumber(), "games |", Player[i].getGivenname(), Player[i].getFamilyname());
					System.out.println();
				}

			}
		}
	}

	private NimPlayer[] rankings(NimPlayer[] Player) {
		// This method is to perform ranking operation by alphabetic order.

		for (int i = 0; i <= playerindex - 2; i++) {
			int min = i;
			for (int j = i + 1; j <= playerindex - 1; j++) {
				if (Player[min].getName().compareTo(Player[j].getName()) > 0) {
					min = j;
				}
			}
			Player[100] = Player[min];
			Player[min] = Player[i];
			Player[i] = Player[100];
		}
		return Player;
	}

	private void Startgame(StringTokenizer Command, NimPlayer[] Player, Scanner Keyboard) {
		// This method is to perform startgame operation.
		game = new NimGame(Integer.parseInt(Command.nextToken(", ")), Integer.parseInt(Command.nextToken(", ")),
				Command.nextToken(", "), Command.nextToken(", "));
		game.Gameprocess(Player, Keyboard, playerindex);
	}

	private void Exit() {
		// This method is to perform exit operation, i.e., end the program.
		System.exit(0);
	}
}