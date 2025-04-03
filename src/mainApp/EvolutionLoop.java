package mainApp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EvolutionLoop {

	private Chromosome[] population;
	private ArrayList<Chromosome> updatedSet;
	private ArrayList<Chromosome> curPop;
	private int numPop;
	private SelectionStrategy selectionStrategy;

	public EvolutionLoop(int numPop, int genomeSize) {
		this.numPop = numPop;
		this.population = new Chromosome[numPop];
		for (int i = 0; i < numPop; i++) {
			this.population[i] = new Chromosome(genomeSize);
		}
		this.updatedSet = new ArrayList<Chromosome>();
		this.curPop = new ArrayList<Chromosome>();
		this.numPop = population.returnPopSize();
		this.selectionStrategy = new RankSelection(); //Default value
	}
	
	public enum SelectionType 
	{
		TRUNCATION,
		RANK,
		TOURNAMENT,
		ROULETTE
	}

	class sortPop implements Comparator<Chromosome> {
		public int compare(Chromosome c1, Chromosome c2) {
			return c1.fitness - c2.fitness;
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
			System.out.println(curPop.get(i).fitness);
		}
	}
	
	public void changeSelectionStrategy(SelectionType type) 
	{
		if(type == SelectionType.TRUNCATION) 
		{
			this.selectionStrategy = new TruncationSelection(numPop);
		}
		else if(type == SelectionType.RANK) 
		{
			this.selectionStrategy = new RankSelection();
		}
		else if(type == SelectionType.ROULETTE) 
		{
			this.selectionStrategy = new RouletteSelection();
		}
		else if(type == SelectionType.TOURNAMENT) 
		{
			this.selectionStrategy = new TournamentSelection();
		}
	}
	
	public void selection() 
	{
		curPop = selectionStrategy.select(curPop);
	}

	public void flipMutation(int mutate) {

		for (int i = 0; i < curPop.size(); i++) {

			Chromosome chromToAdd1 = new Chromosome(curPop.get(i).mutate3(mutate));
			Chromosome chromToAdd2 = new Chromosome(curPop.get(i).mutate3(mutate));

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

				Chromosome chromToAdd = new Chromosome(curPop.get(i).mutate3(mutate));

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
			updatedSet.addAll(curPop.get(i).crossoverWith(curPop.get(i + 1), crossoverPoint));
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
			countForAverage += curPop.get(i).fitness;
		}
		int average = countForAverage / curPop.size();
		System.out.println("Average: " + average);
		return average;
	}

	// returns highest fitness
	public int returnHighestAverage() {
		int highest = Integer.MIN_VALUE;
		for (int i = 0; i < curPop.size(); i++) {
			if (curPop.get(i).fitness > highest) {
				highest = curPop.get(i).fitness;
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
			if (curPop.get(i).fitness < lowest) {
				lowest = curPop.get(i).fitness;
			}

		}
		return lowest;
	}

}
