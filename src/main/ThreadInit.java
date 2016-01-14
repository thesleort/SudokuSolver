package main;

import java.util.ArrayList;

public class ThreadInit {
	public static ArrayList<Thread> threads = new ArrayList<Thread>();
	int i = 0;
	public ThreadInit(int grid[][], int row, int col) {
		Table table = new Table();
		while (grid[row][col] != 0) {
			row++;
			if (row > 8) {
				col++;
				if (col > 8 && row > 8) {
					table.printGrid(grid);
					Sudoku.isSolved = true;
				}
				row = 0;
			}
		}
		final int inputRow = row;
		final int inputCol = col;
		ArrayList<Integer> currentNumbers = new Solver().contains(grid, row, col);

		while (i <= 8) {
			if (currentNumbers.contains(i) == false && Sudoku.isSolved == false) {
				Thread t = new Thread(new Runnable() {
					public void run() {
						grid[inputRow][inputCol] = i;
						ThreadData td = new ThreadData(grid, inputRow, inputCol); // Trying to fix multi-thread problem; testing different methods
						new Solver().solve(td.grid(), td.row(), td.col());
					}
				});
				threads.add(t);
			}
			i++;
		}
		System.out.println("Waiting");
		for (Thread thread : threads) {
				thread.start();
				System.out.println(thread + " has started");
		}
		for (Thread thread : threads) {
			try {
				thread.join();
				System.out.println(thread + " has been interrupted");
			} catch (InterruptedException e) {
				System.out.println("The thread was already interrupted");
				e.printStackTrace();
			}
		}
		System.out.println("All interrupted");
		table.printGrid(Sudoku.finalGrid);
	}
}
