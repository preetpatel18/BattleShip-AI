package theGame;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {

	// DATA MEMBERS
	// All the Ships objects:
	private Ship aircraftCarrier = new Ship("Air-Craft Carrier", 'A', 5, true);
	private Ship battleShip = new Ship("Battle Ship ", 'B', 4, true);
	private Ship submarine = new Ship("Submarine", 'S', 3, true);
	private Ship destroyer = new Ship("Destroyer", 'D', 2, true);
	private Ship patrolBoat = new Ship("Patrol Boat", 'P', 6, true);

	private Scanner scn = new Scanner(System.in);
	private Board gameBoard;
	private ArrayList<Ship> shipObjects = new ArrayList<Ship>();

	// Default Constructor
	public Player(Board b) {
		gameBoard = b;
		shipObjects.add(aircraftCarrier);
		shipObjects.add(battleShip);
		shipObjects.add(destroyer);
		shipObjects.add(submarine);
		shipObjects.add(patrolBoat);
	}

	// Getters and Setters
	public Coordinate[][] getBoardA() {
		return gameBoard.getABoard();
	}

	public Coordinate[][] getBoardB() {
		return gameBoard.getBBoard();
	}

	public ArrayList<Ship> getShipObjects() {
		return shipObjects;
	}

	// Fire in the computer B Board from player perspective.
	public char fireUpon(Computer c, int x, int y) {

		if (c.getBoardA()[x][y].getLetter() != '~') {
			c.getBoardB()[x][y].setLetter('H');

			return 'H';
		}
		c.getBoardB()[x][y].setLetter('M');

		return 'M';
	}

	// Verify if enemies boat is destroyed or not!
	// true for yes, else false
	public boolean verify(Player p) {
		for (int i = 0; i < p.getBoardA().length; i++) {
			for (int j = 0; j < p.getBoardA().length; j++) {
				if (p.getBoardA()[i][j].getLetter() != '~') {
					if (p.getBoardB()[i][j].getLetter() == '~') {
						return false;
					}
				}
			}
		}
		return true;
	}

	public void addingRandShips() {
		pln("********************************************************");
		int i = 0;
		for (i = 0; i < 5; i++) {
			/****************************************************************************
			 * Which Computer wishes wish to add. ("0. Aircraft Carrier Ship Name(A)"); ("1.
			 * Battle Ship Ship Name(B)"); ("2. Submarine Ship Name(S)"); ("3. Destroyer
			 * Ship Name(D)"); ("4. Patrol Boat Ship Name(P)");
			 ******************************************************************************/
			int num = ShipInfo();

			// Side ways or Not
			// 1 = yes/ 0 = no
			int yesNo = (int) (Math.random() * 2);

			// Making the ship!
			Ship s = whichShip(num);
			int size = s.getLength();
			if (yesNo == 1) {
				s.setFacingSide(true);
			} else {
				s.setFacingSide(false);
			}

			/***************************************************************************
			 * Where do I want to place it! Their will be boundaris each time a size is
			 * created, the grid area will changed based upon the size the ship is needed
			 *****************************************************************************/
			int boundary = gameBoard.getABoard().length - size;
			int x = 0;
			int y = 0;
			/********************************************************************************
			 * I am just adjusting the boundaries based on if it up and down or side by
			 * side. If it is side by side, then their is restriction in y value or else if
			 * it is up and down then their is restriction in the x values.
			 ********************************************************************************/
			if (yesNo == 1) {// If Side ways
				x = (int) (Math.random() * 10);
				y = (int) (Math.random() * boundary);
			} else {// Else up-down ways
				x = (int) (Math.random() * boundary);
				y = (int) (Math.random() * 10);
			}

			gameBoard.AddingRandomShips(x, y, s);
		}
	}

	// Adding ships into the into the players A board
	public void addingShips() {
		ArrayList<Ship> shipLength = new ArrayList<Ship>();
		int i = 0;
		while (i < 5) {
			// Which ship we are going to add is
			int num = ShipInfo();

			// Info is basically print info of all the ship as the play added the ship by
			// entring coordinates
			pln("We will be adding the Ship" + info(num));

			// Side ways or Not
			pln("Please Enter if the Ship should be side ways: \nYes-(1) | No-(2): ");
			int yesNo = scn.nextInt();
			while ((yesNo < 1) || (yesNo > 2)) {
				pln(yesNo + " is an invalid input- Please intput the correct Number!\\nYes-(1) | No-(2):");
				yesNo = scn.nextInt();
			}

			// Making the ship!
			Ship s = whichShip(num);
			if (yesNo == 1) {
				s.setFacingSide(true);
			} else {
				s.setFacingSide(false);
			}

			// Where do I want to place it!
			pln("Your Ship is successfully created.\nWhere do you want to place it");
			pln("Enter the coordinates(x, y)");
			int x = scn.nextInt();
			int y = scn.nextInt();

			gameBoard.addPlayerShip(x, y, s);
			shipLength.add(s);
			gameBoard.printABoard();

			pln("********************************************************");
			i++;
		}
	}

	// Helper of adding Ships method
	private String info(int num) {
		String name = "";
		int size = -1;
		char letter = 'L';
		if (num == 1) {
			name = aircraftCarrier.getName();
			size = aircraftCarrier.getLength();
			letter = aircraftCarrier.getLetter();
		} else if (num == 2) {
			name = battleShip.getName();
			size = battleShip.getLength();
			letter = battleShip.getLetter();
		} else if (num == 3) {
			name = submarine.getName();
			size = submarine.getLength();
			letter = submarine.getLetter();
		} else if (num == 4) {
			name = destroyer.getName();
			size = destroyer.getLength();
			letter = destroyer.getLetter();
		} else if (num == 5) {
			name = patrolBoat.getName();
			size = patrolBoat.getLength();
			letter = patrolBoat.getLetter();
		}

		return "\nName: " + name + "\nSize: " + size + "\nLetter: " + letter + " ";
	}

	// Helper of adding Ships method
	private int ShipInfo() {
		int result = 0;
		if (letterChecker(aircraftCarrier.getLetter())) {
			result = 1;
		} else if (letterChecker(battleShip.getLetter())) {
			result = 2;
		} else if (letterChecker(submarine.getLetter())) {
			result = 3;
		} else if (letterChecker(destroyer.getLetter())) {
			result = 4;
		} else if (letterChecker(patrolBoat.getLetter())) {
			result = 5;
		}
		return result;
	}

	// Helper of adding Ships method
	private boolean letterChecker(char c) {
		for (int i = 0; i < getBoardA().length; i++) {
			for (int j = 0; j < getBoardA().length; j++) {
				if (getBoardA()[i][j].getLetter() == c) {
					return false;
				}
			}
		}
		return true;
	}

	// Adding Ship Helper - returns which ship out of 5 numbers
	public Ship whichShip(int num) {
		if (num == 1) {
			return aircraftCarrier;
		} else if (num == 2) {
			return battleShip;
		} else if (num == 3) {
			return submarine;
		} else if (num == 4) {
			return destroyer;
		} else if (num == 5) {
			return patrolBoat;
		}
		return null;
	}

	// Adding Ship Helper- Ship numbers
	public void shipNumbers() {
		String[] s = { "1. Aircraft Carrier	Ship Name(A)	Ship Length(5)",
				"2. Battle Ship		Ship Name(B)	Ship Length(3)", "3. Submarine		Ship Name(S)	Ship Length(4)",
				"4. Destroyer		Ship Name(D)	Ship Length(2)",
				"5. Patrol Boat		Ship Name(P)	Ship Length(6)" };
		for (String sl : s) {
			System.out.println(sl);
		}
	}

	// Pln Method!
	public void pln(String string) {
		System.out.println(string);

	}
}