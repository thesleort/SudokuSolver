package main;

import java.util.ArrayList;

public class ThreadInit {
	public static ArrayList<Thread> threads = new ArrayList<Thread>();

	/*
	 * private int grid[][]; private int row; private int col; private int i =
	 * 1;
	 */
	// final int inputGrid[][];
	// final int inputRow;
	// final int inputCol;
	int i = 0;
	int startNum[] = {1,2,3,4,5,6,7,8,9};
	public ThreadInit(int grid[][], int row, int col) {
		Table table = new Table();
		while (grid[row][col] != 0) {
			row++;
			if (row > 8) {
				col++;
				if (col > 8 && row > 8) {
					table.printGrid(grid);
					Sudoku.isSolved = true;
					/* System.out.println("SOLVED"); */
				}
				row = 0;
			}
		}
		final int inputRow = row;
		final int inputCol = col;
		// System.out.println("(r "+row+" c "+col+")");
		ArrayList<Integer> currentNumbers = new Solver().contains(grid, row, col);

		
		while (i <= 8) {
			if (currentNumbers.contains(i) == false && Sudoku.isSolved == false) {

//				grid[row][col] = i;
//				final int inputGrid[][] = grid;
				
				Thread t = new Thread(new Runnable() {
					public void run() {
						System.out.println(startNum[i-1]);
						grid[inputRow][inputCol] = startNum[i-1];
						ThreadData td = new ThreadData();
						td.assignData(grid, inputRow, inputCol);
						new Solver().solver(td.grid(), td.row(), td.col());
						//System.out.println(inputGrid[0][0]);
							//System.out.println("Thread " + td.grid[inputRow][inputCol]);
						
					}
				});
				
				threads.add(t);

				// Thread t = new RootThread(grid, row, col);
				// threads.add(t);
				// t.start();
			}
//			table.printGrid(Sudoku.finalGrid);

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
				
				// table.printGrid(Sudoku.finalGrid);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("All interrupted");
		table.printGrid(Sudoku.finalGrid);
	}

}
// class MyThread extends Thread {
//
// private int to;
//
// public MyThread(int to) {
// this.to = to;
// }
//
// @Override
// public void run() {
// System.out.println("hello " + to);
// }
// }
