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
import io.FileOperation;
import main.Solver;
import main.Sudoku;
import main.Table;
//import main.ThreadInit; // For multi-threading

public class Gui extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	JButton btnFileSelector, btnSave, btnStart;
	FileSelector fileSelector = new FileSelector();
	int rows = 9;
	int cols = 9;
	JPanel[][] panelHolder = new JPanel[rows][cols];
	JPanel[] superPanel = new JPanel[3];

	public Gui() {
		// Defining the buttons
		this.btnFileSelector = new JButton("Select File");
		this.btnStart = new JButton("Start solving");
		this.btnSave = new JButton("Save sudoku");
		// Defining the superPanels
		superPanel[0] = new JPanel();
		superPanel[1] = new JPanel();
		superPanel[2] = new JPanel();
		// Adding functionality to the buttons
		btnFileSelector.addActionListener(this);
		btnStart.addActionListener(this);
		btnSave.addActionListener(this);
		// Adding the buttons to superPanel[0]
		superPanel[0].add(btnFileSelector);
		superPanel[0].add(btnStart);
		superPanel[0].add(btnSave);
		// Adding the superPanels to the Gui JPanel.
		add(superPanel[0]);
		add(superPanel[1]);
		add(superPanel[2]);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		FileOperation fileop = new FileOperation();
		if (e.getSource() == btnFileSelector) {
			System.out.println("Waiting for file select");
			fileSelector.FileSelect();
			try {
				if (superPanel[1] != null && superPanel[2] != null) {
					superPanel[1].removeAll();
					superPanel[1].revalidate();
					superPanel[1].repaint();
					superPanel[2].removeAll();
					superPanel[2].revalidate();
					superPanel[2].repaint();
				}
				JLabel feedback = new JLabel(
						"<html>The sudoku: " + FileSelector.chosenFile + "<br> has been loaded</html>");
				superPanel[2].add(feedback);
				Sudoku.inputGrid = fileop.FileReader(FileSelector.chosenFile);
				superPanel[1].setLayout(new GridLayout(rows, cols));
				for (int m = 0; m < rows; m++) {
					for (int n = 0; n < cols; n++) {
						JLabel label = new JLabel(String.valueOf(Sudoku.inputGrid[m][n]));
						panelHolder[m][n] = new JPanel();
						panelHolder[m][n].setSize(10, 10);
						superPanel[1].add(panelHolder[m][n]);
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
			} catch (NullPointerException e1) {
				System.out.println("No file was chosen");
				JOptionPane.showMessageDialog(this, "No file was chosen", "No file",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == btnSave) {
			if (Sudoku.inputGrid == null) {
				JOptionPane.showMessageDialog(this, "There is no file to save", "No file",
						JOptionPane.ERROR_MESSAGE);
			} else {
				File path = fileSelector.FileSave();
				fileop.FileWriteSudoku(Sudoku.finalGrid, path);
			}
		} else if (e.getSource() == btnStart) {
			try {
				if (Sudoku.inputGrid == null) {
					throw new NullPointerException();
				}
				new Table().printGrid(Sudoku.inputGrid); // Print the unsolved sudoku in the console/terminal
				int row = 0;
				int col = 0;
				Thread t = new Thread(new Runnable() {
					public void run() {
						Sudoku.isSolved = false;
						new Solver().solve(Sudoku.inputGrid, row, col);
//						new ThreadInit(grid, row, col); // Multi-threading initialiser
						synchronized (Sudoku.sync) {
							System.out.println("notifying");
							Sudoku.sync.notify();
							System.out.println("done");
						}
					}
				});
				t.start();
				synchronized (Sudoku.sync) {
					try {
						Sudoku.sync.wait();
					} catch (InterruptedException f) {
						JOptionPane.showMessageDialog(this,
								"Something happened to the sudoku solving thread and thus could not complete the task",
								"Warning", JOptionPane.ERROR_MESSAGE);
					}
				}
				if (Sudoku.solvable == false) {
					JOptionPane.showMessageDialog(this, "The sudoku could not be solved", "Sudoku unsolvable",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (superPanel[1] != null && superPanel[2] != null) {
						superPanel[1].removeAll();
						superPanel[1].revalidate();
						superPanel[1].repaint();
						superPanel[2].removeAll();
						superPanel[2].revalidate();
						superPanel[2].repaint();
					}
					JLabel feedback = new JLabel("<html>The sudoku has been solved. You can now save it.</html>");
					superPanel[2].add(feedback);
					new Table().printGrid(Sudoku.finalGrid);
					superPanel[1].setLayout(new GridLayout(rows, cols));
					for (int m = 0; m < rows; m++) {
						for (int n = 0; n < cols; n++) {
							JLabel label = new JLabel(String.valueOf(Sudoku.finalGrid[m][n]));
							panelHolder[m][n] = new JPanel();
							panelHolder[m][n].setSize(10, 10);
							superPanel[1].add(panelHolder[m][n]);
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
				}
			} catch (NullPointerException e2) {
				System.out.println(e2.getMessage());
				System.out.println("There was no sudoku to solve");
				JOptionPane.showMessageDialog(this, "There was no sudoku to solve", "No sudoku",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
