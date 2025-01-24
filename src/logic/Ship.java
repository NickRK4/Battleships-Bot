package logic;

import java.util.ArrayList;

public class Ship {
	
	// a ship is just a wrapper class for a group of coordinates
	private ArrayList<Integer> coordinates = new ArrayList<Integer>();
	// checks if each point is floating
	private ArrayList<Boolean> floatingPoints = new ArrayList<Boolean>();
	
	public Ship(int location, char o, int l) {
		setShip(location, o, l);
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
	
	public boolean getIsFloating(int position) {
		return floatingPoints.get(coordinates.indexOf(Integer.valueOf(position)));
	}
	
	
	
	// sets the ship after confirming that it can be placed
	public void setShip(int location, char orientation, int length){
		// Sets the starting point for the boat)
		coordinates.add(location);
		floatingPoints.add(true);
		if (orientation == 'v') {
			// for vertical 
			for (int i = 1; i < length; i++) {
				coordinates.add(location-10*i);
				floatingPoints.add(true);
			}
		} else {
			// for horizontal
			for (int i = 1 ; i < length ; i++) {
				coordinates.add(location+i);
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

