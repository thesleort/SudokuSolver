package main;

public class RootThread extends Thread {
	public int grid[][];
	private int row;
	private int col;
	
	public RootThread(final int grid[][],final int inputRow, final int inputCol) {
		this.grid = grid;
		this.row = inputRow;
		this.col = inputCol;
//		System.out.println(grid[0][0]);
//		new Table().printGrid(grid);
/*		System.out.println("Thread initialized");
		System.out.println("ROW "+row+"  COL "+col);*/
		/*new Table().printGrid(grid);*/
	}
	public void run() {
		System.out.println(grid[0][0]);
		new Solver().solver(grid, row, col);

		/*new Table().printGrid(grid);*/

	}

}
