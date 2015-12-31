package main;

import java.util.ArrayList;

public class ThreadInit {
	public static ArrayList<Thread> threads = new ArrayList<Thread>();
/*	private int grid[][];
	private int row;
	private int col;
	private int i = 1;*/

	public ThreadInit(int grid[][],int row, int col) {
		Table table = new Table();
		while (grid[row][col] != 0) {
			row++;
			if (row > 8) {
				col++;
				if (col > 8 && row > 8) {
					table.printGrid(grid);
					Sudoku.isSolved = true;
					/*System.out.println("SOLVED");*/
				}
				row = 0;
			}
		}
		System.out.println("(r "+row+"  c "+col+")");
		ArrayList<Integer> currentNumbers = new Solver().contains(grid, row, col);
		int i = 1;
		while (i <= 9) {
			if (currentNumbers.contains(i) == false && Sudoku.isSolved == false) {
				grid[row][col] = i;
				

				Thread t = new Thread(new RootThread(grid, row, col));
				threads.add(t);
				t.start();
			}
			/*table.printGrid(grid);*/
			System.out.println(i);
			i++;
		}
		for (Thread thread : threads) {
		    try {
				thread.join();
				table.printGrid(Sudoku.finalGrid);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
