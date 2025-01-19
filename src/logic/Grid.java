package logic;

import java.util.ArrayList;

public abstract class Grid {
	
	protected Boolean[][] board; // stores each "pixel" with a ship as true or false
	
	
	// method to set the coordinate at a given point on the grid
	public void setCoordinate(int row, int col, boolean b) throws ArrayIndexOutOfBoundsException{
		if (row < 0 || col < 0 || row > 9 || col > 9) {
			throw new ArrayIndexOutOfBoundsException("Invalid coordinate (out of bounds)");
		}	
		else {
			board[row][col] = b;
		}
	}
	
	// method that returns the value of a given pixel
	public boolean getCoordinate(int row, int col) throws ArrayIndexOutOfBoundsException{
		if (row < 0 || col < 0 || row > 9 || col > 9) {
			throw new ArrayIndexOutOfBoundsException("Invalid coordinate (out of bounds)");
		}
		else {
			return this.board[row][col];
		}
	}
	
	
	// encapsulates the ships too.
		protected ArrayList<Ship> ships = new ArrayList<Ship>();
		
		public Ship getShipAtCoordinate(int row, int col) {
			int position = PointDecoder.encode(row, col);
			for (Ship s : this.ships) {
				if (s.getShipCoords().contains(position)) {
					return s;
				}
			}
			return null;
		}
		
		public Ship getShipAtCoordinate(int index) {
			for (Ship s : this.ships) {
				if (s.getShipCoords().contains(Integer.valueOf(index))) {
					return s;
				}
			}
			return null;
		}
		
		public boolean shipAtCoordinate(int row, int col) {
			for (Ship s: ships) {
				if (s.getShipCoords().contains(Integer.valueOf(PointDecoder.encode(row, col)))) {
					return true;
				}
			}
			return false;
		}
		
	
	
}
