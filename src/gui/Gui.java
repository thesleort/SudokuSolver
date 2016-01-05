package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.io.File;
import java.io.FileNotFoundException;

import io.FileOperation;
import main.Solver;
import main.Sudoku;
import main.Table;

public class Gui extends JPanel implements ActionListener {
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	JButton btnFileSelector, btnSave, btnStart;
	FileSelector fileSelector = new FileSelector();
	int[][] sudokuTable;
	JPanel[][] panelHolder;
	JPanel[] superPanel = new JPanel[2];

	/**
	 * Create the panel.
	 */

	public Gui() {
		this.btnFileSelector = new JButton("Select File");
		superPanel[0] = new JPanel();
		superPanel[1] = new JPanel();
		btnFileSelector.addActionListener(this);
		superPanel[0].add(btnFileSelector);
		this.btnStart = new JButton("Start solving");
		btnStart.addActionListener(this);
		superPanel[0].add(btnStart);
		this.btnSave = new JButton("Save sudoku");
		btnSave.addActionListener(this);
		superPanel[0].add(btnSave);
		add(superPanel[0]);
		add(superPanel[1]);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		FileOperation fileop = new FileOperation();
		if (e.getSource() == btnFileSelector) {
			System.out.println("Waiting for file select");

			fileSelector.FileSelect();
			try {
				Sudoku.inputGrid = fileop.FileReader(FileSelector.chosenFile);
			} catch (NullPointerException e1) {
				System.out.println("No file was chosen");
			}
		} else if (e.getSource() == btnSave) {

			try {
				if (Sudoku.inputGrid == null) {
					JOptionPane.showMessageDialog(this, "There is no file to save", "Inane error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					File path = fileSelector.FileSave();
					fileop.FileWriteSudoku(Sudoku.finalGrid, path);
				}
			} catch (NullPointerException e1) {
				// TODO: handle exception
			}
		} else if (e.getSource() == btnStart) {
			try {
				new Table().printGrid(Sudoku.inputGrid);
				if (Sudoku.inputGrid == null) {
					throw new NullPointerException();
				}
				long tStart = System.nanoTime();
				int rows = 9;
				int cols = 9;
				int row = 0;
				int col = 0;
				Thread t = new Thread(new Runnable() {
					public void run() {
						new Solver().solver(Sudoku.inputGrid, row, col);
						synchronized (Sudoku.sync) {
							System.out.println("notifying");
							Sudoku.sync.notify();
							System.out.println("done");
						}

					}
				});
				t.start();
				long tEnd = System.nanoTime();
				long tDelta = tEnd - tStart;
				double elapsedSeconds = tDelta / 10000;
				synchronized (Sudoku.sync) {
					try {
						Sudoku.sync.wait();
					} catch (InterruptedException f) {

					}
				}
				//panelHolder = new JPanel[rows][cols];
				superPanel[1].setLayout(new GridLayout(rows, cols));
				for (int m = 0; m < rows; m++) {

					for (int n = 0; n < cols; n++) {
						
						
						JLabel label = new JLabel(String.valueOf(Sudoku.finalGrid[m][n]));
						if(panelHolder[m][n] != null) {
							panelHolder[m][n].removeAll();
						}
						panelHolder[m][n] = new JPanel();
						panelHolder[m][n].setSize(10, 10);
						add(panelHolder[m][n]);
						panelHolder[m][n].add(label);
						if (n % 3 == 2 && m % 3 == 2) {
							panelHolder[m][n].setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.BLACK));
						} else if (n % 3 == 2) {
							panelHolder[m][n].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 3, Color.BLACK));
						} else if (m % 3 == 2) {
							panelHolder[m][n].setBorder(BorderFactory.createMatteBorder(1, 1, 3, 1, Color.BLACK));
						} else {
							panelHolder[m][n].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
						}
					}
				}

				new Table().printGrid(Sudoku.finalGrid);
				System.out.println(elapsedSeconds);
			} catch (NullPointerException e2) {
				new Table().printGrid(Sudoku.finalGrid);
				System.out.println("There was no sudoku to solve");
				JOptionPane.showMessageDialog(this, "There was no sudoku to solve", "Inane error",
						JOptionPane.ERROR_MESSAGE);
			}

		}
	}
}
