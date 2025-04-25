package mainApp;

import java.util.ArrayList;

public class PopulationEliteMaker {

	private ChromosomeOperations cO = new ChromosomeOperations();

	public ArrayList<Chromosome> elitism(int mutate, int n, int popLength, ArrayList<Chromosome> curPop) {
		
		ArrayList<Chromosome> updatedPop = new ArrayList<Chromosome>();
		
		if (n == popLength) {
			for (int i = 0; i < curPop.size(); i++) {
				updatedPop.add(curPop.get(i));
			}
		}

		else {

			ArrayList<Chromosome> toRemove = new ArrayList<Chromosome>();
			for (int i = curPop.size() - 1; i > curPop.size() - n; i--) {
				Chromosome chromToAdd1 = new Chromosome(curPop.get(i).geneticCode());
				updatedPop.add(chromToAdd1);
				toRemove.add(curPop.get(i));
			}

			for (int i = 0; i < toRemove.size(); i++) {
				curPop.remove(toRemove.get(i));
			}

			for (int i = 0; i < curPop.size(); i++) {

				Chromosome chromToAdd = new Chromosome(cO.mutate3(curPop.get(i), mutate));

				updatedPop.add(chromToAdd);

			}
		}
		
		return updatedPop;
	}
}
