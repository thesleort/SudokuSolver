package main;

public class Table {
	public int grid[][] = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
	public void printGrid(int grid[][]) {
		System.out.println("+-------+-------+-------+");
		for (int j = 0; j < 9; j++) {
			for (int i = 0; i < 9; i++) {
				if(i % 3 == 0) {
					System.out.print("| ");
				}
				System.out.print(grid[j][i] + " ");
			}
			System.out.print("|");
			System.out.println();
			if(j % 3 == 2) {
				System.out.println("+-------+-------+-------+");
			}
		}
	}
}
