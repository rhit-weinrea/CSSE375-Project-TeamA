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
	private EvolutionComponent evolutionComponent;

	public EvolutionViewer() {
		JFrame frame = new JFrame();
		FitnessGraphPanel graphPanel = new FitnessGraphPanel(evolutionComponent);
		frame.add(graphPanel, BorderLayout.CENTER);
		GridBagLayout grid = new GridBagLayout();
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(grid);
		inputPanel.setPreferredSize(new Dimension(50, 50));
		viewerSwingComponents = new EvoViewerSwingComponents(frame, inputPanel);		
		initializeTextFieldToDefault();

		Timer timer = new Timer(DELAY, new ActionListener() {
			public int ticks = 0;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (ticks == numGenerationsVal - 1) {
					return;
				}
				if (getViewerSwingComponents().terminateAtMaxButton.isSelected()) {
					if (evolutionComponent.highFit.get(ticks) == (Integer) genomeLengthVal) {
						return;
					}
				}
				System.out.println("repainting");
				frame.repaint();
				graphPanel.repaint();
				ticks++;
			}
		});


		getViewerSwingComponents().enterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clicked++;
				if (clicked == 1) {
					getViewerSwingComponents().enterButton.setText("Pause");
					setDefaults(getViewerSwingComponents().componentToText);
					extractMapValues();
					selectionType = getViewerSwingComponents().selectionBox.getSelectedItem().toString();
					fitnessType = getViewerSwingComponents().fitnessBox.getSelectedItem().toString();
					evolveType = getViewerSwingComponents().evolveTypeBox.getSelectedItem().toString();
					evolutionComponent = new EvolutionComponent(new EvolutionLoop(numGenerationsVal, genomeLengthVal), populationVal);
					graphPanel.setComponent(evolutionComponent);
					evolutionComponent.genSize = numGenerationsVal;
					evolutionComponent.startUp(populationVal, genomeLengthVal);
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
						evolutionComponent.run(getViewerSwingComponents().crossoverButton.isSelected(), mutationRateVal, numElites,
								selectionStrategy, fitnessFunction);
					}
					timer.start();
					clicked++;
				}
				else if (clicked % 2 == 1) {
					timer.stop();
					getViewerSwingComponents().enterButton.setText("Start");
				} else {
					timer.start();
					getViewerSwingComponents().enterButton.setText("Pause");
				}
			}

		});

		getViewerSwingComponents().showFittestButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ChromosomeViewer(evolutionComponent.fittest.get(fitClick), 0.05);
			}
		});
		frame.pack();
		frame.setVisible(true);
	}

	private void extractMapValues() {
		mutationRateVal = componentToDefaultVal.get(getViewerSwingComponents().mutationRateButton);
		numGenerationsVal = componentToDefaultVal.get(getViewerSwingComponents().numGenerationsButton);
		populationVal = componentToDefaultVal.get(getViewerSwingComponents().populationButton);
		genomeLengthVal = componentToDefaultVal.get(getViewerSwingComponents().genomeLengthButton);
		numEliteIndivVal = componentToDefaultVal.get(getViewerSwingComponents().numEliteIndivButton);
	}

	private void initializeTextFieldToDefault() {
		componentToDefaultVal.put(getViewerSwingComponents().mutationRateButton, mutationRateVal);
		componentToDefaultVal.put(getViewerSwingComponents().numGenerationsButton, numGenerationsVal);
		componentToDefaultVal.put(getViewerSwingComponents().populationButton, populationVal);
		componentToDefaultVal.put(getViewerSwingComponents().genomeLengthButton, genomeLengthVal);
		componentToDefaultVal.put(getViewerSwingComponents().numEliteIndivButton, numEliteIndivVal);
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

	public EvoViewerSwingComponents getViewerSwingComponents() {
		return viewerSwingComponents;
	}
}
