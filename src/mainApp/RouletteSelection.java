package mainApp;

import java.util.ArrayList;

public class RouletteSelection extends SelectionStrategy {

	
	@Override
	public ArrayList<Chromosome> select(ArrayList<Chromosome> curPop) 
	{
		ArrayList<Chromosome> sortedPop = super.select(curPop);
		ArrayList<Chromosome> newPop = new ArrayList<Chromosome>();
		
		int totalFitness = 0;
		double[] fitnessArray = new double[sortedPop.size()];
		for(int i = 0; i < sortedPop.size(); i++) 
		{
			int fitness = sortedPop.get(i).fitness;
			//System.out.println("fitness is " + fitness + " at array index " + i);
			fitnessArray[i] = fitness;
			totalFitness += fitness;
		}
		
		for(int i = 0; i < sortedPop.size(); i++) 
		{
			fitnessArray[i] = fitnessArray[i] / totalFitness;
		}
		
		int average = 0;
		for (int i = 0; i < 50; i++) {

			int count = 0;
			double percentChoose = Math.random();
			//System.out.println("percent is" + percentChoose);
			
			for (int j = fitnessArray.length - 1; j >= 0; j--) {
				
				if (fitnessArray[j] >= percentChoose) {

					//System.out.println("This took iterations: " + count);
					average += count;
					Chromosome chromToAdd = sortedPop.get(j);
					newPop.add(chromToAdd);
					break;
				}
				else 
				{
					count++;
					percentChoose -= fitnessArray[j];
					//System.out.println("new percent is" + percentChoose);
				}

			}

		}
		average = average / 50;
		System.out.println("avg iterations: " + average);
		
		return newPop;
	}
}
