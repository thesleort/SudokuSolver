package main;

import java.io.File;

import io.FileOperation;

public class Sudoku {

	public static void main(String[] args) {
		File filename = new File(args[0]);
		FileOperation fileop = new FileOperation();
		char[][] grid = fileop.FileReader(filename);
		for (int j = 0; j < 9; j++) {
			for (int i = 0; i < 9; i++) {
				System.out.print(grid[j][i] + " ");
			}
			System.out.println();
		}
	}
}
