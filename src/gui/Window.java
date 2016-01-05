package gui;

import javax.swing.JFrame;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;

	public Window() {
		JFrame window = new JFrame("Sudoku Solver");
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.add(new Gui());
		window.pack();
		window.setVisible(true);
		window.setSize(500, 320);
		//window.setResizable(false);
	}
}
