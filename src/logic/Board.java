	package logic;

import java.util.ArrayList;

public class Board extends Grid {	
	
	
	// constructs the board (a 2x2 grid of booleans where True is a ship pixel)
	public Board(){
		board = new Boolean[10][];
		for (int i = 0; i<10; i++) {
			board[i] = new Boolean[10];
			for (int j = 0; j < 10; j++) {
				board[i][j] = false;
			}
		}
	}
	
	public void resetBoard() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				setCoordinate(i, j, false);
			}
		}
	}
	
	public void printBoard() {
		int i = 1;
		System.out.println("   🇦 🇧 🇨 🇩 🇪 🇫 🇬 🇭 🇮 🇯");
		for (Boolean[] row : board) {
			System.out.printf("%2d", i);
			for (Boolean col : row) {
				if (col) {
					System.out.print("|🚢️");
				}
				else {
					System.out.print("|⬛️️");
				};
			}
			i++;
			System.out.println();
		}
	}
	
	public void addShip(int row, int col, char orientation, int length) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
		// error check for placing the ships
		
		// throws this if the ship is out of bounds but an invalid orientation
		if (!(orientation == 'v' || orientation == 'h')) {
			throw new IllegalArgumentException("Invalid orientation");
		}
		// throws this error if the ship is out of bounds
		if ((col + length-1 > 9 && orientation == 'h') || (row - length + 1 < 0 && orientation == 'v')) {
			throw new IllegalArgumentException("Your boat cannot fit on the board");
		}
		
		if (orientation == 'v') {
			// performs a check of the successive coordinates to check for no overlapping points
			for (int i = row; i > row - length; i--) {
				if (getCoordinate(i, col)) {
					throw new IllegalArgumentException("Cannot place a ship over another ship");
				}
			}
			// sets the ship otherwise
			for (int i = row; i > row - length; i--) {
				setCoordinate(i, col, true);
			}
		} else {
			for (int i = col; i < col + length; i++) {
				if (getCoordinate(row, i)) throw new IllegalArgumentException("Cannot place a ship over another ship");
			}
			// sets the ship otherwise
			for (int i = col ; i < col + length ; i++) {
				setCoordinate(row, i, true);
			}
		}
		ships.add(new Ship(row, col, orientation, length));
		
	}
	
	
	public ArrayList<Ship> getShips(){
		return this.ships;
	}
		
	public boolean hasShips() {
		int sunkShips = 0;
		for (Ship s: this.getShips()) {
			if (s.isSunk()) {
				sunkShips++;
			}
		}
		return sunkShips < 5;
	}
	
	
}
