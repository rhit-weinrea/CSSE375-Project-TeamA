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
        Chromosome chromosome = initializeChromosome(inputChromosome);
        this.rateInput = mutationRate;

        JLabel label = new JLabel("Enter Mutation Rate  ");
        setupGridLayout();
        initializeChromosomePanel(chromosome);
        JTextField rateInputBox = createRateInputBox();
        setupButtonPanel(label, rateInputBox);
        setupFrame();

        // Set up button functionality
        setupButtonListeners(label, rateInputBox, chromosome);
    }

    private Chromosome initializeChromosome(Chromosome inputChromosome) {
        if (inputChromosome == null) {
            return getTypedChromosome();
        } else {
            return inputChromosome;
        }
    }

    private void setupGridLayout() {
        GridBagLayout grid = new GridBagLayout();
        buttonPanel.setLayout(grid);
    }

    private void initializeChromosomePanel(Chromosome chromosome) {
        chromosomePanel.setLayout(new GridLayout(chromosome.numberOfArrays(), chromosome.numberOfGenesInArray()));
        ChromosomeDrawer.drawLongPhenotype(chromosome, chromosomePanel);
    }

    private JTextField createRateInputBox() {
        JTextField rateInputBox = new JTextField(String.valueOf(this.rateInput), 10);
        return rateInputBox;
    }

    private void setupButtonPanel(JLabel label, JTextField rateInputBox) {
        buttonPanel.add(label);
        buttonPanel.add(rateInputBox);
        buttonPanel.add(enterRateButton);
        buttonPanel.add(mutateButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(saveButton);
    }

    private void setupFrame() {
        frame.setSize(CHROMOSOME_VIEWER_SIZE);
        frame.setTitle("Chromosome Viewer");
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(chromosomePanel, BorderLayout.NORTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void setupButtonListeners(JLabel label, JTextField rateInputBox, Chromosome chromosome) {
        MutateListener mutateListener = new MutateListener(chromosome, frame, this.rateInput);

        enterRateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleEnterRateAction(rateInputBox, label, mutateListener);
            }
        });

        mutateButton.addActionListener(new MutateListener(chromosome, frame, this.rateInput));
        loadButton.addActionListener(new LoadChromosomeFileListener());
        saveButton.addActionListener(new SaveChromosomeFileListener());
    }

    private void handleEnterRateAction(JTextField rateInputBox, JLabel label, MutateListener mutateListener) {
        String rateInputString = rateInputBox.getText();
        System.out.println(rateInputString);
        rateInput = Double.parseDouble(rateInputString);
        System.out.println(rateInput);
        mutateListener.setMutationRate(rateInput);
        label.setText("Rate entered");
    }

    private Chromosome getTypedChromosome() {
        ChromosomeGenerator generatorAtFile = null;
        try {
        	new ChromosomeGenerator();
        } catch (FileNotFoundException ex) {
        }
        return generatorAtFile.getChromosome();
    }
}
