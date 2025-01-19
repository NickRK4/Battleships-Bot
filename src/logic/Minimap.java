package logic;

public class Minimap extends Grid{
	
	public Minimap(){
		board = new Boolean[10][];
		for (int i = 0; i<10; i++) {
			board[i] = new Boolean[10];
			for (int j = 0; j < 10; j++) {
				board[i][j] = null;
			}
		}
	}
	
	public void printBoard(Player other) {
		int i = 1;
		System.out.println("   🇦 🇧 🇨 🇩 🇪 🇫 🇬 🇭 🇮 🇯");
		for (int row = 0; row < 10; row++) {
			System.out.printf("%2d", i);
			for (int col = 0; col < 10; col++) {
				if (board[row][col] == null) {
					System.out.print("|⬜️");
				}
				else if (board[row][col] && other.getBoard().getShipAtCoordinate(row, col).isSunk()) {
					System.out.print("|🏴‍☠️️");
				}
				else if (!board[row][col]){
					System.out.print("|️❌");
				}
				else {
					System.out.print("|✅");
				}
			}
			i++;
			System.out.println();
		}
	}
	
	
	
}
