package mainApp;

public class Population {
	Chromosome[] popArray;
	int popSize;
	private int populationVal, genomeSize;

	/**
	 * Creates Population
	 */
	public Population(int n, int genomeSize) {
		this.popSize = n;
		this.genomeSize = genomeSize;
		this.popArray = new Chromosome[this.popSize];
		for (int i = 0; i < this.popSize; i++) {
			this.popArray[i] = new Chromosome(genomeSize);
		}

	}

	public int returnPopSize() {
		return this.popArray.length;
	}

}
