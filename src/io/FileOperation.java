package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import main.Table;

public class FileOperation {
	public int[][] FileReader(File fileName) {
		Table table = new Table();
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
						if (chars[col].charAt(0) != 'X') {
							int x = Character.getNumericValue(chars[col].charAt(0));
							table.grid[row][col] = x;
						}
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
		return table.grid;

	}

	public void FileWriteSudoku(int grid[][], File fileName) {
		FileWriter writer;
		try {
			writer = new FileWriter(fileName);
			BufferedWriter bw = new BufferedWriter(writer);
			bw.write("+-------+-------+-------+");
			bw.newLine();
			for (int j = 0; j < 9; j++) {
				for (int i = 0; i < 9; i++) {
					if (i % 3 == 0) {
						bw.write("| ");
					}
					bw.write(grid[j][i] + " ");
				}
				bw.write("|");
				System.out.println();
				bw.newLine();
				if (j % 3 == 2) {
					bw.write("+-------+-------+-------+");
					bw.newLine();
				}
			}
			bw.flush();
			bw.close();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
