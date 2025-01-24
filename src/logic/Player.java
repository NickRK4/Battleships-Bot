package logic;

import java.util.ArrayList;

public class Player {
	// each player has its own "attempted squares"
	// each player has its own board
	// each player can strike another player's board
	// each player can adjust its own mini-map
	private Board board;
	private Minimap minimap;
	
	// helpful information for the robot and for printing the mini-map
	protected ArrayList<Integer> hitPixels = new ArrayList<Integer>();
	protected ArrayList<Integer> unknownPixels = new ArrayList<Integer>();
	protected ArrayList<Integer> missedPixels = new ArrayList<Integer>();
	
	public Player(Board board, Minimap minimap) {
		this.board = board;
		this.minimap = minimap;
		for (int i = 0; i < 100; i++) {
			unknownPixels.add(i);
		}
	}
	
	public Board getBoard() {
		return board;
	}
	
	
	public Minimap getMinimap() {
		return minimap;
	}
	
	// Strikes another player's board
	// Set's the opponents pixel to false if it is true (i.e. there is a ship)
	// Set's the player's mini-map to true
	
	public void strike(Player other, int row, int col) throws IllegalArgumentException {
		unknownPixels.remove(Integer.valueOf(PointDecoder.encode(row, col)));
		// if you strike the opponent's ship
		/*
		 * Should always remove the pixel from the unknown pixels, since the pixel 'becomes known'
		 * Print "Strike"
		 * Add the point to hitPixels and remove from unknownPixels
		 * Set the opponent's ship pixel to false, to 'sink' the square
		 * Remove the pixel from the list of pixels corresponding to the struck boat
		 * Adjust your mini-map pixel with that square to be true
		 */
		
		// if you strike the ship's pixel
		
		if (other.getBoard().get)
		
		
		
		
		
		
		if (other.getBoard().getPixelAtCoordinate()) {
			System.out.println("Strike");			
			hitPixels.add(Integer.valueOf(PointDecoder.encode(row, col)));
			other.getBoard().setCoordinate(row, col, false);
			
			// long line of code to 'change' the ship's truth value at an index 
			other.getBoard().getShipAtCoordinate(row, col).setIsFloating(PointDecoder.encode(row, col), false);
			minimap.setCoordinate(row, col, true);
			return;
		}
		else {
			// otherwise, print "miss", add this point to the missed pixels, and adjust minimap
			System.out.println("Miss...Lame");
			missedPixels.add(Integer.valueOf(PointDecoder.encode(row, col)));
			minimap.setCoordinate(row, col, false);
			return;
		}
	}
	
	
	
	
	
	// runs all the checks manually before placing a ship; 3 checks:
	// 1) Orientation
	// 2) Ship is within bounds
	// 3) Coordinate is within bounds
	
	
	
	
	
	public boolean canPlace(int row, int col, char orientation, int length) {
		if (row < 0 || col < 0 || row > 9 || col > 9) return false;
		
		 if (!(orientation == 'v' || orientation == 'h')) return false;	
		    
		    // Check if the ship is out of bounds
		    if ((col + length - 1 > 9 && orientation == 'h') || (row - length + 1 < 0 && orientation == 'v')) {
		        return false;
		    }

		    // Now converts the point into a point
		    int p = PointDecoder.encode(row, col);
		    
		    // Check for overlaps
		    if (orientation == 'v') {
		        // Check vertical overlap
		        for (int i = row; i > row - (length*10); i-=10) {
		        	/*
		        	 * We need a method here that automatically checks if a ship exists
		        	 * At a given coordinate already
		        	 * I think there is a getShipAtCoord() method for this
		        	 */		        	
		            if (getBoard().getShipAtCoordinate(i) != null) {
		                return false; // Ship would overlap with an existing ship
		            }
		        }
		    } else {
		        // Check horizontal overlap
		        for (int i = col; i < col + 10; i++) {
		            if (getBoard().getShipAtCoordinate(i) != null) {
		                return false; // Ship would overlap with an existing ship
		            }
		        }
		    }
		    return true; // If no issues, the ship can be placed
	}
}
