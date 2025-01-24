package logic;

import java.util.ArrayList;

public class Minimap extends Grid{
	
	public ArrayList<Integer> hitPoints = new ArrayList<Integer>();
	public ArrayList<Integer> missedPoints = new ArrayList<Integer>();
	
	public void printBoard(Player other) {
		int row = 1;
		System.out.println("   🇦 🇧 🇨 🇩 🇪 🇫 🇬 🇭 🇮 🇯");
		for (int i = 0; i < 100; i++) {
			if (i % 10 == 0) {
				System.out.printf("%2d", row);
				row++;
			}
			if (missedPoints.contains(i)) {
				System.out.print("🟥|");
			}
			else if (hitPoints.contains(i) && other.getBoard().getShipAtCoordinate(i).isSunk()) {
				System.out.print("🏴‍☠️|");
			}
			else if (hitPoints.contains(i)) {
				System.out.print("🟩|");
			}
			else {
				System.out.print("🌊|");
			}
			if ((i+1) % 10 == 0) {
				System.out.println();
			}
		}
	}
}
