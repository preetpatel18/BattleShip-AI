package theGame;

import java.util.*;

public class Board {
	// DATA MEMBERS
	private Coordinate[][] boardA = new Coordinate[10][10];
	private Coordinate[][] boardB = new Coordinate[10][10];
	private Scanner scn = new Scanner(System.in);

	// DEFAULT CONSTRUCTOR
	public Board() {
		// Declaring/Initializing both the boards
		for (int i = 0; i < boardA.length; i++) {
			for (int j = 0; j < boardA[1].length; j++) {
				boardA[i][j] = new Coordinate(i, j, '~');
				boardB[i][j] = new Coordinate(i, j, '~');
			}
		}
	}

	// GETTERS/SETTERS
	public Coordinate[][] getABoard() {
		return boardA;
	}

	public Coordinate[][] getBBoard() {
		return boardB;
	}

	// EXTRA METHODS:
	// PRINTING THE BOARD-A
	public void printABoard() {
		System.out.print("  ");
		for (int i = 0; i < boardA.length; i++) {
			System.out.print(i + " ");
		}
		System.out.print("|");
		System.out.println();
		for (int i = 0; i < boardA.length; i++) {
			System.out.print("");
			System.out.print(i + " ");
			for (int j = 0; j < boardA[1].length; j++) {
				System.out.print(boardA[i][j].getLetter() + " ");
			}
			if (i == 4) {
				System.out.print("Y");
			} else
				System.out.print("|");
			System.out.println();
		}
		System.out.println("-----------X--------- +");
	}

	// PRINT THE BOARD-B
	public void printBBoard() {

		System.out.print("  ");
		for (int i = 0; i < boardB.length; i++) {
			System.out.print(i + " ");
		}
		System.out.print("|");
		System.out.println();
		for (int i = 0; i < boardB.length; i++) {
			System.out.print("");
			System.out.print(i + " ");
			for (int j = 0; j < boardB[1].length; j++) {
				System.out.print(boardB[i][j].getLetter() + " ");
			}
			if (i == 4) {
				System.out.print("Y");
			} else
				System.out.print("|");
			System.out.println();
		}
		System.out.println("-----------X--------- +");
	}

	/******************************************************************************
	 * ADDING SHIPs: Adding ships add the ship object in the A object Grid. It will
	 * return true of false if it was successful to add the ship anything becuase it
	 * is just adding the ships letter into the grid.
	 *******************************************************************************/
	public boolean addPlayerShip(int x, int y, Ship sh) {

		boolean a = (x >= 0 && x < boardA.length) && (y >= 0 && y < boardA[0].length)
				&& boardA[x][y].getLetter() != '~';
		boolean b = (x < 0 || x >= boardA.length) || (y < 0 || y >= boardA[0].length);

		boolean c = false;
		if (sh.getFacingSide()) {
			c = (x >= boardA.length) || (y + sh.getLength() >= boardA.length);
		} else if (!sh.getFacingSide()) {
			c = (x + sh.getLength() >= boardA.length) || (y >= boardA.length);
		}
		boolean d = false;
		boolean e = (x > boardA.length) || (y > boardA.length);

		while (e) {
			System.out.println("Invalid Input, your number is out of bound! \n(Enter X and Y value (x, y)");
			x = scn.nextInt();
			y = scn.nextInt();
			e = (x > boardA.length) || (y > boardA.length);
		}

		if (sh.getFacingSide()) {
			for (int i = y; i < sh.getLength(); i++) {
				if (boardA[x][i].getLetter() != '~') {
					d = true;
				}
			}
		} else if (!sh.getFacingSide()) {
			for (int i = x; i < sh.getLength(); i++) {
				if (boardA[y][i].getLetter() != '~') {
					d = true;
				}
			}
		}
		
		/*******************************************************************************
		 * Goes into while loop until a valid Coordinate have been written by the User!
		 ******************************************************************************/
		while (a || b || c || d) {
			if (a) {
				System.out.println(
						"You cannot place two or more chips on the same location- \n(Enter X and Y value (x, y)");
			} else if (b) {
				System.out.println("You can't place your Ship outside the board length \n(Enter X and Y value (x, y)");
			} else if (c) {
				System.out.println(
						"Your ship is going outside of the grid, please rewrite the coordinates!\n(Enter X and Y value (x, y)");
			} else if (d) {
				System.out.println(
						"Invalid Input, you cannot place two ships on top of each other. \n(Enter X and Y value (x, y)");
			} else if (e) {
				System.out.println("Invalid Input, your number is out of bound! \n(Enter X and Y value (x, y)");
			}
			x = scn.nextInt();
			y = scn.nextInt();

			if (sh.getFacingSide()) {
				c = (x >= boardA.length) || (y + sh.getLength() >= boardA.length);
			} else if (!sh.getFacingSide()) {
				c = (x + sh.getLength() >= boardA.length) || (y >= boardA.length);
			}

			a = (x >= 0 && x < boardA.length) && (y >= 0 && y < boardA[0].length) && boardA[x][y].getLetter() != '~';
			b = (x < 0 || x >= boardA.length) || (y < 0 || y >= boardA[0].length);
			e = (x >= boardA.length) || (y >= boardA.length);

			if (sh.getFacingSide()) {
				for (int i = y; i < sh.getLength(); i++) {
					if (boardA[x][i].getLetter() != '~') {
						d = true;
					}
				}
			} else if (!sh.getFacingSide()) {
				for (int i = x; i < sh.getLength(); i++) {
					if (boardA[i][y].getLetter() != '~') {
						d = true;
					}
				}
			}
		}
		if (sh.getFacingSide()) {
			for (int i = y; i < sh.getLength(); i++) {
				boardA[x][i].setLetter(sh.getLetter());
			}
			return true;
		} else if (!sh.getFacingSide()) {
			for (int i = x; i < sh.getLength(); i++) {
				boardA[i][y].setLetter(sh.getLetter());
			}
			return true;
		}
		return false;
	}

