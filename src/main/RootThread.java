package main;

public class RootThread implements Runnable {
	int grid[][];
	int row;
	int col;
	public RootThread(int inputGrid[][],int inputRow, int inputCol) {
		grid = inputGrid;
		row = inputRow;
		col = inputCol;
/*		System.out.println("Thread initialized");
		System.out.println("ROW "+row+"  COL "+col);*/
		/*new Table().printGrid(grid);*/
	}
	public void run() {
		new Solver().solver(grid, row, col);

		/*new Table().printGrid(grid);*/

	}

}
