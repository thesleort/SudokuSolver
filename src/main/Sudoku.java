package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import io.FileOperation;

public class Sudoku {
	public static boolean isSolved = false;
	public static int finalGrid[][];
	public static void main(String[] args) throws IOException {
		int row = 0;
		int col = 0;
		File filename = new File(args[0]);
		FileOperation fileop = new FileOperation();
		int[][] grid = fileop.FileReader(filename);
		/*Solver solver = new Solver();
		solver.solver(grid, row, col);*/
		new ThreadInit(grid, row, col);
		new Table().printGrid(finalGrid);
		System.out.println("HELLO");

		System.out.println("DONE");
		new Solver().solver(grid, row, col);
		new Table().printGrid(finalGrid);
		
	}
}
