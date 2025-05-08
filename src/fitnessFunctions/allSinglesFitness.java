package fitnessFunctions;

import mainApp.Chromosome;

public class allSinglesFitness extends FitnessFunction{
	
	public int gene;
	
	public allSinglesFitness(int input) {
		gene = input;
	}

	@Override
	public int fitness(Chromosome chrom) {
		int count = 0;
		for (int j = 0; j < chrom.numberOfArrays(); j++) {
			for (int k = 0; k < chrom.numberOfGenesInArray(); k++) {
				if (chrom.geneticCode()[j][k] == gene) {
					count++;
				}
			}
		}
		return count;
	}

}
