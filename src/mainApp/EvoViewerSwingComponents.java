package mainApp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EvoViewerSwingComponents {
    private static final int FRAME_WIDTH = 1800;
	private static final int FRAME_HEIGHT = 600;
    private static final int TEXT_FIELD_WIDTH = 15;

    public HashMap<JComponent, String> componentToText = new HashMap<>();

    private final String mutationRateText = "Enter Mutation Rate";
    public JTextField mutationRateButton = new JTextField(mutationRateText, TEXT_FIELD_WIDTH);

    private final String numGenerationsText = "Enter # of Generations";
    public JTextField numGenerationsButton = new JTextField(numGenerationsText, TEXT_FIELD_WIDTH);

    private final String populationText = "Enter Population";
    public JTextField populationButton = new JTextField(populationText, TEXT_FIELD_WIDTH);

    private final String genomeLengthText = "Enter Genome Length";
    public JTextField genomeLengthButton = new JTextField(genomeLengthText, TEXT_FIELD_WIDTH);

    private final String numEliteIndiv = "Number of Elites";
    public JTextField numEliteIndivButton = new JTextField(numEliteIndiv, TEXT_FIELD_WIDTH);

    private final String enterText = "Start";
    public JButton enterButton = new JButton(enterText);

    private final String showFittestText = "Show Fittest Chromosome";
    public JButton showFittestButton = new JButton(showFittestText);

    private final String terminateAtMaxText = "Terminate at Max Fitness?";
    public JCheckBox terminateAtMaxButton = new JCheckBox(terminateAtMaxText);

    private final static String crossoverText = "Crossover?";
    public static JCheckBox crossoverButton = new JCheckBox(crossoverText, true);

    private final String[] selectionChoices = { "Truncation", "Roulette", "Rank", "Tournament" };
	public final JComboBox<String> selectionBox = new JComboBox<>(selectionChoices);

    private final String[] fitnessChoices = { "All Ones", "Consecutive Ones", "All Zeros", "Consecutive Zeros" };
	public final JComboBox<String> fitnessBox = new JComboBox<>(fitnessChoices);

    private final String[] evolveTypes = { "Regular", "Elitism" };
    public final JComboBox<String> evolveTypeBox = new JComboBox<>(evolveTypes);

    public EvoViewerSwingComponents(JFrame frame, JPanel inputPanel) {
        frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        initializeComponentToText();
        removeTextForComponents(componentToText);
        centerAlignTextFields(componentToText);
        addComponentsToPanel(componentToText, inputPanel);
		frame.add(inputPanel, BorderLayout.SOUTH);
    }

    private void removeTextForComponents(HashMap<JComponent, String> componentToText) {
        for (JComponent component : componentToText.keySet()) {
            if (component instanceof JTextField) {
                JTextField textField = (JTextField) component;
                removeTextOnClick(textField);
            }
        }
    }

    private void centerAlignTextFields(HashMap<JComponent, String> componentToText) {
        for (JComponent component : componentToText.keySet()) {
            if (component instanceof JTextField) {
                JTextField textField = (JTextField) component;
                textField.setHorizontalAlignment(JTextField.CENTER);
            }
        }
    }

    private void addComponentsToPanel(HashMap<JComponent, String> componentToText, JPanel inputPanel) {
        inputPanel.add(enterButton);
		inputPanel.add(genomeLengthButton);
		inputPanel.add(populationButton);
		inputPanel.add(numGenerationsButton);
		inputPanel.add(mutationRateButton);
		inputPanel.add(numEliteIndivButton);
		inputPanel.add(selectionBox);
		inputPanel.add(fitnessBox);
		inputPanel.add(evolveTypeBox);
		inputPanel.add(terminateAtMaxButton);
		inputPanel.add(crossoverButton);
        inputPanel.add(evolveTypeBox);
		inputPanel.add(showFittestButton);
    }

    private void initializeComponentToText(){
        componentToText.put(enterButton, enterText);
        componentToText.put(genomeLengthButton, genomeLengthText);
        componentToText.put(populationButton, populationText);
        componentToText.put(numGenerationsButton, numGenerationsText);
        componentToText.put(mutationRateButton, mutationRateText);
        componentToText.put(numEliteIndivButton, numEliteIndiv);
        componentToText.put(selectionBox, "Selection Method");
        componentToText.put(fitnessBox, "Fitness Function");
        componentToText.put(evolveTypeBox, "Evolution Type");
        componentToText.put(terminateAtMaxButton, terminateAtMaxText);
        componentToText.put(crossoverButton, crossoverText);
        componentToText.put(showFittestButton, showFittestText);
    }

    private void removeTextOnClick(JTextField textField){
		textField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				textField.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(componentToText.get(textField));
                }
			}

		});
	}
}
