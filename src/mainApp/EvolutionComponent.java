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

import mainApp.EvolutionLoop.SelectionType;

@SuppressWarnings("serial")
public class EvolutionComponent{

	
	private EvolutionLoop evoLoop;
	public int genSize;
	public Chromosome fittestChrom;

	// Lists for three values to be graphed
	public ArrayList<Integer> highFit;
	public ArrayList<Integer> averageFit;
	public ArrayList<Integer> lowFit;
	public ArrayList<Chromosome> fittest;



	public EvolutionComponent(EvolutionLoop evoLoop2, int populationVal) {

		this.lowFit = new ArrayList<Integer>();
		this.averageFit = new ArrayList<Integer>();
		this.highFit = new ArrayList<Integer>();
		this.fittest = new ArrayList<Chromosome>();

	}

	public void setUpLoop(int numPop, int genomeSize) {
		this.evoLoop = new EvolutionLoop(numPop, genomeSize);

	}

	// Adds values from evolution loop
	public void startUp(int numPop, int genomeSize) {
		this.evoLoop = new EvolutionLoop(numPop, genomeSize);
		getEvoLoop().createPop();

	}
	
	public EvolutionLoop getLoop() 
	{
		return this.getEvoLoop();
	}

	public void run(boolean crossoverOption, int mutate, int numElites, SelectionType selectionStrategy) 
	{
		getEvoLoop().changeSelectionStrategy(selectionStrategy);
		getEvoLoop().selection();
		
		if(numElites == 0) 
		{
			runNoElitism(crossoverOption, mutate);
		}
		else 
		{
			runElitism(crossoverOption, mutate, numElites);
		}
		this.highFit.add(getEvoLoop().returnHighestAverage());
		this.averageFit.add(getEvoLoop().returnAverage());
		this.lowFit.add(getEvoLoop().returnLowestAverage());
		this.fittest.add(getEvoLoop().returnFittest());
	}
	
	public void runNoElitism(boolean crossoverOption, int mutate) 
	{
		if (crossoverOption) 
		{
			getEvoLoop().flipMutation(mutate);
		}
		else
		{
			getEvoLoop().crossoverMutation(50);
		}
	}
	
	public void runElitism(boolean crossoverOption, int mutate, int numElites) 
	{
		if (crossoverOption) 
		{
			getEvoLoop().crossoverMutation(50);
		}
		
		getEvoLoop().elitism(mutate, numElites);
	}

	public EvolutionLoop getEvoLoop() {
		return evoLoop;
	}



}
