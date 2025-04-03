package mainApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import mainApp.EvolutionLoop.SelectionType;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EvolutionViewer {

	static final int FRAME_WIDTH = 1800;
	static final int FRAME_HEIGHT = 600;

	private int genomeLengthVal, populationVal, numGenerationsVal, mutationRateVal, elitismNum, clicked, fitClick;
	private String selectionType, evolveType;

	// How long to wait in milliseconds between each step of the simulation
	private static final int DELAY = 50;

	public EvolutionViewer() {
		// Creates frames
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		// Creates instance of EvolutionComponent
		EvolutionComponent newComponent = new EvolutionComponent(new EvolutionLoop(populationVal, this.genomeLengthVal),
				populationVal);

		// Adding buttons and creating layout
		GridBagLayout grid = new GridBagLayout();

		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(grid);

		ArrayList<Component> textFields = new ArrayList<Component>();

		JTextField mutationRate = new JTextField("Enter Mutation Rate", 0);
		JTextField numGenerations = new JTextField("Enter Number of Generations", 0);
		JTextField population = new JTextField("Enter Population", 0);
		JTextField genomeLength = new JTextField("Enter Genome Length", 0);
		JTextField elitismNumButton = new JTextField("Number of Elites", 0);
		JButton enterButton = new JButton("Start");
		JButton seeFitChrom = new JButton("Show Fittest Chromosome");
		JCheckBox terminateAtMaxButton = new JCheckBox("Terminate at Max Fitness?");
		JCheckBox crossoverOption = new JCheckBox("Crossover?");


		String[] selectionChoices = { "Truncation", "Roulette", "Rank", "Tournament" };

		final JComboBox<String> cb = new JComboBox<String>(selectionChoices);

		String[] evolveChoices = { "Regular", "Elitism" };

		final JComboBox<String> cb2 = new JComboBox<String>(evolveChoices);

		// Add things to frame
		inputPanel.add(enterButton);
		inputPanel.add(genomeLength);
		inputPanel.add(population);
		inputPanel.add(numGenerations);
		inputPanel.add(mutationRate);
		inputPanel.add(elitismNumButton);
		inputPanel.add(cb);
		inputPanel.add(cb2);
		inputPanel.add(terminateAtMaxButton);
		inputPanel.add(crossoverOption);
		inputPanel.add(seeFitChrom);

		frame.add(inputPanel, BorderLayout.SOUTH);
		frame.add(newComponent, BorderLayout.CENTER);


		// Add button panel on the right side

		// Starts the simulator
		Timer t = new Timer(DELAY, new ActionListener() {
			public int ticks = 0;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (ticks == numGenerationsVal - 1) {
					return;
				}

				if (terminateAtMaxButton.isSelected()) {
					if (newComponent.highFit.get(ticks) == (Integer) genomeLengthVal) {
						return;
					}

				}

				frame.repaint();
				newComponent.repaint();
				// new ChromosomeViewer(newComponent.fittest.get(ticks));
				ticks++;

			}

		});

		enterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clicked++;
				if (clicked == 1) {
					enterButton.setText("Pause");

					genomeLengthVal = Integer.parseInt(genomeLength.getText());
					numGenerationsVal = Integer.parseInt(numGenerations.getText());
					populationVal = Integer.parseInt(population.getText());
					mutationRateVal = Integer.parseInt(mutationRate.getText());
					selectionType = cb.getSelectedItem().toString();
					evolveType = cb2.getSelectedItem().toString();
					elitismNum = Integer.parseInt(elitismNumButton.getText());
					newComponent.genSize = numGenerationsVal;
					newComponent.startUp(populationVal, genomeLengthVal);
					SelectionType selectionStrategy = SelectionType.TRUNCATION; //Default value
					int numElites = 0;

					if (selectionType.equals("Truncation")) {
						selectionStrategy = SelectionType.TRUNCATION;
					}
					else if (selectionType.equals("Roulette")) {
						selectionStrategy = SelectionType.ROULETTE;
					}
					else if (selectionType.equals("Rank")) {
						selectionStrategy = SelectionType.RANK;
					}
					else if(selectionType.equals("Tournament")) 
					{
						selectionStrategy = SelectionType.TOURNAMENT;
					}
					
					if(evolveType.equals("Elitism")) 
					{
						numElites = elitismNum;
					}

					for (int i = 0; i < numGenerationsVal; i++) {
						newComponent.run(crossoverOption.isSelected(), mutationRateVal, numElites, selectionStrategy);
					}
					
					t.start();
					clicked++;
				}

				else if (clicked % 2 == 1) {
					t.stop();
					enterButton.setText("Start");
				} else {
					t.start();
					enterButton.setText("Pause");
				}

			}

		});

		seeFitChrom.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ChromosomeViewer(newComponent.fittest.get(fitClick));

			}

		});

		// Creates timer and runs simulation

		frame.pack();
		frame.setVisible(true);

	}

}
