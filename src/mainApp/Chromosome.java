package mainApp;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Sets up a Chromosome to be drawn and analyzed.
 * 
 * @author Wynesakia Akamah & Abby Weinreb
 *
 */

public class Chromosome {

	public int fitness;

	private int[][] geneticCode;
	private int numberOfArrays, numberOfGenesInArray;

	public Chromosome() {
		Random random = new Random();

		this.numberOfArrays = 10;
		this.numberOfGenesInArray = 10;
		int[][] randomGeneticCode = new int[numberOfArrays][numberOfGenesInArray];
		for (int j = 0; j < numberOfArrays; j++) {
			for (int k = 0; k < numberOfGenesInArray; k++) {
				randomGeneticCode[j][k] = random.nextInt(2);
			}
		}
		this.geneticCode = randomGeneticCode;
		this.fitness = this.calculateInARowFitness();
		System.out.println(this.fitness);
	}

	public Chromosome(int genomeSize) {
		if (genomeSize == 20) {
			Random random = new Random();
			this.numberOfArrays = 4;
			this.numberOfGenesInArray = 5;
			int[][] randomGeneticCode = new int[this.numberOfArrays][this.numberOfGenesInArray];
			for (int j = 0; j < this.numberOfArrays; j++) {
				for (int k = 0; k < this.numberOfGenesInArray; k++) {
					randomGeneticCode[j][k] = random.nextInt(2);
				}
			}
			this.geneticCode = randomGeneticCode;
		}

		else if (checkIfPerfectSquare(genomeSize)) {
			Random random = new Random();
			this.numberOfArrays = (int) (Math.sqrt(genomeSize));
			this.numberOfGenesInArray = this.numberOfArrays;
			int[][] randomGeneticCode = new int[this.numberOfArrays][this.numberOfGenesInArray];
			for (int j = 0; j < this.numberOfArrays; j++) {
				for (int k = 0; k < this.numberOfGenesInArray; k++) {
					if (random.nextBoolean()) {
						randomGeneticCode[j][k] = 1;
					} else {
						randomGeneticCode[j][k] = 0;
					}

				}
			}
			this.geneticCode = randomGeneticCode;
		}

		else {
			throw new IllegalArgumentException();
		}

		this.fitness = this.calculateFitnessV1();
		System.out.println();
	}

	public Chromosome(int[][] geneticCode) {
		this.geneticCode = new int[geneticCode.length][geneticCode[0].length];
		this.numberOfArrays = this.geneticCode.length;
		this.numberOfGenesInArray = this.geneticCode[0].length;
		for (int j = 0; j < this.numberOfArrays; j++) {
			for (int k = 0; k < this.numberOfGenesInArray; k++) {
				this.geneticCode[j][k] = geneticCode[j][k];
			}
		}
		this.fitness = this.calculateFitnessV1();
	}

	public int[][] geneticCode() {
		return this.geneticCode;
	}

	public int numberOfArrays() {
		return this.numberOfArrays;
	}

	public int numberOfGenesInArray() {
		return this.numberOfGenesInArray;
	}

	public int numberOfGenes() {
		return this.numberOfArrays * numberOfGenesInArray;
	}

	public void setGeneticCode(int[][] geneticCode) {
		this.geneticCode = geneticCode;
	}

	/**
	 * Converts the Chromosome's information into a readable String.
	 * 
	 * @author akamahwa
	 *
	 */
	public String asString() {
		String geneticCodeString = "";
		for (int[] array : this.geneticCode) {
			for (int gene : array) {
				geneticCodeString += gene + " ";
			}
			geneticCodeString += "\n              ";
		}
		return "Genetic Code: " + geneticCodeString;
	}

	public String asStringRaw() {
		String geneticCodeString = "";
		for (int[] array : this.geneticCode) {
			for (int gene : array) {
				geneticCodeString += gene;
			}
		}
		return geneticCodeString;
	}

	/**
	 * Treats a Chromosome's genetic code as a binary number and converts it into a
	 * decimal number.
	 * 
	 * @author akamahwa
	 *
	 */
	public long getDecimal() {
		long decimalRep = 0;
		for (int j = 0; j < this.numberOfArrays; j++) {
			for (int k = 0; k < this.numberOfGenesInArray; k++) {
				decimalRep += this.geneticCode[j][k] * Math.pow(2, j * this.numberOfArrays + k);
			}
		}
		return decimalRep;
	}

	public Chromosome mutatedOffspring(int n) {
		Chromosome offspring = new Chromosome(this.geneticCode);
		for (int k = 0; k < n; k++) {
			Random random = new Random();
			int randomArray = random.nextInt(offspring.numberOfArrays);
			int randomGeneInArray = random.nextInt(offspring.numberOfGenesInArray);
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
		this.fitness = count;
		;
		return count;
	}

	// Calculates fitness based on how many consecutive bits are 1's
	// ex: 100110110111 has a fitness of 7
	public int calculateInARowFitness() {
		int count = 1;
		int curCount = 0;
		String rawGene = this.asStringRaw();
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

		this.fitness = count;
		return count;

	}

	public int calculateBorderFitness() {
		int count = 0;
		for (int i = 0; i < this.numberOfArrays; i++) {
			for (int j = 0; j < this.numberOfGenesInArray; j++) {
				if (i == 0 || j == 0 || i == this.numberOfArrays - 1 || j == this.numberOfGenesInArray - 1) {
					count += this.geneticCode[i][j];
				}
			}
		}
		return count;
	}

	/**
	 * Quantifies the difference between two Chromosome's genetic code.
	 * 
	 * @author akamahwa
	 *
	 */
	public int calculateHammingDistance(Chromosome otherChromosome) {
		int hammingDistance = 0;
		for (int j = 0; j < this.numberOfArrays; j++) {
			for (int k = 0; k < this.numberOfGenesInArray; k++) {
				if (this.geneticCode[j][k] != otherChromosome.geneticCode[j][k]) {
					hammingDistance++;
				}
			}
		}
		return hammingDistance;
	}

	/**
	 * Quantifies the similarity between two Chromosome's genetic code.
	 * 
	 * @author akamahwa
	 *
	 */
	int calculateInverseHammingDistance(Chromosome otherChromosome) {
		int inverseHammingDistance = 0;
		for (int j = 0; j < this.numberOfArrays; j++) {
			for (int k = 0; k < this.numberOfGenesInArray; k++) {
				if (this.geneticCode[j][k] == otherChromosome.geneticCode[j][k]) {
					inverseHammingDistance++;
				}
			}
		}
		return inverseHammingDistance;
	}

	public int calculateFitnessMatchOtherChromosome(Chromosome perfectSpecimen) {
		return 100 * this.calculateHammingDistance(perfectSpecimen) / this.numberOfGenes();
	}

	private boolean checkIfPerfectSquare(int n) {
		int roundedSquareRoot = (int) Math.sqrt(n);
		return Math.pow(roundedSquareRoot, 2) == n;
	}

}
