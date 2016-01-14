package main;
/*
 * Code made by Troels Blicher Petersen
 * troels@newtec.dk
 */
import java.awt.EventQueue;
//import java.io.File; // For multi-threading
import java.io.IOException;

//import io.FileOperation; // For multi-threading
import gui.*;

public class Sudoku {
	public static boolean isSolved = false; // For multi-threading
	public static int finalGrid[][];
	public static final Object sync = new Object(); // For gui multi-threading
	public static int inputGrid[][];
	public static boolean solvable; 
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Window();
			}
		});
		/*
		 * Multi-threaded solving below.
		 * - Does not work due to variable problem.
		 */
//		File filename = new File(args[0]);
//		FileOperation fileop = new FileOperation();
//		int[][] grid = fileop.FileReader(filename);
//		int row = 0;
//		int col = 0;
//		new ThreadInit(grid, row, col); //Multi-threading
		
	}
}