	/******************************************************************************
	 * ADDING SHIPs: Adding the ship object in the A object Grid. It will return
	 * true of false if it was successful to add the ship anything becuase it is
	 * just adding the ships letter into the grid, however this time it is randomly.
	 *******************************************************************************/
	public boolean AddingRandomShips(int x, int y, Ship sh) {

		// All the conditions for placing the ship
		boolean a = (x >= 0 && x < boardA.length) && (y >= 0 && y < boardA[0].length)
				&& boardA[x][y].getLetter() != '~';
		boolean b = (x < 0 || x >= boardA.length) || (y < 0 || y >= boardA[0].length);

		boolean c = false;
		if (sh.getFacingSide()) {
			for (int i = y; i < sh.getLength(); i++) {
				if (boardA[x][i].getLetter() != '~') {
					c = true;
					break;
				} else {
					c = false;
				}
			}
		} else if (!sh.getFacingSide()) {
			for (int i = x; i < sh.getLength(); i++) {
				if (boardA[y][i].getLetter() != '~') {
					c = true;
					break;
				} else {
					c = false;
				}
			}
		}

		/*****************************************************************
		 * Goes into while loop until a valid Coordinate have been found!
		 *****************************************************************/
		while (a || b || c) {
			int boundary = boardA.length - sh.getLength();
			if (sh.getFacingSide()) {// If Side ways
				x = (int) (Math.random() * 10);
				y = (int) (Math.random() * boundary);
			} else {// Else up-down ways
				x = (int) (Math.random() * boundary);
				y = (int) (Math.random() * 10);
			}

			a = (x >= 0 && x < boardA.length) && (y >= 0 && y < boardA[0].length) && boardA[x][y].getLetter() != '~';
			b = (x < 0 || x >= boardA.length) || (y < 0 || y >= boardA[0].length);

			if (sh.getFacingSide()) {
				for (int i = y; i < sh.getLength(); i++) {
					if (boardA[x][i].getLetter() != '~') {
						c = true;
						break;
					} else {
						c = false;

					}
				}
			} else if (!sh.getFacingSide()) {
				for (int i = x; i < sh.getLength(); i++) {
					if (boardA[y][i].getLetter() != '~') {
						c = true;
						break;
					} else {
						c = false;
					}
				}
			}
		}
		if (sh.getFacingSide()) {
			for (int i = 0; i < sh.getLength(); i++) {
				boardA[x][y].setLetter(sh.getLetter());
				y++;
			}
			return true;
		} else if (!sh.getFacingSide()) {
			for (int i = 0; i < sh.getLength(); i++) {
				boardA[x][y].setLetter(sh.getLetter());
				x++;
			}
			return true;
		}
		return false;
	}
}