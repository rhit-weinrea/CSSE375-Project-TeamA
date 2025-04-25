package selectionStrategies;

import fitnessFunctions.FitnessFunction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import mainApp.Chromosome;
import mainApp.EvolutionLoop;

public abstract class SelectionStrategy {
	
	public FitnessFunction fitness;
    public EvolutionLoop.SelectionType type;
	
	protected SelectionStrategy(FitnessFunction fitness) {
		this.fitness = fitness;
	}
	
	class sortPop implements Comparator<Chromosome> {
		public int compare(Chromosome c1, Chromosome c2) {
			return fitness.fitness(c1) - fitness.fitness(c2);
		}
	}
	
	public ArrayList<Chromosome> select(ArrayList<Chromosome> curPop) 
	{
		Collections.sort(curPop, new sortPop());
		return curPop;
	}

	public void updateFitness(FitnessFunction fitnessFunction) {
		this.fitness = fitnessFunction;
	}
	
}
