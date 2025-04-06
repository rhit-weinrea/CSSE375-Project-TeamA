package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import fitnessFunctions.FitnessFunction;
import fitnessFunctions.allSinglesFitness;
import fitnessFunctions.orderedFitness;
import mainApp.Chromosome;

public class FitnessTesting {
	
	private int[][] createGeneticCode(int x, int y, int numFilled) {
		if (x*y < numFilled) {
			throw new RuntimeException();
		}
		
		int[][] genCode = new int[x][y];
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				if(numFilled > 0) {
					genCode[i][j] = 1;
					numFilled--;
				} else {
					genCode[i][j] = 0;
				}
			}
		}
		return genCode;
	}

	@Test
	public void blankAllOnesTest() {
		FitnessFunction f1 = new allSinglesFitness(1);
		int[][] geneticCode = createGeneticCode(3, 3, 0);
		Chromosome crom = new Chromosome(geneticCode);
		assertEquals(0, f1.fitness(crom));
	}
	

	@Test
	public void someFilledAllOnesTest() {
		FitnessFunction f1 = new allSinglesFitness(1);
		int[][] geneticCode = createGeneticCode(3, 3, 2);
		Chromosome crom = new Chromosome(geneticCode);
		assertEquals(2, f1.fitness(crom));
	}
	
	@Test
	public void FilledAllOnesTest() {
		FitnessFunction f1 = new allSinglesFitness(1);
		int[][] geneticCode = createGeneticCode(3, 3, 9);
		Chromosome crom = new Chromosome(geneticCode);
		assertEquals(9, f1.fitness(crom));
	}
	

	@Test
	public void blankAllZerosTest() {
		FitnessFunction f1 = new allSinglesFitness(0);
		int[][] geneticCode = createGeneticCode(3, 3, 0);
		Chromosome crom = new Chromosome(geneticCode);
		assertEquals(9, f1.fitness(crom));
	}
	

	@Test
	public void someFilledAllZerosTest() {
		FitnessFunction f1 = new allSinglesFitness(0);
		int[][] geneticCode = createGeneticCode(3, 3, 2);
		Chromosome crom = new Chromosome(geneticCode);
		assertEquals(7, f1.fitness(crom));
	}
	
	@Test
	public void FilledAllZerosTest() {
		FitnessFunction f1 = new allSinglesFitness(0);
		int[][] geneticCode = createGeneticCode(3, 3, 9);
		Chromosome crom = new Chromosome(geneticCode);
		assertEquals(0, f1.fitness(crom));
	}

	@Test
	public void blankOrderedOnesTest() {
		FitnessFunction f1 = new orderedFitness('1');
		int[][] geneticCode = createGeneticCode(3, 3, 0);
		Chromosome crom = new Chromosome(geneticCode);
		assertEquals(0, f1.fitness(crom));
	}
	
	@Test
	public void fullOrderedOnesTest() {
		FitnessFunction f1 = new orderedFitness('1');
		int[][] geneticCode = createGeneticCode(3, 3, 9);
		Chromosome crom = new Chromosome(geneticCode);
		assertEquals(8, f1.fitness(crom));
	}
	
	@Test
	public void singleOrderedOnesTest() {
		FitnessFunction f1 = new orderedFitness('1');
		int[][] geneticCode = createGeneticCode(3, 3, 1);
		Chromosome crom = new Chromosome(geneticCode);
		assertEquals(0, f1.fitness(crom));
	}
	
	@Test
	public void twoOrderedOnesTest() {
		FitnessFunction f1 = new orderedFitness('1');
		int[][] geneticCode = createGeneticCode(3, 3, 2);
		Chromosome crom = new Chromosome(geneticCode);
		assertEquals(1, f1.fitness(crom));
	}
	
	@Test
	public void alternatingOrderedOnesTest() {
		FitnessFunction f1 = new orderedFitness('1');
		int[][] geneticCode = createGeneticCode(2, 2, 1);
		geneticCode[1][0] = 1;
		Chromosome crom = new Chromosome(geneticCode);
		assertEquals(0, f1.fitness(crom));
	}
	

	@Test
	public void someAndOneOrderedOnesTest() {
		FitnessFunction f1 = new orderedFitness('1');
		int[][] geneticCode = createGeneticCode(2, 2, 2);
		geneticCode[1][1] = 1;
		Chromosome crom = new Chromosome(geneticCode);
		assertEquals(1, f1.fitness(crom));
	}
	


	@Test
	public void blankOrderedZerosTest() {
		FitnessFunction f1 = new orderedFitness('0');
		int[][] geneticCode = createGeneticCode(3, 3, 0);
		Chromosome crom = new Chromosome(geneticCode);
		assertEquals(8, f1.fitness(crom));
	}
	
	@Test
	public void fullOrderedzeroesTest() {
		FitnessFunction f1 = new orderedFitness('0');
		int[][] geneticCode = createGeneticCode(3, 3, 9);
		Chromosome crom = new Chromosome(geneticCode);
		assertEquals(0, f1.fitness(crom));
	}
	
	@Test
	public void singleOrderedZerosTest() {
		FitnessFunction f1 = new orderedFitness('0');
		int[][] geneticCode = createGeneticCode(3, 3, 8);
		Chromosome crom = new Chromosome(geneticCode);
		assertEquals(0, f1.fitness(crom));
	}
	
	@Test
	public void twoOrderedZerosTest() {
		FitnessFunction f1 = new orderedFitness('0');
		int[][] geneticCode = createGeneticCode(3, 3, 7);
		Chromosome crom = new Chromosome(geneticCode);
		assertEquals(1, f1.fitness(crom));
	}
	
	@Test
	public void alternatingOrderedZerosTest() {
		FitnessFunction f1 = new orderedFitness('0');
		int[][] geneticCode = createGeneticCode(2, 2, 1);
		geneticCode[1][0] = 1;
		Chromosome crom = new Chromosome(geneticCode);
		assertEquals(0, f1.fitness(crom));
	}
	

	@Test
	public void someAndOneOrderedzerosTest() {
		FitnessFunction f1 = new orderedFitness('0');
		int[][] geneticCode = createGeneticCode(2, 2, 0);
		geneticCode[1][0] = 1;
		Chromosome crom = new Chromosome(geneticCode);
		assertEquals(1, f1.fitness(crom));
	}
}
