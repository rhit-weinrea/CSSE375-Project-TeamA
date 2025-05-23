package selectionStrategies;

import fitnessFunctions.FitnessFunction;
import java.util.ArrayList;
import mainApp.Chromosome;
import mainApp.EvolutionLoop.SelectionType;

public class RankSelection extends SelectionStrategy {
	
	public RankSelection(FitnessFunction fitness) {
		super(fitness, SelectionType.RANK);
	}

	@Override
	public ArrayList<Chromosome> select(ArrayList<Chromosome> curPop) 
	{
		ArrayList<Chromosome> newPop = super.select(curPop);
		double[] valuesArray = new double[newPop.size()];
		Chromosome[] secondArray = new Chromosome[newPop.size()];
		int total = 0;
		double rankPercentSum = 0;

		for (int i = 0; i < newPop.size() - 1; i++) {
			total += i;
		}

		for (int i = 0; i < newPop.size(); i++) {
			total += super.fitness.fitness(newPop.get(i));
			double percentage = (double) i / (double) total;
			rankPercentSum += percentage;
			valuesArray[i] = rankPercentSum;
			secondArray[i] = newPop.get(i);

		}

		newPop = new ArrayList<>();
		for (int i = 0; i <= 50; i++) {
			double chance = Math.random();
			int beforeSize = newPop.size();
			for (int j = 0; j < valuesArray.length; j++) {

				if (valuesArray[j] > chance) {

					curPop.add(secondArray[j]);
					break;
				}

			}
			if (newPop.size() == beforeSize) {
				newPop.add(secondArray[secondArray.length - 1]);
			}

		}
		
		return newPop;
	}
}
