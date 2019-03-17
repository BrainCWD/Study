
/**
 * The University of Melbourne
 * COMP90041 Programming and Software Development
 * Student: Wendong Chen
 * Student ID: 931018    Username: wendongc1
 * Date: May 22th,2018
 *
 * This program is an implementation of the game of Nim.
 * It's a two player game which begins with a number of stones. Two players remove
 * some stones alternatively until the last stone is removed. The player who
 * removes the last stone loses.
 * In Project C, the Nim program adds more functions than Project B. In addition to 
 * playing games between people, the system can add AI players, also wen can store 
 * the statistics of the game. Futhermore, the system can handle the invalid input
 * via exceptions.
 */
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class Nimsys {
	/**
	 * This class with main method manages the process of the whole program. When
	 * user text commands on the screen, the program executes commands until an
	 * "exit" command is texted. We have Addplayer, Removeplayer, Editplayer,
	 * Resetstats, Displayplayer, Rankings Exit methods in this class, which improve
	 * functionality of Nim.
	 */
	private int playerindex = 0;
	private final int playermaxnumber = 100;
	private final int highestargu = 1;
	private final int validargu = 3;
	private final int startgameargu = 4;

	private Scanner keyboard = new Scanner(System.in);
	// The "Keyboard" object is created to get user's input.

	private NimPlayer[] player = new NimPlayer[playermaxnumber];
	// This is an array of players.

	private NimGame game;

	private StringTokenizer command;

	public static void main(String[] args) {

		String order = null;
		// This variable is to get user's command.

		Nimsys call = new Nimsys();
		// This object is to invoke/call the methods in Nimsys class.

		System.out.println("Welcome to Nim");
		// Next will read the file "players.dat".
		try {
			Scanner input = new Scanner(new FileInputStream("players.dat"));
			while (input.hasNext()) {
				if (input.nextLine().equals("true")) {
					call.player[call.playerindex] = new NimAIPlayer(input.nextLine(), 
						input.nextLine(), input.nextLine());
					call.player[call.playerindex].setGamenumber(Integer.parseInt(input.nextLine()));
					call.player[call.playerindex].setWinnumber(Integer.parseInt(input.nextLine()));
				} else {
					call.player[call.playerindex] = new NimHumanPlayer(input.nextLine(), 
						input.nextLine(), input.nextLine());
					call.player[call.playerindex].setGamenumber(Integer.parseInt(input.nextLine()));
					call.player[call.playerindex].setWinnumber(Integer.parseInt(input.nextLine()));
				}
				call.playerindex = call.playerindex + 1;
			}
			input.close();

		} catch (FileNotFoundException e) {
		}

		do {
			try {
				System.out.println();
				System.out.print("$");
				// All the input is texted after this prompt "$".

				call.command = new StringTokenizer(call.keyboard.nextLine(), ", ");
				// This is to recover the words or tokens in a multi-word String.

				order = call.command.nextToken();

				if (order.equals("addplayer") || order.equals("addaiplayer")) {
					/*
					 * When the order is "addplayer" or "addaiplayer", excute the addplayer command,
					 * i.e., call the Addplayer method.
					 */
					try {
						if (call.command.countTokens() != call.validargu) {
							throw new InvalidNumberofArgumentsException();
						}
						call.Addplayer(order, call.command.nextToken(", "), call.command.nextToken(", "),
								call.command.nextToken(", "), call.player);
					} catch (InvalidNumberofArgumentsException e) {
						System.out.println(e.getMessage());
					}

				} else if (order.equals("removeplayer")) {
					/*
					 * When the order is "removeplayer", excute the removeplayer command, i.e., call
					 * the Removeplayer method.
					 */
					try {
						if (call.command.countTokens() > call.highestargu) {
							throw new InvalidNumberofArgumentsException();
						}
						call.Removeplayer(call.command, call.player, call.keyboard);
					} catch (InvalidNumberofArgumentsException e) {
						System.out.println(e.getMessage());
					}

				} else if (order.equals("editplayer")) {
					/*
					 * When the order is "editplayer", excute the editplayer command, i.e., call the
					 * Editplayer method.
					 */
					try {
						if (call.command.countTokens() != call.validargu) {
							throw new InvalidNumberofArgumentsException();
						}
						call.Editplayer(call.command.nextToken(", "), call.command.nextToken(", "),
								call.command.nextToken(", "), call.player);
					} catch (InvalidNumberofArgumentsException e) {
						System.out.println(e.getMessage());
					}

				} else if (order.equals("displayplayer")) {
					/*
					 * When the order is "displayplayer", excute the displayplayer command, i.e.,
					 * call the Displayplayer method.
					 */
					try {
						if (call.command.countTokens() > call.highestargu) {
							throw new InvalidNumberofArgumentsException();
						}
						call.Displayplayer(call.command, call.player);
					} catch (InvalidNumberofArgumentsException e) {
						System.out.println(e.getMessage());
					}

				} else if (order.equals("startgame")) {
					/*
					 * When the order is "startgame", excute the startgame command, i.e., call the
					 * Startgame method.
					 */
					try {
						if (call.command.countTokens() != call.startgameargu) {
							throw new InvalidNumberofArgumentsException();
						}
						call.Startgame(call.command, call.player, call.keyboard);
					} catch (InvalidNumberofArgumentsException e) {
						System.out.println(e.getMessage());
					}

				} else if (order.equals("resetstats")) {
					/*
					 * When the order is "resetstats", excute the resetstats command, i.e., call the
					 * Resetstats method.
					 */
					try {
						if (call.command.countTokens() > call.highestargu) {
							throw new InvalidNumberofArgumentsException();
						}
						call.Resetstats(call.command, call.player, call.keyboard);
					} catch (InvalidNumberofArgumentsException e) {
						System.out.println(e.getMessage());
					}

				} else if (order.equals("rankings")) {
					/*
					 * When the order is "rankings", excute the rankings command, i.e., call the
					 * Rankings method.
					 */
					try {
						if (call.command.countTokens() > call.highestargu) {
							throw new InvalidNumberofArgumentsException();
						}
						call.Rankings(call.command, call.player);
					} catch (InvalidNumberofArgumentsException e) {
						System.out.println(e.getMessage());
					}

					// While the order is not valid, it will throw an exception.
				} else if (!order.equals("exit")) {
					throw new InvalidCommandException(order);
				}
			} catch (InvalidCommandException c) {
				System.out.println("'" + c.getMessage() + "' is not a valid command.");
			}

		} while (!order.equals("exit"));
		System.out.println();
		/*
		 * When the order is "exit", exit the while loop and excute the exit command,
		 * i.e., call the Exit method.
		 */
		call.Exit();
	}

	private void Addplayer(String Order, String Username, String Familyname, 
							String Givenname, NimPlayer[] Player) {
		// This method is to perform addplayer operation.

		if (playerindex != 0) {
			for (int i = 0; i < playerindex; i++) {
				if (Username.equals(Player[i].getName())) {
					System.out.println("The player already exists.");
					break;
				}

				if (i == playerindex - 1) {
					if (Order.equals("addplayer")) {
						Player[playerindex] = new NimHumanPlayer(Username, Familyname, Givenname);
						playerindex = playerindex + 1;
						break;
					}
					if (Order.equals("addaiplayer")) {
						Player[playerindex] = new NimAIPlayer(Username, Familyname, Givenname);
						playerindex = playerindex + 1;
						break;
					}
				}
			}
		} else {
			if (Order.equals("addplayer")) {
				Player[playerindex] = new NimHumanPlayer(Username, Familyname, Givenname);
				playerindex = playerindex + 1;
			} else {
				Player[playerindex] = new NimAIPlayer(Username, Familyname, Givenname);
				playerindex = playerindex + 1;
			}

		}
	}

	private void Removeplayer(StringTokenizer Command, NimPlayer[] Player, 
								Scanner Keyboard) {
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

	private void Editplayer(String userName, String Familyname, 
							String Givenname, NimPlayer[] Player) {
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
							Player[i].getName() + "," + Player[i].getGivenname() + "," 
							+ Player[i].getFamilyname() + "," + Player[i].getGamenumber() 
							+ " games," + Player[i].getWinnumber() + " wins");
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
			NimPlayer[] Temp = new NimPlayer[100];
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

				for (int i = 0; i < playerindex; i++) {
					Temp[i] = Player[i];
				}

				for (int i = 0; i <= playerindex - 2; i++) {
					int max = i;
					for (int j = i + 1; j <= playerindex - 1; j++) {
						if (Player[max].getPercentage() < Player[j].getPercentage()) {
							max = j;
						}
					}
					NimPlayer temp = Player[max];
					Player[max] = Player[i];
					Player[i] = temp;
				}

				for (int i = 0; i <= playerindex - 2; i++) {
					int min = i;
					for (int j = i + 1; j <= playerindex - 1; j++) {
						if (Temp[min].getPercentage() > Temp[j].getPercentage()) {
							min = j;
						}
					}
					NimPlayer temp = Temp[min];
					Temp[min] = Temp[i];
					Temp[i] = temp;
				}
			} else {
				Temp[0] = Player[0];
			}

			if (Command.hasMoreElements()) {
				String nextorder = Command.nextToken();
				if (nextorder.equals("asc")) {
					for (int i = 0; i < playerindex && i < 10; i++) {
						System.out.printf("%-4s %s %02d %s %s %s", Temp[i].getPercentageformat(), 
							"|", Temp[i].getGamenumber(), "games |", Temp[i].getGivenname(), 
							Temp[i].getFamilyname());
						System.out.println();

					}
				} else if (nextorder.equals("desc")) {
					for (int i = 0; i < playerindex && i < 10; i++) {
						System.out.printf("%-4s %s %02d %s %s %s", Player[i].getPercentageformat(), 
							"|", Player[i].getGamenumber(), "games |", Player[i].getGivenname(),
							Player[i].getFamilyname());
						System.out.println();
					}
				}
			} else {
				for (int i = 0; i < playerindex && i < 10; i++) {
					System.out.printf("%-4s %s %02d %s %s %s", Player[i].getPercentageformat(), 
						"|", Player[i].getGamenumber(), "games |", Player[i].getGivenname(), 
						Player[i].getFamilyname());
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
			NimPlayer temp = Player[min];
			Player[min] = Player[i];
			Player[i] = temp;
		}
		return Player;
	}

	private void Startgame(StringTokenizer Command, NimPlayer[] Player, Scanner Keyboard) {
		// This method is to perform startgame operation.
		game = new NimGame(Integer.parseInt(Command.nextToken(", ")), 
							Integer.parseInt(Command.nextToken(", ")),
							Command.nextToken(", "), Command.nextToken(", "));
		game.Gameprocess(Player, Keyboard, playerindex);
	}

	private void Exit() {
		/*
		 * This method is to perform exit operation, i.e., end the program. Before the
		 * ending, the statistics will write to the file "players.dat".
		 */
		try {
			PrintWriter output = new PrintWriter(new FileOutputStream("players.dat"));
			if (playerindex > 0) {
				for (int i = 0; i < playerindex; i++) {
					output.println(player[i].getIsAI() + "\n" + player[i].getName() + 
						"\n" + player[i].getFamilyname() + "\n" + player[i].getGivenname() + 
						"\n" + player[i].getGamenumber() + "\n" + player[i].getWinnumber());
				}
			}
			output.close();
		} catch (FileNotFoundException e) {
			System.out.println("Problem opening files.");
		}
		System.exit(0);
	}
}