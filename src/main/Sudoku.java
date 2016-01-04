package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import io.FileOperation;
import gui.*;

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
		new Solver().solver(grid, row, col);
//		for(int i = 0; i < 10; i++) {
//			new MyThread(i).start();
//		}
		//new Solver().solver(grid, row, col);
		System.out.println("-----------------------------------------------------");
//		new ThreadInit(grid, row, col);
//		new Solver().solver(grid, row, col);
		new Table().printGrid(finalGrid);

		System.out.println("DONE");
		
	}
}
