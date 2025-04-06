package mainApp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JComponent;

import mainApp.EvolutionLoop.FitnessType;
import mainApp.EvolutionLoop.SelectionType;

@SuppressWarnings("serial")
public class EvolutionComponent extends JComponent {

	public static final int LINE_WIDTH = 2;
	private static final int FRAME_WIDTH = 1800;
	public static final int STATS_HEIGHT = 200;
	public static final int SIDE_OFFSET = 100;
	private EvolutionLoop evoLoop;
	public int genSize;
	public int i;
	public Chromosome fittestChrom;

	// Lists for three values to be graphed
	public ArrayList<Integer> highFit;
	public ArrayList<Integer> averageFit;
	public ArrayList<Integer> lowFit;
	public ArrayList<Chromosome> fittest;


	public EvolutionLoop getEvoLoop() 
	{
		return this.evoLoop;
	}
	
	public EvolutionComponent(EvolutionLoop evoLoop2, int populationVal) {
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(FRAME_WIDTH, STATS_HEIGHT));
		// this.evoLoop = new EvolutionLoop(numPop);
		this.lowFit = new ArrayList<Integer>();
		this.averageFit = new ArrayList<Integer>();
		this.highFit = new ArrayList<Integer>();
		this.fittest = new ArrayList<Chromosome>();
		this.i = 0;
	}

	public void setUpLoop(int numPop, int genomeSize) {
		this.evoLoop = new EvolutionLoop(numPop, genomeSize);

	}

	// Adds values from evolution loop
	public void startUp(int numPop, int genomeSize) {
		this.evoLoop = new EvolutionLoop(numPop, genomeSize);
		evoLoop.createPop();

	}
	
	public EvolutionLoop getLoop() 
	{
		return this.evoLoop;
	}

	public void run(boolean crossoverOption, int mutate, int numElites, SelectionType selectionStrategy, FitnessType fitnessFunction) {
		evoLoop.changeFitnessType(fitnessFunction);
		evoLoop.changeSelectionStrategy(selectionStrategy);
		evoLoop.selection();
		
		if(numElites == 0) 
		{
			runNoElitism(crossoverOption, mutate);
		}
		else 
		{
			runElitism(crossoverOption, mutate, numElites);
		}
		this.highFit.add(evoLoop.returnHighestAverage());
		this.averageFit.add(evoLoop.returnAverage());
		this.lowFit.add(evoLoop.returnLowestAverage());
		this.fittest.add(evoLoop.returnFittest());
	}
	
	public void runNoElitism(boolean crossoverOption, int mutate) 
	{
		if (crossoverOption) 
		{
			evoLoop.flipMutation(mutate);
		}
		else
		{
			evoLoop.crossoverMutation(50);
		}
	}
	
	public void runElitism(boolean crossoverOption, int mutate, int numElites) 
	{
		if (crossoverOption) 
		{
			evoLoop.crossoverMutation(50);
		}
		
		evoLoop.elitism(mutate, numElites);
	}



}