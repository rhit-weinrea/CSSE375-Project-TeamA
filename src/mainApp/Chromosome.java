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
	private int numberOfArrays;
	private int numberOfGenesInArray;
	private ChromosomeOperations cOperations;
	
	private static final Random RANDOM = new Random();

	public Chromosome(int genomeSize) {
		this.cOperations = new ChromosomeOperations(this);
		initializeChromosome(genomeSize);
		
	}
	
	public Chromosome(int[][] geneticCode) {
		this.cOperations = new ChromosomeOperations(this);
		initializeCustomChromosome(geneticCode);
		
	}

	private void initializeCustomChromosome(int[][] geneticCode) {
		this.geneticCode = geneticCode;
		this.fitness = cOperations.calculateFitnessV1();
	}

	private void initializeChromosome(int genomeSize) {
		if (checkIfPerfectSquare(genomeSize)) {
			this.numberOfArrays = (int) (Math.sqrt(genomeSize));
			this.numberOfGenesInArray = this.numberOfArrays;		
			this.geneticCode = generateGeneticCode();
		}
		else {
			throw new IllegalArgumentException();
		}
		this.fitness = cOperations.calculateFitnessV1();
	}

	private int[][] generateGeneticCode() {
		int[][] randomGeneticCode = new int[numberOfArrays][numberOfGenesInArray];
		for (int j = 0; j < numberOfArrays; j++) {
			for (int k = 0; k < numberOfGenesInArray; k++) {
				randomGeneticCode[j][k] = RANDOM.nextInt(2);
			}
		}
		return randomGeneticCode;
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


	public long getDecimal() {
		long decimalRep = 0;
		for (int j = 0; j < this.numberOfArrays; j++) {
			for (int k = 0; k < this.numberOfGenesInArray; k++) {
				decimalRep += this.geneticCode[j][k] * Math.pow(2, j * this.numberOfArrays + k);
			}
		}
		return decimalRep;
	}

	

	private boolean checkIfPerfectSquare(int n) {
		int roundedSquareRoot = (int) Math.sqrt(n);
		return Math.pow(roundedSquareRoot, 2) == n;
	}

}
