package theGame;

public class Coordinate {
	// DATA MEMBERS
	private int x;
	private int y;
	private char letter;

	// DEFAULT CONSTRUCTOR
	public Coordinate(int a, int b, char c) {
		x = a;
		y = b;
		letter = c;
	}

	// GETTERS
	public char getLetter() {
		return letter;
	}

	// SETTERS
	public void setLetter(char c) {
		letter = c;
	}

	// TO STRING
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}