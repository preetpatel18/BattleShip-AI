package theGame;

public class Ship {
	// DATA MEMBERS
	// Bascially all the attributes of the Ship!
	private String name;
	private char letter;
	private int length;
	private boolean facingSide;

	// DEFAULT CONSTRUCTOR
	public Ship(String nam, char let, int size, boolean side) {
		name = nam;
		letter = let;
		length = size;
		facingSide = side;

	}

	// SETTERS/GETTERS
	public char getLetter() {
		return letter;
	}

	public int getLength() {
		return length;
	}

	public boolean getFacingSide() {
		return facingSide;
	}

	public void setLetter(char c) {
		letter = c;
	}

	public void setLength(int l) {
		length = l;
	}

	public void setFacingSide(boolean b) {
		facingSide = b;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}