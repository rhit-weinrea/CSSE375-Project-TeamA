package mainApp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PopulationViewer {
	public static final Dimension POPULATION_VIEWER_SIZE = new Dimension(500, 500);
	private ArrayList<Chromosome> population;
	private JFrame frame;

	public PopulationViewer(ArrayList<Chromosome> population, JFrame frame) {
		this.frame = frame;
		this.population = population;

		JPanel populationPanel = new JPanel();
		GridBagLayout grid = new GridBagLayout();

		// Sets up display grid.
		initializePanel(population, frame, populationPanel, grid);

	}

	private void initializePanel(ArrayList<Chromosome> population, JFrame frame, JPanel populationPanel,
			GridBagLayout grid) {
		populationPanel.setLayout(grid);
		populationPanel
				.setLayout(new GridLayout((int) (population.get(0).numberOfArrays() * Math.pow(population.size(), 0.5)),
						(int) (population.get(0).numberOfGenes() * Math.pow(population.size(), 0.5))));

		for (int j = 0; j < 100; j++) {

			ChromosomeDrawer.drawShortPhenotype(this.population.get(j), populationPanel);
		}

		frame.setSize(POPULATION_VIEWER_SIZE);
		frame.setTitle("Test Population Viewer");

		frame.add(populationPanel, BorderLayout.NORTH);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}
