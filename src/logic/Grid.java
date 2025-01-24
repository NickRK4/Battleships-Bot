package logic;

import java.util.ArrayList;

public abstract class Grid {
		
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
		

		public void addShip(Ship s) {
			getShips().add(s);
		}
		
		public ArrayList<Ship> getShips(){
			return this.ships;
		}
	
		public void resetBoard() {
			ships.clear(); // clears all the ships
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
		
		public boolean isPixelAtCoordinate(int coord) {
			for (Ship s: this.getShips()) {
				// if a ship has this coordinate
				if ((s.getShipCoords().contains(coord))) {
					// find its index
					int index = s.getShipCoords().indexOf(coord);
					// if the index is true in boolean space, then there is a pixel there
						if (s.getShipFloats().get(index)) return true;
				}
			}
			
			return false;
		}
	
}
