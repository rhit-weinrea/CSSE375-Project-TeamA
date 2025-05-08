package fitnessFunctions;

import mainApp.Chromosome;

public class orderedFitness extends FitnessFunction{
	
	public char gene;
	
	public orderedFitness(char input) {
		gene = input;
	}

	@Override
	public int fitness(Chromosome chrom) {
		int count = 0;
		String rawGene = chrom.asStringRaw();
		int i = 0;
		while (i < rawGene.length() - 1) {
			if (rawGene.charAt(i) == gene && rawGene.charAt(i + 1) == gene) {
				count++;
			}
			i++;
		}

		return count;
	}

}
