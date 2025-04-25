package selectionStrategies;

import fitnessFunctions.FitnessFunction;
import java.util.ArrayList;
import java.util.Collections;
import mainApp.Chromosome;
import mainApp.EvolutionLoop;
import mainApp.PopulationSorter;

public abstract class SelectionStrategy{
	
	public FitnessFunction fitness;
    public EvolutionLoop.SelectionType type;
	
	protected SelectionStrategy(FitnessFunction fitness) {
		this.fitness = fitness;
	}
	
	public ArrayList<Chromosome> select(ArrayList<Chromosome> curPop) 
	{
		Collections.sort(curPop, new PopulationSorter(fitness));
		return curPop;
	}

	public void updateFitness(FitnessFunction fitnessFunction) {
		this.fitness = fitnessFunction;
	}
	
}
