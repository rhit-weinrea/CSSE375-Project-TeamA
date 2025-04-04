package mainApp;

import java.util.ArrayList;
import java.util.Random;

public class ChromosomeOperations {
	
	private int[][] geneticCode;
	private int numberOfArrays;
	private int numberOfGenesInArray;
	private Chromosome chromosome;
	
	public ChromosomeOperations(Chromosome c) {
		this.geneticCode = c.geneticCode();
		this.numberOfArrays = c.numberOfArrays();
		this.numberOfGenesInArray = c.numberOfGenesInArray();
		this.chromosome = c;
		
	}
	
	
	
	public Chromosome mutatedOffspring(int n) {
		Chromosome offspring = new Chromosome(this.geneticCode);
		for (int k = 0; k < n; k++) {
			Random random = new Random();
			int randomArray = random.nextInt(offspring.numberOfArrays());
			int randomGeneInArray = random.nextInt(offspring.numberOfGenesInArray());
			if (offspring.geneticCode()[randomArray][randomGeneInArray] == 0) {
				offspring.geneticCode()[randomArray][randomGeneInArray] = 1;
			} else {
				offspring.geneticCode()[randomArray][randomGeneInArray] = 0;
			}
		}

		return offspring;
	}

	public ArrayList<Chromosome> crossoverWith(Chromosome mate, int crossoverPoint) {
		// Assumption: Parents have the same code size.
		ArrayList<Chromosome> offspring = new ArrayList<>();
		int[][] firstbornCode = new int[this.numberOfArrays][this.numberOfGenesInArray];
		int[][] secondbornCode = new int[this.numberOfArrays][this.numberOfGenesInArray];
		for (int j = 0; j < firstbornCode.length; j++) {
			for (int k = 0; k < firstbornCode[0].length; k++) {
				if (firstbornCode.length * j + k < crossoverPoint) {
					firstbornCode[j][k] = this.geneticCode[j][k];
					secondbornCode[j][k] = mate.geneticCode()[j][k];
				}

				else {
					firstbornCode[j][k] = mate.geneticCode()[j][k];
					secondbornCode[j][k] = this.geneticCode[j][k];
				}
			}
		}

		offspring.add(new Chromosome(firstbornCode));
		offspring.add(new Chromosome(secondbornCode));
		return offspring;
	}

	public int[][] mutate3(int n) {
		Random random = new Random();
		int[][] newGeneCode = new int[this.geneticCode.length][this.geneticCode[0].length];

		int rand = 0;
		for (int i = 0; i < this.geneticCode.length; i++) {
			for (int j = 0; j < this.geneticCode[0].length; j++) {
				newGeneCode[i][j] = this.geneticCode[i][j];
			}
		}

		for (int i = 0; i < newGeneCode.length; i++) {
			for (int j = 0; j < newGeneCode[0].length; j++) {
				rand = random.nextInt(100);
				if (rand < n) {
					if (newGeneCode[i][j] == 0) {
						newGeneCode[i][j] = 1;
					}

					else if (newGeneCode[i][j] == 1) {
						newGeneCode[i][j] = 0;
					}
				}
			}

		}
		// System.out.println(newGeneCode.toString());
		return newGeneCode;
	}

	public int calculateFitnessV1() {
		int count = 0;
		for (int j = 0; j < this.numberOfArrays; j++) {
			for (int k = 0; k < this.numberOfGenesInArray; k++) {
				if (this.geneticCode[j][k] == 1) {
					count++;
				}
			}
		}
		chromosome.fitness = count;
		
		return count;
	}

	// Calculates fitness based on how many consecutive bits are 1's
	// ex: 100110110111 has a fitness of 7
	public int calculateInARowFitness() {
		int count = 1;
		String rawGene = chromosome.asStringRaw();
		int i = 0;
		while (i < rawGene.length() - 1) {
			if (rawGene.charAt(i) == '1' && rawGene.charAt(i + 1) == '1') {
				count++;
			}
			i++;
		}
		if (rawGene.charAt(rawGene.length() - 2) == '1' && rawGene.charAt(rawGene.length() - 1) == '1') {
			count++;
		}

		if (count == 1) {
			return 0;
		}

		chromosome.fitness = count;
		return count;

	}

}
