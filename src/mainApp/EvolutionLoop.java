package mainApp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
	private ArrayList<Chromosome> updatedSet;
	private ArrayList<Chromosome> curPop;
	private int numPop;
	public SelectionStrategy selectionStrategy;
	public FitnessFunction fitnessFunction;
	private ChromosomeOperations cO = new ChromosomeOperations();
	private ArrayList<SelectionStrategy> selectionStrategies = new ArrayList<SelectionStrategy>();

	public EvolutionLoop(int numPop, int genomeSize) {
		this.numPop = numPop;
		this.population = new Chromosome[numPop];
		for (int i = 0; i < numPop; i++) {
			this.population[i] = new Chromosome(genomeSize);
		}
		this.updatedSet = new ArrayList<Chromosome>();
		this.curPop = new ArrayList<Chromosome>();
		this.fitnessFunction = new allSinglesFitness(1);
		this.selectionStrategy = new RankSelection(fitnessFunction); //Default value

		this.selectionStrategies.add(new RankSelection(fitnessFunction));
		this.selectionStrategies.add(new RouletteSelection(fitnessFunction));
		this.selectionStrategies.add(new TournamentSelection(fitnessFunction));
		this.selectionStrategies.add(new TruncationSelection(numPop, fitnessFunction));
		
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

	class sortPop implements Comparator<Chromosome> {
		public int compare(Chromosome c1, Chromosome c2) {
			return fitnessFunction.fitness(c1) - fitnessFunction.fitness(c2);
		}
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

		for (int i = 0; i < curPop.size(); i++) {

			Chromosome chromToAdd1 = new Chromosome(cO.mutate3(curPop.get(i), mutate));
			Chromosome chromToAdd2 = new Chromosome(cO.mutate3(curPop.get(i), mutate));

			updatedSet.add(chromToAdd1);
			updatedSet.add(chromToAdd2);

		}

		curPop = new ArrayList<>();
		for (int i = 0; i < updatedSet.size(); i++) {
			curPop.add(updatedSet.get(i));
		}

		Collections.sort(curPop, new sortPop());
		updatedSet = new ArrayList<>();

	}

	public void elitism(int mutate, int n) {
		if (n == population.length) {
			for (int i = 0; i < curPop.size(); i++) {
				updatedSet.add(curPop.get(i));
			}
		}

		else {

			ArrayList<Chromosome> toRemove = new ArrayList<Chromosome>();
			for (int i = curPop.size() - 1; i > curPop.size() - n; i--) {
				Chromosome chromToAdd1 = new Chromosome(curPop.get(i).geneticCode());
				updatedSet.add(chromToAdd1);
				toRemove.add(curPop.get(i));
			}

			for (int i = 0; i < toRemove.size(); i++) {
				curPop.remove(toRemove.get(i));
			}

			for (int i = 0; i < curPop.size(); i++) {

				Chromosome chromToAdd = new Chromosome(cO.mutate3(curPop.get(i), mutate));

				updatedSet.add(chromToAdd);

			}
		}

		curPop = new ArrayList<>();
		for (int i = 0; i < updatedSet.size(); i++) {
			curPop.add(updatedSet.get(i));
		}

		Collections.sort(curPop, new sortPop());
		updatedSet = new ArrayList<>();

	}

	public void crossoverMutation(int crossoverPoint) {

		for (int i = 0; i < curPop.size() - 2; i++) {
			updatedSet.addAll(cO.crossoverWith(curPop.get(i), curPop.get(i + 1), crossoverPoint));
		}								

		curPop = new ArrayList<>();
		for (int i = 0; i < updatedSet.size(); i++) {
			curPop.add(updatedSet.get(i));
		}

		Collections.sort(curPop, new sortPop());
		updatedSet = new ArrayList<>();
	}

	public Chromosome returnFittest() {
		Collections.sort(curPop, new sortPop());
		Chromosome fittest = curPop.get(curPop.size() - 1);
		return fittest;
	}

	// returns average fitness for population
	public int returnAverage() {
		int countForAverage = 0;
		for (int i = 0; i < curPop.size(); i++) {
			countForAverage += fitnessFunction.fitness(curPop.get(i));
		}
		int average = countForAverage / curPop.size();
		System.out.println("Average: " + average);
		return average;
	}

	// returns highest fitness
	public int returnHighestAverage() {
		int highest = Integer.MIN_VALUE;
		for (int i = 0; i < curPop.size(); i++) {
			int currentFitness = fitnessFunction.fitness(curPop.get(i));
			if (currentFitness > highest) {
				highest = currentFitness;
			}
		}
		System.out.println();
		System.out.println("Highest: " + highest);
		return highest;
	}

	// returns lowest fitness
	public int returnLowestAverage() {
		int lowest = Integer.MAX_VALUE;
		for (int i = 0; i < curPop.size(); i++) {
			int currentFitness = fitnessFunction.fitness(curPop.get(i));
			if (currentFitness < lowest) {
				lowest = currentFitness;
			}

		}
		return lowest;
	}
}
