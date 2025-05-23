package mainApp;

import java.util.ArrayList;
import java.util.Random;

public class ChromosomeOperations {
	

	
	public ChromosomeOperations() {
		
		
	}
	
	
	
	public Chromosome mutatedOffspring(Chromosome c, int n) {
		Chromosome offspring = new Chromosome(c.geneticCode());
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

	public ArrayList<Chromosome> crossoverWith(Chromosome mate1, Chromosome mate2, int crossoverPoint) {
		// Assumption: Parents have the same code size.
		ArrayList<Chromosome> offspring = new ArrayList<>();
		int[][] firstbornCode = new int[mate1.numberOfArrays()][mate1.numberOfGenesInArray()];
		int[][] secondbornCode = new int[mate1.numberOfArrays()][mate1.numberOfGenesInArray()];
		for (int j = 0; j < firstbornCode.length; j++) {
			for (int k = 0; k < firstbornCode[0].length; k++) {
				if (firstbornCode.length * j + k < crossoverPoint) {
					firstbornCode[j][k] = mate1.geneticCode()[j][k];
					secondbornCode[j][k] = mate2.geneticCode()[j][k];
				}

				else {
					firstbornCode[j][k] = mate2.geneticCode()[j][k];
					secondbornCode[j][k] = mate1.geneticCode()[j][k];
				}
			}
		}

		offspring.add(new Chromosome(firstbornCode));
		offspring.add(new Chromosome(secondbornCode));
		return offspring;
	}

	public int[][] mutate3(Chromosome c, int n) {
		Random random = new Random();
		int[][] newGeneCode = new int[c.geneticCode().length][c.geneticCode()[0].length];

		int rand = 0;
		for (int i = 0; i < c.geneticCode().length; i++) {
			for (int j = 0; j < c.geneticCode()[0].length; j++) {
				newGeneCode[i][j] = c.geneticCode()[i][j];
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

}
