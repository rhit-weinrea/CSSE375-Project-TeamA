package fitnessFunctions;

import mainApp.Chromosome;

public class orderedFitness extends FitnessFunction{
	
	char gene;
	
	public orderedFitness(char input) {
		gene = input;
	}

	@Override
	public int fitness(Chromosome chrom) {
		int count = 1;
		String rawGene = chrom.asStringRaw();
		int i = 0;
		while (i < rawGene.length() - 1) {
			if (rawGene.charAt(i) == gene && rawGene.charAt(i + 1) == gene) {
				count++;
			}
			i++;
		}
		if (rawGene.charAt(rawGene.length() - 2) == gene && rawGene.charAt(rawGene.length() - 1) == gene) {
			count++;
		}

		if (count == 1) {
			return 0;
		}

		return count;
	}

}
