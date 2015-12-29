package main;

import java.io.File;
import java.io.IOException;
import io.FileOperation;

public class Sudoku {

	public static void main(String[] args) throws IOException {
		int row = 0;
		int col = 0;
		File filename = new File(args[0]);
		FileOperation fileop = new FileOperation();
		int[][] grid = fileop.FileReader(filename);
		Solver solver = new Solver();
		solver.solver(grid, row, col);
	}
}
