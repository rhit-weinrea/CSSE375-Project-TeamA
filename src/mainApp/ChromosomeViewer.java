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

/**
 * 
 * @author Wynesakia Akamah & Abby Weinreb
 *
 */

public class ChromosomeViewer {

	public static final Dimension CHROMOSOME_VIEWER_SIZE = new Dimension(500, 500);
	private double rateInput;

	JFrame frame = new JFrame();
	JPanel chromosomePanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	JButton mutateButton = new JButton("Mutate");
	JButton loadButton = new JButton("Load");
	JButton saveButton = new JButton("Save");
	JButton enterRateButton = new JButton("Enter");

	/**
	 * This is the constructor of ChromosomeViewer which allows chromosomes defined
	 * by the program to be displayed.
	 *
	 */
	public ChromosomeViewer(Chromosome inputChromosome, double mutationRate) {
		Chromosome chromosome;
		if(inputChromosome == null){
			chromosome = getTypedChromosome();
		}
		else{
			chromosome = inputChromosome;
		}
		this.rateInput = mutationRate;

		JLabel label = new JLabel("Enter Mutation Rate  ");

		GridBagLayout grid = new GridBagLayout();

		// Sets up display grid.
		buttonPanel.setLayout(grid);
		chromosomePanel.setLayout(new GridLayout(chromosome.numberOfArrays(), chromosome.numberOfGenesInArray()));

		chromosome.drawOn(1, chromosomePanel);

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


	private Chromosome getTypedChromosome(){
		String filename = JOptionPane.showInputDialog("Enter Chromosome File:");
		ChromosomeGenerator generatorAtFile = null;
            try {
                generatorAtFile = new ChromosomeGenerator(filename);
            } 
			catch (FileNotFoundException ex) {
            }
		Chromosome chromosomeAtFile = generatorAtFile.getChromosome();
		return chromosomeAtFile;
	}

}
