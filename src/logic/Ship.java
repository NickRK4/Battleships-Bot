package logic;

import java.util.ArrayList;

public class Ship {
	
	// a ship is just a wrapper class for a group of coordinates
	private ArrayList<Integer> coordinates = new ArrayList<Integer>();
	private ArrayList<Boolean> isFloating = new ArrayList<Boolean>();
	
	public Ship(int row, int col, char o, int l) {
		setShip(row, col, o, l);
	}
	
	public ArrayList<Integer> getShipCoords(){
		return coordinates;
	}
	
	public ArrayList<Boolean> getShipFloats(){
		return isFloating;
	}
	
	public void setIsFloating(int position, boolean b) {
		isFloating.set(coordinates.indexOf(Integer.valueOf(position)), b);
	}
	
	
	// sets the ship after confirming that it can be placed
	public void setShip(int row, int col, char orientation, int length){
		// Sets the starting point for the boat)
		int flatCoords = PointDecoder.encode(row, col);
		coordinates.add(flatCoords);
		isFloating.add(true);
		if (orientation == 'v') {
			// for vertical 
			for (int i = 1; i < length; i++) {
				coordinates.add(flatCoords-10*i);
				isFloating.add(true);
			}
		} else {
			for (int i = 1 ; i < length ; i++) {
				coordinates.add(flatCoords+i);
				isFloating.add(true);
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
		for (Boolean b: this.isFloating) {
			if (b) {
				return false;
			}
		}
		return true;
	}
	
	
}

