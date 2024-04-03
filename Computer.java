package theGame;

public class Computer extends Player {

	// DATA MEMBERS
	private int[][] crossAlgo = new int[10][10];

	// Default Constructor:
	public Computer(Board b) {
		super(b);
		crossAlgo();
	}

	// Getting Random Coordinate:
	private int[] rand(Coordinate[][] p) {
		int x = (int) (Math.random() * 10);
		int y = (int) (Math.random() * 10);
		do {
			x = (int) (Math.random() * 10);
			y = (int) (Math.random() * 10);
		} while (!(p[x][y].getLetter() == '~'));
		return new int[] { x, y };
	}

	// Smart Part Helper-
	/*****************************************************************************
	 * If a Hit have been found on the board, this thing will attack near the hits
	 * to find if thier are more hits available
	 *****************************************************************************/
	private int[] simple(Player c) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (c.getBoardB()[i][j].getLetter() == 'H') {
					if (i > 0 && c.getBoardB()[i - 1][j].getLetter() == '~')
						return new int[] { i - 1, j };
					if (j > 10 && c.getBoardB()[i][j - 1].getLetter() == '~')
						return new int[] { i, j - 1 };
					if (i < 9 && c.getBoardB()[i + 1][j].getLetter() == '~')
						return new int[] { i + 1, j };
					if (j < 9 && c.getBoardB()[i][j + 1].getLetter() == '~')
						return new int[] { i, j + 1 };
				}
			}
		}
		return rand(c.getBoardB());
	}

	// Making a cross Sign!
	private void crossAlgo() {
		int j = 9;
		for (int i = 0; i < 10; i++) {
			crossAlgo[i][i] = 1;
			crossAlgo[i][j] = 1;
			j--;
		}
	}

	// Making sure if their are no place to hit for cross Algorithm!
	private boolean crossAlgoCheck() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (crossAlgo[i][j] == 1) {
					return true;
				}
			}
		}
		return false;
	}

	// If their is a place to hit, it will hit for a coordinate!
	private int[] lookOne() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (crossAlgo[i][j] == 1) {
					crossAlgo[i][j] = 0;
					return new int[] { i, j };
				}
			}
		}
		return new int[] { 5, 5 };
	}

	// We can do:
	// Player vs Player
	// Computer vs Computer
	public void smartness(Player pc) {
		// Its gonna attack every cross
		if (crossAlgoCheck()) {
			int[] xy = lookOne();
			int k = xy[0];
			int j = xy[1];
			if (pc.getBoardA()[k][j].getLetter() != '~') {
				pc.getBoardB()[k][j].setLetter('H');

			} else {
				pc.getBoardB()[k][j].setLetter('M');
			}
			return;
		}

		/*******************************************************************************
		 * After all the cross position have been hit, some educated guess will be made
		 *******************************************************************************/
		int[] s = simple(pc);
		int x = s[0];
		int y = s[1];

		if (pc.getBoardA()[x][y].getLetter() != '~') {
			pc.getBoardB()[x][y].setLetter('H');

		} else {
			pc.getBoardB()[x][y].setLetter('M');
		}
	}
}