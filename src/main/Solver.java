package main;

import java.util.ArrayList;

public class Solver {
	public boolean solve(int grid[][], int row, int col) {
		if (Sudoku.isSolved == true) {
			return false; // For multi-threading
		}
		Sudoku.solvable = false;
		while (grid[row][col] != 0) {
			row++;
			if (row > 8) {
				col++;
				if (col > 8 && row > 8) {
					Sudoku.finalGrid = grid; // Saving the solved version in the finalGrid. This is also very important for multi-threading.
					System.out.println("SOLVED");
					Sudoku.solvable = true; // For gui
					Sudoku.isSolved = true; // For multi-threading
					for (Thread t : ThreadInit.threads) {
						t.interrupt(); // For multi-threading
					}
					return true; // For "single threading"/no multi-threading.
				}
				row = 0;
			}
		}
		ArrayList<Integer> currentNumbers = contains(grid, row, col);
		int i = 1;
		while (i <= 9) {
			if (currentNumbers.contains(i) == false) {
				grid[row][col] = i;
				boolean rec = solve(grid, row, col);
				if (rec == true) {
					return true;
				} else {
					grid[row][col] = 0;
					currentNumbers = contains(grid, row, col);
				}
			}
			i++;
		}
		return false;
	}

	ArrayList<Integer> contains(int grid[][], int row, int col) {
		// Check if the same number exists in the same row or column.
		ArrayList<Integer> currentNumbers = new ArrayList<Integer>();
		int startRow = row / 3 * 3;
		int startCol = col / 3 * 3;
		for (int i = 0; i < 9; i++) {
			if (grid[i][col] != 0) {
				if (currentNumbers.contains(grid[i][col]) == false) {
					currentNumbers.add(grid[i][col]);
				}
			}
			if (grid[row][i] != 0) {
				if (currentNumbers.contains(grid[row][i]) == false) {
					currentNumbers.add(grid[row][i]);
				}
			}
		}
		// Check if the same number exists in the 3x3 box.
		for (int i = startRow; i < startRow + 3; i++) {
			for (int j = startCol; j < startCol + 3; j++) {
				if (grid[i][j] != 0) {
					if (currentNumbers.contains(grid[i][j]) == false) {
						currentNumbers.add(grid[i][j]);
					}
				}
			}
		}
		return currentNumbers;
	}
}
