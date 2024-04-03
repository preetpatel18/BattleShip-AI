package theGame;

import java.util.Scanner;

public class BattleShip {

	// Objects:
	static Board playerBoard = new Board();
	static Board computerBoard = new Board();
	static Board comBotTester = new Board();
	static Player user = new Human(playerBoard);

	// Robots
	static Computer jeff = new Computer(computerBoard);
	static Computer alice = new Computer(comBotTester);

	// Scanner
	static Scanner scn = new Scanner(System.in);

	// Main Method
	public static void main(String[] args) {
		pln("How do you want to play this game?\n1) Computer V/S Computer\n2) Player V/S Computer");
		int play = scn.nextInt();
		if (play == 1) {
			cPlayC();
		} else if (play == 2) {
			menu();
		}
	}

	// Computer vs Computer
	// Thier is no User Interection
	public static void cPlayC() {
		int rounds = 0;
		jeff.addingRandShips();
		alice.addingRandShips();
		while (!(jeff.verify(alice)) && !(alice.verify(jeff))) {
			pln("JEff");
			jeff.smartness(alice);
			comBotTester.printABoard();
			comBotTester.printBBoard();

			if ((jeff.verify(alice))) {
				pln("Jeff Won");
				break;
			} else if (alice.verify(jeff)) {
				pln("Alice Won");
				break;
			}

			pln("Bot ");
			alice.smartness(jeff);
			computerBoard.printABoard();
			computerBoard.printBBoard();

			if (jeff.verify(alice)) {
				pln("Jeff Won");
				break;
			} else if (alice.verify(jeff)) {
				pln("Alice Won");
				break;
			}
			rounds++;
			pln("" + rounds);
			System.out.println("******************************************************************************");
		}
		pln("" + rounds);
	}

	// Menu Method - Adding Ships or not if a player wish to play!
	public static void menu() {
		pln("Would you like to add your ships:");
		pln("1- Randomize");
		pln("2- Do it yourself");
		int choice = scn.nextInt();

		if (choice == 1) {
			user.addingRandShips();
			jeff.addingRandShips();
		} else if (choice == 2) {
			user.addingShips();
			jeff.addingRandShips();
		}
		pln("***********************************");
		pln("	Lets Begin the play!	");
		pln("***********************************");
		play();
	}

	// This method called play is basically determines when the game ends for Player vs Computer!
	// It is Player VS Computer Method
	public static void play() {
		playerBoard.printABoard();
		while (!(jeff.verify(user)) && !(user.verify(jeff))) {
			pln("Please enter the coordinates where you wish to attack");
			pln("Y-Value");
			int y = scn.nextInt();
			pln("X-Value");
			int x = scn.nextInt();

			while ((x >= 10 || y >= 10) || (x < 0 || y < 0)) {
				pln("Please enter a valid number!");
				x = scn.nextInt();
				y = scn.nextInt();
			}
			user.fireUpon(jeff, x, y);
			computerBoard.printBBoard();

			jeff.smartness(user);
			playerBoard.printABoard();
			playerBoard.printBBoard();
		}
		won(!(user.verify(jeff)));
	}

	// Just pops up if you win or not!
	public static void won(boolean b) {
		if (b) {
			pln("Congratulation you won");
		} else {
			pln("Better luck next time, you played well.");
		}
	}

	// System.out.println(); method!
	public static void pln(String s) {
		System.out.println(s);
	}
}
