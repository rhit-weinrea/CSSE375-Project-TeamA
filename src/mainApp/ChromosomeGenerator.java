package mainApp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Converts the contents of a text file into a Chromosome.
 * 
 * @author Wynesakia Akamah & Abby Weinreb
 *
 */
public class ChromosomeGenerator {
	private String filename;
	private Chromosome translatedChromosome;

	public ChromosomeGenerator(String filename) throws FileNotFoundException {
		this.filename = "ChromosomesText/" + filename + ".txt";
		FileReader reader = new FileReader(this.filename);
		Scanner scanner = new Scanner(reader);
		String rawGeneticCode = scanner.nextLine();

		// Debugger print
		System.out.println(rawGeneticCode);
		System.out.println();

		if (rawGeneticCode.length() == 20) {
			int numberOfArrays = 4;
			int lengthOfArray = 5;
			int[][] inputGeneticCode = new int[numberOfArrays][lengthOfArray];
			for (int j = 0; j < numberOfArrays; j++) {
				for (int k = 0; k < lengthOfArray; k++) {
					if (rawGeneticCode.charAt(numberOfArrays * j + k) == '0') {
						inputGeneticCode[j][k] = 0;
					}

					else if (rawGeneticCode.charAt(numberOfArrays * j + k) == '1') {
						inputGeneticCode[j][k] = 1;
					}

					else {
						throw new IllegalArgumentException();
					}
				}
			}
			this.translatedChromosome = new Chromosome(inputGeneticCode);

			// Debugger print
			System.out.println(this.translatedChromosome.asString());
			System.out.println();
		}

		else if (checkIfPerfectSquare(rawGeneticCode.length())) {
			int numberOfArrays = (int) Math.sqrt(rawGeneticCode.length());
			int lengthOfArray = numberOfArrays;
			int[][] inputGeneticCode = new int[numberOfArrays][lengthOfArray];
			for (int j = 0; j < numberOfArrays; j++) {
				for (int k = 0; k < lengthOfArray; k++) {
					if (rawGeneticCode.charAt(numberOfArrays * j + k) == '0') {
						inputGeneticCode[j][k] = 0;
					}

					else if (rawGeneticCode.charAt(numberOfArrays * j + k) == '1') {
						inputGeneticCode[j][k] = 1;
					}

					else {
						throw new IllegalArgumentException();
					}
				}
			}
			this.translatedChromosome = new Chromosome(inputGeneticCode);

			// Debugger print
			System.out.println(this.translatedChromosome.asString());
			System.out.println();
		}

		else {
			throw new IllegalArgumentException();
		}
	}

	public Chromosome getChromosome() {
		return this.translatedChromosome;
	}

	private boolean checkIfPerfectSquare(int n) {
		int roundedSquareRoot = (int) Math.sqrt(n);
		return Math.pow(roundedSquareRoot, 2) == n;
	}
}
