package mainApp;

import java.util.ArrayList;

import fitnessFunctions.FitnessFunction;

//Would be static but Java isn't supporting top-line classes being static
public class PopulationOperations {
	
	// returns average fitness for population
		public int returnAverage(ArrayList<Chromosome> curPop, FitnessFunction fitnessFunction) {
			int countForAverage = 0;
			for (int i = 0; i < curPop.size(); i++) {
				countForAverage += fitnessFunction.fitness(curPop.get(i));
			}
			int average = countForAverage / curPop.size();
			System.out.println("Average: " + average);
			return average;
		}

		// returns highest fitness
		public int returnHighestAverage(ArrayList<Chromosome> curPop, FitnessFunction fitnessFunction) {
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
		public int returnLowestAverage(ArrayList<Chromosome> curPop, FitnessFunction fitnessFunction) {
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
