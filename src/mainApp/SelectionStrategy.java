package mainApp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public abstract class SelectionStrategy {
	
	class sortPop implements Comparator<Chromosome> {
		public int compare(Chromosome c1, Chromosome c2) {
			return c1.fitness - c2.fitness;
		}
	}
	
	public ArrayList<Chromosome> select(ArrayList<Chromosome> curPop) 
	{
		Collections.sort(curPop, new sortPop());
		return curPop;
	}
	
}
