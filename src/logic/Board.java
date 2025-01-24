package logic;
import java.util.ArrayList;

public class Board extends Grid {	
	
	
	// constructs the board (a 2x2 grid of booleans where True is a ship pixel)
	
	
	public void resetBoard() {
		ships.clear(); // clears all the ships
	}
	public void printBoard() {
		System.out.println("   🇦 🇧 🇨 🇩 🇪 🇫 🇬 🇭 🇮 🇯");
		for (int i = 0; i < 100; i++) {
			if (i % 10 == 0) {
				System.out.println();
			}
			if (getShipAtCoordinate(i) == null || !getShipAtCoordinate(i).getIsFloating(i)) {
				System.out.print("⬛️");
			} else {
				System.out.println("🚢");
				}
		}
	}
	
	public void addShip(Ship s) {
		getShips().add(s);
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
	
	@SuppressWarnings("unlikely-arg-type")
	public boolean pixelAtCoordinate(int coord) {
		for (Ship s: this.getShips()) {
			// if a ship has this coordinate
			if ((s.getShipCoords().contains(coord))) {
				// find its index
				int index = s.getShipCoords().indexOf(Integer.valueOf(coord));
				// if the index is true in boolean space, then there is a pixel there
					if (s.getShipFloats().get(index)) return true;
			}
		}
		
		return false;
	}
	
	
}
