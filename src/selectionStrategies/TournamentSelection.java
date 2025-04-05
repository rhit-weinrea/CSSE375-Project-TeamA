package selectionStrategies;

import java.util.ArrayList;
import java.util.Random;

import fitnessFunctions.FitnessFunction;
import mainApp.Chromosome;

public class TournamentSelection extends SelectionStrategy {
	
	private Random r;
	
	public TournamentSelection(FitnessFunction fitness) 
	{
		super(fitness);
		this.r = new Random();
	}
	
	@Override
	public ArrayList<Chromosome> select(ArrayList<Chromosome> curPop) 
	{
		ArrayList<Chromosome> sortedPop = super.select(curPop);
		ArrayList<Chromosome> newPop = new ArrayList<Chromosome>();
		
		for(int i = 0; i < sortedPop.size()/2; i++) 
		{
			int indexIndividualOne = r.nextInt(sortedPop.size());
			int indexIndividualTwo = r.nextInt(sortedPop.size());
			newPop.add(sortedPop.get(Math.max(indexIndividualOne, indexIndividualTwo)));
		}
		
		return newPop;
	}
}
