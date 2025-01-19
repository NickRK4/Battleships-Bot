package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomSelector {
	

	public double[][] createGrid(int n){
		double[][] arr= new double[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				arr[i][j] = 1;
			}
		}
		return arr;
	}
	
	
	public double[][] copyGrid(double[][] grid){
		double[][] newGrid = new double[grid.length][grid.length];
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				newGrid[i][j] = grid[i][j];
			}
		}
		return newGrid;
	}
	
	
	
	
	
	public static int[] selectRandom(double[][] probMatrix) {
		List<Double> weights = new ArrayList<Double>();
		List<int[]> candidates = new ArrayList<int[]>();
		
		for (int row = 0; row < probMatrix.length; row++) {
			for (int col = 0; col < probMatrix[row].length; col++) {
				if (probMatrix[row][col] > 0) {
					candidates.add(new int[]{row, col});
					weights.add(probMatrix[row][col]);
					
				}
			}
		}
		int index = weightedSelectorAlgorithm(weights);
		System.out.println("Chosen cell: " + candidates.get(index)[0] + " " + candidates.get(index)[1]);
		System.out.println("Probability of chosen boat: " + weights.get(index));
		return candidates.get(index);
	}
	
	
	// returns the index of the chosen 
	public static int weightedSelectorAlgorithm(List<Double> weights) {
		Random r = new Random();
		
		double totalWeight = weights.stream().mapToDouble(Double::doubleValue).sum();
		double randomValue = (r.nextDouble() * totalWeight);
		
		double cumulativeWeight = 0;
		for (int i = 0; i < weights.size(); i++) {
			cumulativeWeight += weights.get(i);
			if (cumulativeWeight >= randomValue) {
				return i;
			}
		}
		return weights.size() - 1;
	}
	

	// 
	
	
}

