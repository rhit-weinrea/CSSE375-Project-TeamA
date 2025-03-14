package mainApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class SaveChromosomeFileListener implements ActionListener {

	public SaveChromosomeFileListener() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		Scanner scanner = null;
		if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			try {
	            FileWriter fw = new FileWriter(chooser.getSelectedFile()+".txt");
	        } 
			catch (Exception ex) {
	            ex.printStackTrace();
	        }
		}
	}

}
