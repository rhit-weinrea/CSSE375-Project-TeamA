package mainApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class LoadChromosomeFileListener implements ActionListener {

	public LoadChromosomeFileListener() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		Scanner scanner = null;
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File selectedFile = chooser.getSelectedFile();
			try {
				scanner = new Scanner(selectedFile);
			} catch (FileNotFoundException e1) {
				System.err.println("File not found. Please try again.");
			}
		}

	}

}
