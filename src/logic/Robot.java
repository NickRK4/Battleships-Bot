package logic;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Robot extends Player {

	public static Random r = new Random();
	private ArrayList<Integer> robotPixels = new ArrayList<Integer>();
	
	private void setRobotPixels() {
		for (int row = 0; row < 10; row++) {
			if (row % 2 == 0) {
				for (int col = 0; col < 10; col+=2) {
					int index = PointDecoder.encode(row, col);
					robotPixels.add(index);
				}
			} else {
				for (int col = 1; col < 10; col+=2) {
					int index = PointDecoder.encode(row, col);
					robotPixels.add(index);
				}
			}
		}
	}
	
	private double[][] probabilityGrid;
	// constructs the board (a 2x2 grid of doubles where True is a ship pixel)

	public Robot(Board board, Minimap minimap) {
		super(board, minimap);
		setRobotPixels();
	}
	
	// function that creates a board where no ship touches the other
	public void createBoard() {
		Set<String> pair = new HashSet<String>();
		RandomSelector selector = new RandomSelector();
		
		probabilityGrid = selector.createGrid(10);
		int[] lengths = {2,3,3,4,5};
		
		for (int i: lengths) {
			boolean isPlaced = false;
			while (!isPlaced) {
				int row = r.nextInt(10);
				int col = r.nextInt(10);
				char orientation = r.nextBoolean() ? 'h' : 'v';
				// firstly, checks if the given pixel already has been attempted
				String key = row+"-"+col+"-"+orientation;
				if (pair.contains(key)) {
					continue;
				}
				else {
					pair.add(key); // adds this to the list
				}
				// checks if the given coordinates will work
				boolean canPlace = canPlace(row, col, orientation, i);
				if (canPlace) {
					boolean landsWithinBuffer = landsWithinBuffer(row, col, orientation, i);
					if (!landsWithinBuffer) {
						
						updateGrid(row, col, orientation, i); // updates the grid correctly
						getBoard().addShip(row, col, orientation, i); // adds the boat
						isPlaced = true;
					}
					else {
						continue;
					}
					} else continue;
				
			} // end of while loop
		} // end of for loop
	}
	
	
	// arguments are the ship's row, ship's column, length of the ship, orientation
	// tries to update the grid. Returns true if it updates successfully, returns false if not.
	private void updateGrid(int row, int col, char o, int length) {
		//RandomSelector s = new RandomSelector();
		//double[][] tempGrid = s.copyGrid(probabilityGrid); // deep copy of the current grid
        int startRow, endRow, startCol, endCol;
        if (o == 'h') {
            // Horizontal ship
            startRow = Math.max(0, row - 1);
            endRow = Math.min(9, row + 1);
            startCol = Math.max(0, col - 1);
            endCol = Math.min(9, col + length);
        } else {
            // Vertical ship
            startRow = Math.max(0, row - length);
            endRow = Math.min(9, row + 1);
            startCol = Math.max(0, col - 1);
            endCol = Math.min(9, col + 1);
        }

        // Create the buffer zone around the new ship
        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++) {
                probabilityGrid[i][j] = 0;
            }
        }
    }
	
	public boolean landsWithinBuffer(int row, int col, char o, int length) {
		if (o == 'v') {
			// performs a check of the coordinates to check for ships within the buffer
			for (int i = row; i > row - length; i--) {
				if (probabilityGrid[i][col] == 0) {
					return true;
				}
			}
		} else {
			for (int i = col; i < col + length; i++) {
				if (probabilityGrid[row][i] == 0) {
					return true;
				}
			}
		}
		return false;
	}
	// method to print the probability grid
	public void printGrid() {
		int i = 1;
		for (double[] row: probabilityGrid) {
			System.out.printf("%2d", i);
			for (double col : row) {
				if (col < 0.00001) {
					System.out.print("|🟦");
				}
				else {
					System.out.print("|⬛️");
				}
			}
			i++;
			System.out.println();
		}
		System.out.println();
	}
	
	
	
	
	// MAIN STRIKE FUNCTION
	
	// stores a list of all the diagonal pixels to the ship and ensures that they are dumped
	// from the unknownPixels at the beginning of each iteration
	
	// need to store the position of the ships!!
	
	public void strike(Player other) {
	    ArrayList<Integer> OneAndTwoAway = new ArrayList<>();
	    ArrayList<Integer> onlyOneAway = new ArrayList<>();

	    for (int u : unknownPixels) {
	        boolean isOneAway = 
	            (hitPixels.contains(u + 1) && !other.getBoard().getShipAtCoordinate(u + 1).isSunk()) ||
	            (hitPixels.contains(u - 1) && !other.getBoard().getShipAtCoordinate(u - 1).isSunk()) ||
	            (hitPixels.contains(u + 10) && !other.getBoard().getShipAtCoordinate(u + 10).isSunk()) ||
	            (hitPixels.contains(u - 10) && !other.getBoard().getShipAtCoordinate(u - 10).isSunk());

	        boolean isTwoAway = 
	            (hitPixels.contains(u + 2) && !other.getBoard().getShipAtCoordinate(u + 2).isSunk()) ||
	            (hitPixels.contains(u - 2) && !other.getBoard().getShipAtCoordinate(u - 2).isSunk()) ||
	            (hitPixels.contains(u + 20) && !other.getBoard().getShipAtCoordinate(u + 20).isSunk()) ||
	            (hitPixels.contains(u - 20) && !other.getBoard().getShipAtCoordinate(u - 20).isSunk());

	        if (isOneAway && isTwoAway) {
	            OneAndTwoAway.add(u);
	        } else if (isOneAway) {
	            onlyOneAway.add(u);
	        }
	    }

	    int chosenMove;
	    if (!OneAndTwoAway.isEmpty()) {
	        // Prioritize both 1 and 2 squares away
	        chosenMove = chooseRandom(OneAndTwoAway);
	    } else if (!onlyOneAway.isEmpty()) {
	        // If no high-priority moves, pick one that is 1 square away
	        chosenMove = chooseRandom(onlyOneAway);
	    } else {
	        // Otherwise, pick a random unknown pixel
	        chosenMove = chooseRandom(robotPixels);
	    }
	    // always remove the chosen pixel from robot pixels to prevent 'double guessing' a spot
	    if (robotPixels.contains(chosenMove)) {
        	robotPixels.remove(Integer.valueOf(chosenMove));
        }
	    
	    // Decode the chosen move
	    int[] decodedMove = PointDecoder.decode(chosenMove);
	    // Perform the strike
	    String[] humanCoords = getCoordinates(decodedMove);
	    System.out.println("Computer plays: " + humanCoords[1] + "" + humanCoords[0]);
	    super.strike(other, decodedMove[0], decodedMove[1]);
	}	
	/*
	private boolean isDiagonalToTruePixel(int guess) {
		for (int hit : hitPixels) {
			if (Math.abs(guess - hit) == 11 || Math.abs(guess - hit) == 9) {
				return true;
			}
		}
		return false;
	}
	*/
	
	// returns a valid integer from the list
	private int chooseRandom(ArrayList<Integer> list) {
		int guess = r.nextInt(list.size());
		boolean valid = false;
		while (!valid) {
			try {
				list.get(guess);
				valid = true;
			} catch (IndexOutOfBoundsException e) {
				guess = r.nextInt(list.size());
			}
		}
		return list.get(guess);
	}

	
	// helper method just to print out the coordinates
	public String[] getCoordinates(int[] rc) {
		String row = String.valueOf(rc[0]+1);
		String col = Character.toString(rc[1] + 65);
		
		return new String[] {row, col};
	}
	
	
}

