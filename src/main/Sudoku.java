package main;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;

import io.FileOperation;
import gui.*;

public class Sudoku {
	public static boolean isSolved = false;
	public static int finalGrid[][];
	public static final Object sync = new Object();
	public static int inputGrid[][];
	public static void main(String[] args) throws IOException {
		int row = 0;
		int col = 0;
		//File filename = new File(args[0]);
		FileOperation fileop = new FileOperation();
		//int[][] grid = fileop.FileReader(filename);
		//inputGrid = grid;
		/*
		 * Solver solver = new Solver(); solver.solver(grid, row, col);
		 */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window();
					// frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		// for(int i = 0; i < 10; i++) {
		// new MyThread(i).start();
		// }
		// new Solver().solver(grid, row, col);
		System.out.println("-----------------------------------------------------");
		// new ThreadInit(grid, row, col);
		// new Solver().solver(grid, row, col);
		

		System.out.println("DONE");

	}
}
