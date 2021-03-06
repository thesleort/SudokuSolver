package gui;


import java.io.File;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;

public class FileSelector extends JFrame {

	private static final long serialVersionUID = 1L;
	public static File chosenFile, path;

	public FileSelector() {
		
	}
	public File FileSelect() {
		JFileChooser fileChooser = new JFileChooser();
		// The JFileChooser is only showing files with the extension .sudoku
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Sudoku files, .sudoku", "sudoku");

		fileChooser.setFileFilter(filter);
		int returnVal = fileChooser.showOpenDialog(this);
		// If cancel is pressed, the JFileChooser returns nothing.
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			// Since JFileChooser insn't thread safe it waits 
			// until one chooses a file.
			chosenFile = fileChooser.getSelectedFile();
			// For debugging
			System.out.println("You chose to open this file: " + fileChooser.getSelectedFile());

		} else if (returnVal == JFileChooser.CANCEL_OPTION) {
			System.out.println("Cancel was selected");
			chosenFile = null;
			return null;
		}
		return path;
	}
	public File FileSave() {
		JFileChooser fileChooser = new JFileChooser();
		// The JFileChooser is only showing files with the extension .ssudoku
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Sudoku files, .ssudoku", "ssudoku");

		fileChooser.setFileFilter(filter);
		int returnVal = fileChooser.showSaveDialog(this);
		// If cancel is pressed, the JFileChooser returns nothing.
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			path = fileChooser.getSelectedFile().getAbsoluteFile();
			path = new File(path+".ssudoku");
		} else if (returnVal == JFileChooser.CANCEL_OPTION) {
			System.out.println("Cancel was selected");
			chosenFile = null;
			return null;
		}
		return path;
	}

}
