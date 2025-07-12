package logic;
import java.util.ArrayList;

public class Board extends Grid {	
	public void printBoard() {
		System.out.println("   🇦| 🇧| 🇨| 🇩| 🇪| 🇫| 🇬| 🇭| 🇮| 🇯|");
		int row = 1;
		for (int i = 0; i < 100; i++) {
			if (i % 10 == 0) {
				System.out.printf("%2d ", row);
				row++;
			}
			if (getShipAtCoordinate(i) == null || !getShipAtCoordinate(i).getIsFloating(i)) {
				System.out.print("⬛️|");
			} else {
				System.out.print("🚢|");
				}
			if ((i+1) % 10 == 0) {
				System.out.println();
			}
		}
	}
}
