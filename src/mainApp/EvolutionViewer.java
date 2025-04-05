package mainApp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import mainApp.EvolutionLoop.FitnessType;
import mainApp.EvolutionLoop.SelectionType;

public class EvolutionViewer extends JComponent{
	private int genomeLengthVal = 100;
	private int populationVal = 100;
	private int numGenerationsVal = 500;
	private int mutationRateVal = 5;
	private int numEliteIndivVal = 1;
	private int clicked;
	private int fitClick;

	private String selectionType, evolveType, fitnessType;
	private EvoViewerSwingComponents viewerSwingComponents;

	private static final int DELAY = 50;
	private HashMap<JTextField, Integer> componentToDefaultVal = new HashMap<>();

	public EvolutionViewer() {
		JFrame frame = new JFrame();
		EvolutionComponent newComponent = new EvolutionComponent(new EvolutionLoop(populationVal, genomeLengthVal),
				populationVal);
		GridBagLayout grid = new GridBagLayout();
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(grid);
		inputPanel.setPreferredSize(new Dimension(50, 50));
		viewerSwingComponents = new EvoViewerSwingComponents(frame, inputPanel);
		frame.add(newComponent, BorderLayout.CENTER);
		initializeTextFieldToDefault();

		Timer timer = new Timer(DELAY, new ActionListener() {
			public int ticks = 0;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (ticks == numGenerationsVal - 1) {
					return;
				}
				if (viewerSwingComponents.terminateAtMaxButton.isSelected()) {
					if (newComponent.highFit.get(ticks) == (Integer) genomeLengthVal) {
						return;
					}
				}
				frame.repaint();
				newComponent.repaint();
				ticks++;
			}
		});


		viewerSwingComponents.enterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clicked++;
				if (clicked == 1) {
					viewerSwingComponents.enterButton.setText("Pause");
					setDefaults(viewerSwingComponents.componentToText);
					extractMapValues();
					selectionType = viewerSwingComponents.selectionBox.getSelectedItem().toString();
					fitnessType = viewerSwingComponents.fitnessBox.getSelectedItem().toString();
					evolveType = viewerSwingComponents.evolveTypeBox.getSelectedItem().toString();
					newComponent.genSize = numGenerationsVal;
					newComponent.startUp(populationVal, genomeLengthVal);
					SelectionType selectionStrategy = SelectionType.TRUNCATION; // Default value
					FitnessType fitnessFunction = FitnessType.ALLONES; //Default value
					int numElites = 0;

					if (selectionType.equals("Truncation")) {
						selectionStrategy = SelectionType.TRUNCATION;
					} else if (selectionType.equals("Roulette")) {
						selectionStrategy = SelectionType.ROULETTE;
					} else if (selectionType.equals("Rank")) {
						selectionStrategy = SelectionType.RANK;
					} else if (selectionType.equals("Tournament")) {
						selectionStrategy = SelectionType.TOURNAMENT;
					}
					
					if (selectionType.equals("All Ones")) {
						fitnessFunction = FitnessType.ALLONES;
					} else if (selectionType.equals("Consecutive Ones")) {
						fitnessFunction = FitnessType.ORDEREDONES;
					} else if (selectionType.equals("All Zeros")) {
						fitnessFunction = FitnessType.ALLZEROS;
					} else if (selectionType.equals("Consecutive Zeros")) {
						fitnessFunction = FitnessType.ORDEREDZEROS;
					}

					if (evolveType.equals("Elitism")) {
						numElites = numEliteIndivVal;
					}

					for (int i = 0; i < numGenerationsVal; i++) {
						newComponent.run(viewerSwingComponents.crossoverButton.isSelected(), mutationRateVal, numElites,
								selectionStrategy, fitnessFunction);
					}
					timer.start();
					clicked++;
				}
				else if (clicked % 2 == 1) {
					timer.stop();
					viewerSwingComponents.enterButton.setText("Start");
				} else {
					timer.start();
					viewerSwingComponents.enterButton.setText("Pause");
				}
			}

		});

		viewerSwingComponents.showFittestButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ChromosomeViewer(newComponent.fittest.get(fitClick), 0.05);
			}
		});
		frame.pack();
		frame.setVisible(true);
	}

	private void extractMapValues() {
		mutationRateVal = componentToDefaultVal.get(viewerSwingComponents.mutationRateButton);
		numGenerationsVal = componentToDefaultVal.get(viewerSwingComponents.numGenerationsButton);
		populationVal = componentToDefaultVal.get(viewerSwingComponents.populationButton);
		genomeLengthVal = componentToDefaultVal.get(viewerSwingComponents.genomeLengthButton);
		numEliteIndivVal = componentToDefaultVal.get(viewerSwingComponents.numEliteIndivButton);
	}

	private void initializeTextFieldToDefault() {
		componentToDefaultVal.put(viewerSwingComponents.mutationRateButton, mutationRateVal);
		componentToDefaultVal.put(viewerSwingComponents.numGenerationsButton, numGenerationsVal);
		componentToDefaultVal.put(viewerSwingComponents.populationButton, populationVal);
		componentToDefaultVal.put(viewerSwingComponents.genomeLengthButton, genomeLengthVal);
		componentToDefaultVal.put(viewerSwingComponents.numEliteIndivButton, numEliteIndivVal);
	}

	private void setDefaults(HashMap<JComponent, String> componentToText) {
		for (JComponent component : componentToText.keySet()) {
			if (component instanceof JTextField) {
				String text = componentToText.get(component);
				JTextField textField = (JTextField) component;
				if(!textField.getText().equals(text) && !textField.getText().equals("")) {
					componentToDefaultVal.put(textField, Integer.valueOf(textField.getText()));
				}
			}
		}
	}
}
