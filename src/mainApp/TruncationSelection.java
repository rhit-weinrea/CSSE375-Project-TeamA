package mainApp;

import java.util.ArrayList;

public class TruncationSelection extends SelectionStrategy {
	
	private int numPop;
	
	public TruncationSelection(int numPop) 
	{
		this.numPop = numPop;
	}
	
	@Override
	public ArrayList<Chromosome> select(ArrayList<Chromosome> curPop) 
	{
		ArrayList<Chromosome> newPop = super.select(curPop);
		ArrayList<Chromosome> toRemove = new ArrayList<Chromosome>();
		int count = 0;
		if (newPop.size() == numPop) {
			for (int i = 0; i < newPop.size(); i++) {
				if (count < (newPop.size() / 2)) {
					toRemove.add(newPop.get(i));
					count++;
				}
			}
		}

		for (int j = 0; j < toRemove.size(); j++) {
			newPop.remove(toRemove.get(j));

		}
		
		return newPop;
	}
}
