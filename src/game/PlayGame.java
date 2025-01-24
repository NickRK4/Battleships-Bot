package game;
import java.util.ArrayList;
import java.util.Scanner;
import logic.*;

public class PlayGame {
	
	public static Scanner scanner = new Scanner(System.in);
	
	static int[] getCoordinates() {
		Scanner scan = new Scanner(System.in);
		String[] response = scan.nextLine().split("");
		int col = String.valueOf(response[0]).toLowerCase().charAt(0) - 97;
		int row;
		
		if (response.length == 3) {
			String num = response[1] + response[2];
			row = Integer.parseInt(num);
		}
		else {
			row = (int) Integer.parseInt(response[1]);
		}

		return new int[] {row-1, col};
	}
	
	public static void main(String[] args) {
		Robot user = new Robot(new Board(), new Minimap());
		Robot opponent = new Robot(new Board(), new Minimap());
		
		int[] boatLengths = {2,3,3,4,5};
		
		System.out.println("Welcome to battleships, mofo!");
		System.out.println("Would you like to auto-generate a board (1), or enter it manually? (2)");
		
		int choice = Integer.parseInt(scanner.nextLine());
		while (choice != 1 && choice != 2) {
			System.out.println("Please select a valid choice!");
			choice = scanner.nextInt();
		}
		if (choice == 1) {
			boolean happy = false; // checks if the user is happy with the board
			while (!happy) {
				user.createBoard();
				user.getBoard().printBoard();
				System.out.println("Would you like to keep this board? Y/N");
				happy = (scanner.nextLine().toLowerCase().equals("y")) ? true : false;
			}
			
		} else {
			for (int length : boatLengths) {
				boolean isPlaced = false;
				while (!isPlaced) {
					 System.out.println("Place a ship of length " + length + ":");
					 
					 System.out.print("\nEnter your coordinates (e.g. A4): ");
					 int[] rowCol = getCoordinates();
	                 int row = rowCol[0];
	                 int col = rowCol[1];
	                 
	                 System.out.print("Enter orientation (h for horizontal, v for vertical): ");
	                 char orientation = scanner.next().toLowerCase().charAt(0);

	                 // Attempt to add the ship
	                 boolean canPlace = user.canPlace(row, col, orientation, length);
	                 // canPlace method returns false if it detects an error, otherwise returns true
	                 if (canPlace) {
	                	 System.out.println("Success");
	                	 isPlaced = true;
	                	 user.getBoard().addShip(new Ship(PointDecoder.encode(row, col), orientation, length));
	                	 user.getBoard().printBoard();
	                 }
	                 else {
	                	 System.out.println("Error");
	                 }
	                 
				} // end of while
			} // end of for loop
		}
		
		// creates the opponent's board
		opponent.createBoard();
		// stores the user's moves to warn against repeated moves
		ArrayList<Integer> userMoves = new ArrayList<Integer>();
		// empty reference
		Player winner;
		
		boolean playerTurn = true;
		while (user.getBoard().hasShips() && opponent.getBoard().hasShips()) {
			if (playerTurn) {
				System.out.println("\nYour board");
				user.getBoard().printBoard();
				System.out.println("Your minimap");
				user.getMinimap().printBoard(opponent);
				
				boolean validMove = false;
				while (!validMove) {
					System.out.println("Where would you like to strike?");
					try {
						int[] coords = getCoordinates();
						int row = coords[0];
						int col = coords[1];
						int point = PointDecoder.encode(row, col);
						if (userMoves.contains(point)) {
							System.out.println("You have already moved there!");
							continue;
						}
						else {
							userMoves.add(point);
							user.strike(opponent, point);
							validMove = true;
						}
					} catch (Exception e) {
						System.out.println("Illegal move!");
					}
				}
				playerTurn = false;
			}
			else {
				System.out.println("\nComputer is moving...");
				opponent.strike(user);
				playerTurn = true;
			}
		}
		if (user.getBoard().hasShips()) {
			winner = user;
		}
		else {
			winner = opponent;
		}
		
		System.out.println("\nGame over! " + winner.getClass().getSimpleName() + " wins!");
		user.getBoard().printBoard();
		opponent.getBoard().printBoard();
		
	} // end of main method
}
