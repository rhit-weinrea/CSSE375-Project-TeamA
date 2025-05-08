package mainApp;

import java.util.ArrayList;
import java.util.Collections;

import fitnessFunctions.FitnessFunction;
import fitnessFunctions.allSinglesFitness;
import fitnessFunctions.orderedFitness;
import selectionStrategies.RankSelection;
import selectionStrategies.RouletteSelection;
import selectionStrategies.SelectionStrategy;
import selectionStrategies.TournamentSelection;
import selectionStrategies.TruncationSelection;

public class EvolutionLoop {

	private Chromosome[] population;
	private ArrayList<Chromosome> curPop;
	private int numPop;
	public SelectionStrategy selectionStrategy;
	public FitnessFunction fitnessFunction;
	
	private PopulationOperations pO = new PopulationOperations();
	private PopulationMutator populationMutator = new PopulationMutator();
	private PopulationEliteMaker populationEliteMaker = new PopulationEliteMaker();
	
	
	private ArrayList<SelectionStrategy> selectionStrategies = new ArrayList<SelectionStrategy>();

	public EvolutionLoop(int numPop, int genomeSize) {
		this.numPop = numPop;
		this.population = new Chromosome[numPop];
		for (int i = 0; i < numPop; i++) {
			this.population[i] = new Chromosome(genomeSize);
		}
		this.curPop = new ArrayList<Chromosome>();
		this.fitnessFunction = new allSinglesFitness(1);
		this.selectionStrategy = new RankSelection(fitnessFunction); //Default value

		this.selectionStrategies.add(new RankSelection(fitnessFunction));
		this.selectionStrategies.add(new RouletteSelection(fitnessFunction));
		this.selectionStrategies.add(new TournamentSelection(fitnessFunction));
		this.selectionStrategies.add(new TruncationSelection(numPop, fitnessFunction));
		System.out.println("rechecking");
		System.out.println(selectionStrategies.get(0).type);
		
	}
	
	public enum SelectionType 
	{
		TRUNCATION,
		RANK,
		TOURNAMENT,
		ROULETTE
	}
	
	public enum FitnessType 
	{
		ALLONES,
		ORDEREDONES,
		ALLZEROS,
		ORDEREDZEROS
	}

	public int returnGeneSize() {
		return this.population[0].numberOfArrays() * this.population[0].numberOfGenesInArray();
	}

	public void createPop() {
		for (int i = 0; i < this.numPop; i++) {
			curPop.add(population[i]);
		}

		for (int i = 0; i < curPop.size(); i++) {
			System.out.println(fitnessFunction.fitness(curPop.get(i)));
		}
	}
	
	public void changeSelectionStrategy(SelectionType type) 
	{
		for(SelectionStrategy strategy : selectionStrategies) 
		{
			if(strategy.type == type) 
			{
				this.selectionStrategy = strategy;
				break;
			}
		}
	}
	
	public void changeFitnessType(FitnessType type) {
		if(type == FitnessType.ALLONES) 
		{
			this.fitnessFunction = new allSinglesFitness(1);
		}
		else if(type == FitnessType.ORDEREDONES) 
		{
			this.fitnessFunction = new orderedFitness('1');
		}
		else if(type == FitnessType.ALLZEROS) 
		{
			this.fitnessFunction = new allSinglesFitness(0);
		}
		else if(type == FitnessType.ORDEREDZEROS) 
		{
			this.fitnessFunction = new orderedFitness('0');
		}
		this.selectionStrategy.updateFitness(this.fitnessFunction);
	}
	
	public void selection() 
	{
		curPop = selectionStrategy.select(curPop);
	}

	public void flipMutation(int mutate) {

		ArrayList<Chromosome> updatedPop = populationMutator.flipMutation(mutate, curPop);
		curPop = sortPop(updatedPop);

	}

	public void elitism(int mutate, int n) {
		ArrayList<Chromosome> updatedPop = populationEliteMaker.elitism(mutate, n, population.length, curPop);
		curPop = sortPop(updatedPop);

	}

	public void crossoverMutation(int crossoverPoint) {

		ArrayList<Chromosome> updatedPop = populationMutator.crossoverMutation(crossoverPoint, curPop);
		curPop = sortPop(updatedPop);
	}
	
	public ArrayList<Chromosome> sortPop(ArrayList<Chromosome> oldPop) 
	{
		Collections.sort(oldPop, new PopulationSorter(fitnessFunction));
		return oldPop;
	}

	public Chromosome returnFittest() {
		curPop = sortPop(curPop);
		Chromosome fittest = curPop.get(curPop.size() - 1);
		return fittest;
	}

	// returns average fitness for population
	public int returnAverage() {
		return pO.returnAverage(curPop, fitnessFunction);
	}

	// returns highest fitness
	public int returnHighestAverage() {
		return pO.returnHighestAverage(curPop, fitnessFunction);
	}

	// returns lowest fitness
	public int returnLowestAverage() {
		return pO.returnLowestAverage(curPop, fitnessFunction);
	}
}
