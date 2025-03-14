package mainApp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
//import java.awt.GridBagConstraints;
//import javax.swing.JLayeredPane;

/**
 * 
 * @author Wynesakia Akamah & Abby Weinreb
 *
 */

public class ChromosomeViewer {

	public static final Dimension CHROMOSOME_VIEWER_SIZE = new Dimension(500, 500);
	private double rateInput;

	/**
	 * This is the initial constructor of ChromosomeViewer which requires a file
	 * from ChromosomesText to be input.
	 *
	 */
	public ChromosomeViewer() throws FileNotFoundException {
		this.rateInput = 0;

		// Asks for a filename to be entered.
		String filename = JOptionPane.showInputDialog("Enter Chromosome File:");

		// Converts the file of the given file name into a Chromosome.
		ChromosomeGenerator chromosomeCreator = new ChromosomeGenerator(filename);
		Chromosome chromosomeFromFile = chromosomeCreator.getChromosome();

		JFrame frame = new JFrame();
		JPanel chromosomePanel = new JPanel();
		JPanel buttonPanel = new JPanel();

		GridBagLayout grid = new GridBagLayout();

		// Sets up display grid.
		buttonPanel.setLayout(grid);
		chromosomePanel.setLayout(
				new GridLayout(chromosomeFromFile.numberOfArrays(), chromosomeFromFile.numberOfGenesInArray()));

		chromosomeFromFile.drawOn(1, chromosomePanel);

		JButton mutateButton = new JButton("Mutate");
		JButton loadButton = new JButton("Load");
		JButton saveButton = new JButton("Save");
		JButton enterRateButton = new JButton("Enter");
		JTextField rateInputBox = new JTextField(String.valueOf(this.rateInput), 10);
		JLabel label = new JLabel("Enter Mutation Rate  ");

		// ----------------------------------------------------------------------------------------------
		// Where buttons are given functionality

		MutateListener mutateListener = new MutateListener(chromosomeFromFile, frame, rateInput);

		enterRateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String rateInputString = rateInputBox.getText();
				System.out.println(rateInputString);
				rateInput = Double.parseDouble(rateInputString);
				mutateListener.setMutationRate(rateInput);
				System.out.println(rateInput);
				label.setText("Rate Entered");
			}
		});

		mutateButton.addActionListener(mutateListener);
		loadButton.addActionListener(new LoadChromosomeFileListener());
		saveButton.addActionListener(new SaveChromosomeFileListener());
		// ----------------------------------------------------------------------------------------------

		buttonPanel.add(label);
		buttonPanel.add(rateInputBox);
		buttonPanel.add(enterRateButton);
		buttonPanel.add(mutateButton);
		buttonPanel.add(loadButton);
		buttonPanel.add(saveButton);

		frame.setSize(CHROMOSOME_VIEWER_SIZE);
		frame.setTitle("Chromosome Viewer");

		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.add(chromosomePanel, BorderLayout.NORTH);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * This is the constructor of ChromosomeViewer which allows chromosomes defined
	 * by the program to be displayed.
	 *
	 */
	public ChromosomeViewer(Chromosome chromosome, double mutationRate) {
		this.rateInput = mutationRate;

		JFrame frame = new JFrame();
		JPanel chromosomePanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JLabel label = new JLabel("Enter Mutation Rate  ");

		GridBagLayout grid = new GridBagLayout();

		// Sets up display grid.
		buttonPanel.setLayout(grid);
		chromosomePanel.setLayout(new GridLayout(chromosome.numberOfArrays(), chromosome.numberOfGenesInArray()));

		chromosome.drawOn(1, chromosomePanel);

		JButton mutateButton = new JButton("Mutate");
		JButton loadButton = new JButton("Load");
		JButton saveButton = new JButton("Save");
		JButton enterRateButton = new JButton("Enter");
		JTextField rateInputBox = new JTextField(String.valueOf(this.rateInput), 10);

		// ----------------------------------------------------------------------------------------------
		// Where buttons are given functionality

		MutateListener mutateListener = new MutateListener(chromosome, frame, this.rateInput);

		enterRateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String rateInputString = rateInputBox.getText();
				System.out.println(rateInputString);
				rateInput = Double.parseDouble(rateInputString);
				System.out.println(rateInput);
				// System.out.println(chromosome.fitness);
				mutateListener.setMutationRate(rateInput);
				label.setText("Rate entered");
			}
		});

		mutateButton.addActionListener(new MutateListener(chromosome, frame, this.rateInput));
		loadButton.addActionListener(new LoadChromosomeFileListener());
		saveButton.addActionListener(new SaveChromosomeFileListener());
		// ----------------------------------------------------------------------------------------------

		buttonPanel.add(label);
		buttonPanel.add(rateInputBox);
		buttonPanel.add(enterRateButton);
		buttonPanel.add(mutateButton);
		buttonPanel.add(loadButton);
		buttonPanel.add(saveButton);

		frame.setSize(CHROMOSOME_VIEWER_SIZE);
		frame.setTitle("Test Chromosome Viewer");

		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.add(chromosomePanel, BorderLayout.NORTH);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public ChromosomeViewer(Chromosome chromosome) {

		JFrame frame = new JFrame();
		JPanel chromosomePanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JLabel label = new JLabel("Enter Mutation Rate  ");

		GridBagLayout grid = new GridBagLayout();

		// Sets up display grid.

		chromosomePanel.setLayout(new GridLayout(chromosome.numberOfArrays(), chromosome.numberOfGenesInArray()));

		chromosome.drawOn(1, chromosomePanel);

		// ----------------------------------------------------------------------------------------------

		frame.setSize(CHROMOSOME_VIEWER_SIZE);
		frame.setTitle("Chromosome Viewer");

		frame.add(chromosomePanel, BorderLayout.NORTH);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}
