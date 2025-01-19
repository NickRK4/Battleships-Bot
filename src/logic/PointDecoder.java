package logic;

public class PointDecoder {
	
	public PointDecoder() {};
	
	// converts a pixel from 1 to 100 into a a position (row, col) for the Boolean[][] board.
	public static int[] decode(int pixel) {
		int row = pixel / 10;
		int col = pixel % 10; 
		return new int[] {row, col};
	}
	
	public static int encode(int row, int col) {
		return (row * 10) + col;
	}
	
}
