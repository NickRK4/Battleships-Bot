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
		
	
	
}
