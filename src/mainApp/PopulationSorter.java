package mainApp;

import java.util.Comparator;

import fitnessFunctions.FitnessFunction;

public class PopulationSorter implements Comparator<Chromosome>{
	
	FitnessFunction fitnessFunction;
	
	public PopulationSorter(FitnessFunction f) 
	{
		this.fitnessFunction = f;
	}

	public int compare(Chromosome c1, Chromosome c2) {
		return fitnessFunction.fitness(c1) - fitnessFunction.fitness(c2);
	}

}
