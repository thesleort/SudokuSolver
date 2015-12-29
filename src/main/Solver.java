package main;

import java.io.IOException;
import java.util.ArrayList;

public class Solver {
	public boolean solver(int grid[][], int row, int col) throws IOException {
		Table table = new Table();
		/*
		 * System.out.println(
		 * "---------------------------------------------------");
		 * table.printGrid(table.grid);
		 * 
		 * System.out.println("row "+ row); System.out.println("col "+ col);
		 * System.out.println("num " + grid[row][col]);
		 */
		while (grid[row][col] != 0) {
			row++;
			if (row > 8) {
				col++;
				// System.out.println(row + " " + col);
				if (col > 8 && row > 8) {
					table.printGrid(grid);
					System.out.println(
							"DONE ==================================================================================");
					return true;
				}
				row = 0;

			}
		}
		ArrayList<Integer> currentNumbers = new Solver().contains(grid, row, col);

		// System.out.println(currentNumbers);
		int i = 1;
		while (i <= 9) {
			if (currentNumbers.contains(i) == false) {
				System.out.println(currentNumbers);
				System.out.println(grid[row][col]);
				grid[row][col] = i;
				System.out.println("---------------------------------------------------");
				System.out.println("INPUT:" + grid[row][col] + " on: " + (row + 1) + "," + (col + 1));
				table.printGrid(grid);
				// boolean rec = solver(grid, row, col);
				if (solver(grid, row, col) == true) {
					System.out.println(
							"DONE ---------------------------------------------------------------------------------------------");
					break;
				}
				if (solver(grid, row, col) == false) {
					System.out.println("NOPE");
					
				}
			}
			i++;
		}
		/*
		 * System.out.println(
		 * "---------------------------------------------------"); Table table =
		 * new Table(); table.printGrid(grid);
		 */
		return false;
	}

	public ArrayList<Integer> contains(int grid[][], int row, int col) {
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
