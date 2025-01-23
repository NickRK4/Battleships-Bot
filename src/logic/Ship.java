package logic;

import java.util.ArrayList;

public class Ship {
	
	// a ship is just a wrapper class for a group of coordinates
	private ArrayList<Integer> coordinates = new ArrayList<Integer>();
	// checks if each point is floating
	private ArrayList<Boolean> floatingPoints = new ArrayList<Boolean>();
	
	public Ship(int row, int col, char o, int l) {
		setShip(row, col, o, l);
	}
	
	public ArrayList<Integer> getShipCoords(){
		return coordinates;
	}
	
	public ArrayList<Boolean> getShipFloats(){
		return floatingPoints;
	}
	
	public void setIsFloating(int position, boolean b) {
		floatingPoints.set(coordinates.indexOf(Integer.valueOf(position)), b);
	}
	
	
	// sets the ship after confirming that it can be placed
	public void setShip(int row, int col, char orientation, int length){
		// Sets the starting point for the boat)
		int flatCoords = PointDecoder.encode(row, col);
		coordinates.add(flatCoords);
		floatingPoints.add(true);
		if (orientation == 'v') {
			// for vertical 
			for (int i = 1; i < length; i++) {
				coordinates.add(flatCoords-10*i);
				floatingPoints.add(true);
			}
		} else {
			for (int i = 1 ; i < length ; i++) {
				coordinates.add(flatCoords+i);
				floatingPoints.add(true);
			}
		}
	}
	
	public void printCoords() {
		for (int i : getShipCoords()) {
			System.out.print(i + ", ");
		}
		System.out.println();
	}
	
	public boolean isSunk() {
		for (Boolean b: this.floatingPoints) {
			if (b) {
				return false;
			}
		}
		return true;
	}
	
	
}

