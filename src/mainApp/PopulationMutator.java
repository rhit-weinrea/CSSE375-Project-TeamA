package mainApp;

import java.util.ArrayList;


public class PopulationMutator {

	private ChromosomeOperations cO = new ChromosomeOperations();
	
	public ArrayList<Chromosome> crossoverMutation(int crossoverPoint, ArrayList<Chromosome> curPop) {
		
		ArrayList<Chromosome> updatedPop = new ArrayList<Chromosome>();

		for (int i = 0; i < curPop.size() - 2; i++) {
			updatedPop.addAll(cO.crossoverWith(curPop.get(i), curPop.get(i + 1), crossoverPoint));
		}							
		
		return updatedPop;
	}
	
	public ArrayList<Chromosome> flipMutation(int mutate, ArrayList<Chromosome> curPop) {

		ArrayList<Chromosome> updatedPop = new ArrayList<Chromosome>();
		
		for (int i = 0; i < curPop.size(); i++) {

			Chromosome chromToAdd1 = new Chromosome(cO.mutate3(curPop.get(i), mutate));
			Chromosome chromToAdd2 = new Chromosome(cO.mutate3(curPop.get(i), mutate));

			updatedPop.add(chromToAdd1);
			updatedPop.add(chromToAdd2);

		}

		return updatedPop;

	}
}
