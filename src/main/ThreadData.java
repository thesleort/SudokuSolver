package main;

public class ThreadData {
	private int grid[][];
	private int row;
	private int col;
	public void assignData(int grid[][], int row, int col) {
		this.grid = grid;
		this.row = row;
		this.col = col;
	}
	public int[][] grid() {
		return grid;
	}
	public int row() {
		return row;
	}
	public int col() {
		return col;
	}
}
