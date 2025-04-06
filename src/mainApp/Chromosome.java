package mainApp;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JPanel;



public class Chromosome {

	private int[][] geneticCode;
	private static int numberOfArrays;
	private static int numberOfGenesInArray;
	private ChromosomeOperations cO = new ChromosomeOperations();
	
	private static final Random RANDOM = new Random();

	public Chromosome(int genomeSize) {
		initializeChromosome(genomeSize);
		
		
	}
	
	public Chromosome(int[][] geneticCode) {
		initializeCustomChromosome(geneticCode);
		
		
	}

	private void initializeCustomChromosome(int[][] geneticCode) {
		this.geneticCode = geneticCode;
		this.numberOfArrays = geneticCode.length;
		this.numberOfGenesInArray = geneticCode[0].length;
	}

	private void initializeChromosome(int genomeSize) {
		if (checkIfPerfectSquare(genomeSize)) {
			Chromosome.numberOfArrays = (int) (Math.sqrt(genomeSize));
			Chromosome.numberOfGenesInArray = Chromosome.numberOfArrays;		
			this.geneticCode = generateGeneticCode();
		}
		else {
			throw new IllegalArgumentException();
		}
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
		return Chromosome.numberOfArrays;
	}

	public int numberOfGenesInArray() {
		return Chromosome.numberOfGenesInArray;
	}

	public int numberOfGenes() {
		return Chromosome.numberOfArrays * numberOfGenesInArray;
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
		for (int j = 0; j < Chromosome.numberOfArrays; j++) {
			for (int k = 0; k < Chromosome.numberOfGenesInArray; k++) {
				decimalRep += this.geneticCode[j][k] * Math.pow(2, j * this.numberOfArrays + k);
			}
		}
		return decimalRep;
	}
	

	

	private boolean checkIfPerfectSquare(int n) {
		int roundedSquareRoot = (int) Math.sqrt(n);
		return Math.pow(roundedSquareRoot, 2) == n;
	}

	public void mutate3(int n) {
		cO.mutate3(this, n);
	}

}
