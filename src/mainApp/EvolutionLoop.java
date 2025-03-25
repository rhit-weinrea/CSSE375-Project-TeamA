package mainApp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EvolutionLoop {

	private Population population;
	private ArrayList<Chromosome> updatedSet;
	private ArrayList<Chromosome> curPop;
	private int numPop;

	public EvolutionLoop(int numPop, int genomeSize) {
		this.population = new Population(numPop, genomeSize);
		this.updatedSet = new ArrayList<Chromosome>();
		this.curPop = new ArrayList<Chromosome>();
		this.numPop = population.returnPopSize();

	}

	class sortPop implements Comparator<Chromosome> {
		public int compare(Chromosome c1, Chromosome c2) {
			return c1.fitness - c2.fitness;
		}
	}

	public int returnGeneSize() {
		return this.population.popArray[0].numberOfArrays() * this.population.popArray[0].numberOfGenesInArray();
	}

	public void createPop() {
		for (int i = 0; i < this.numPop; i++) {
			curPop.add(population.popArray[i]);
		}

		for (int i = 0; i < curPop.size(); i++) {
			System.out.println(curPop.get(i).fitness);
		}
	}

	public void truncationSelection() {
		Collections.sort(curPop, new sortPop());
//		

		ArrayList<Chromosome> toRemove = new ArrayList<Chromosome>();
		int count = 0;
		if (curPop.size() == numPop) {
			for (int i = 0; i < curPop.size(); i++) {
				if (count < (curPop.size() / 2)) {
					toRemove.add(curPop.get(i));
					count++;
				}
			}
		}

		for (int j = 0; j < toRemove.size(); j++) {
			curPop.remove(toRemove.get(j));

		}
	}

	public void rankSelection() {
		Collections.sort(curPop, new sortPop());
		double[] valuesArray = new double[curPop.size()];
		Chromosome[] secondArray = new Chromosome[curPop.size()];
		int total = 0;
		double rankPercentSum = 0;

		for (int i = 0; i < curPop.size() - 1; i++) {
			total += i;
		}

		for (int i = 0; i < curPop.size(); i++) {
			total += curPop.get(i).fitness;
			double percentage = (double) i / (double) total;
			rankPercentSum += percentage;
			valuesArray[i] = rankPercentSum;
			secondArray[i] = curPop.get(i);

		}

		curPop = new ArrayList<>();
		for (int i = 0; i <= 50; i++) {
			double chance = Math.random();
			int beforeSize = curPop.size();
			for (int j = 0; j < valuesArray.length; j++) {

				if (valuesArray[j] > chance) {

					curPop.add(secondArray[j]);
					break;
				}

			}
			if (curPop.size() == beforeSize) {
				curPop.add(secondArray[secondArray.length - 1]);
			}

		}
	}

	public void roulette() {
		Collections.sort(curPop, new sortPop());
		double[] valuesArray = new double[curPop.size()];
		Chromosome[] secondArray = new Chromosome[curPop.size()];
		int total = 0;
		double rankPercentSum = 0;

		for (int i = 0; i < curPop.size() - 1; i++) {
			total += curPop.get(i).fitness;
		}

		for (int i = 0; i < curPop.size(); i++) {
			total += curPop.get(i).fitness;
			double percentage = (double) i / (double) total;
			rankPercentSum += percentage;
			valuesArray[i] = rankPercentSum;
			secondArray[i] = curPop.get(i);

		}

		curPop = new ArrayList<>();
		for (int i = 0; i <= 50; i++) {
			double chance = Math.random();
			int beforeSize = curPop.size();
			for (int j = 0; j < valuesArray.length; j++) {

				if (valuesArray[j] > chance) {

					curPop.add(secondArray[j]);
					break;
				}

			}
			if (curPop.size() == beforeSize) {
				curPop.add(secondArray[secondArray.length - 1]);
			}

		}
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
		if (n == population.popSize) {
			for (int i = 0; i < curPop.size(); i++) {
				updatedSet.add(curPop.get(i));
			}
		}

		else {

			ArrayList<Chromosome> toRemove = new ArrayList<Chromosome>();
			for (int i = curPop.size() - 1; i < curPop.size() - n; i--) {
				Chromosome chromToAdd1 = new Chromosome(curPop.get(i).geneticCode());
				updatedSet.add(chromToAdd1);
				toRemove.add(curPop.get(i));
			}

			for (int i = 0; i < toRemove.size(); i++) {
				curPop.remove(toRemove.get(i));
			}

			for (int i = 0; i < curPop.size(); i++) {

				Chromosome chromToAdd1 = new Chromosome(curPop.get(i).mutate3(mutate));
				Chromosome chromToAdd2 = new Chromosome(curPop.get(i).mutate3(mutate));

				updatedSet.add(chromToAdd1);
				updatedSet.add(chromToAdd2);

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
		// System.out.println("Size = " + updatedSet.size());
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
