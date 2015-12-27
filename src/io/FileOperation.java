package io;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import main.Table;

public class FileOperation {
	char[][] grid = new Table().grid;

	public char[][] FileReader(File fileName) {
		try {
			FileInputStream finput = new FileInputStream(fileName);
			DataInputStream dinput = new DataInputStream(finput);
			BufferedReader br = new BufferedReader(new InputStreamReader(dinput));

			String currentLine;

			try {
				int row = 0;
				while ((currentLine = br.readLine()) != null) {
					String[] chars = currentLine.split(" ");
					for (int col = 0; col < 9; col++) {
						grid[row][col] = chars[col].charAt(0);
					}
					row++;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return grid;

	}
}
