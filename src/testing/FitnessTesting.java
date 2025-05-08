package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import fitnessFunctions.FitnessFunction;
import fitnessFunctions.allSinglesFitness;
import fitnessFunctions.orderedFitness;
import mainApp.Chromosome;
import mainApp.EvolutionLoop;
import mainApp.EvolutionLoop.FitnessType;

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
	
	public void generateSinglesFitnessTest(int singleNumber, int x, int y, int numFilled, int goal) {
		FitnessFunction f1 = new allSinglesFitness(singleNumber);
		int[][] geneticCode = createGeneticCode(x, y, numFilled);
		Chromosome crom = new Chromosome(geneticCode);
		assertEquals(goal, f1.fitness(crom));
	}

	@Test
	public void blankAllOnesTest() {
		generateSinglesFitnessTest(1, 3, 3, 0, 0);
	}
	

	@Test
	public void someFilledAllOnesTest() {
		generateSinglesFitnessTest(1, 3, 3, 2, 2);
	}
	
	@Test
	public void FilledAllOnesTest() {
		generateSinglesFitnessTest(1, 3, 3, 9, 9);
	}
	

	@Test
	public void blankAllZerosTest() {
		generateSinglesFitnessTest(0, 3, 3, 0, 9);
	}
	

	@Test
	public void someFilledAllZerosTest() {
		generateSinglesFitnessTest(0, 3, 3, 2, 7);
	}
	
	@Test
	public void FilledAllZerosTest() {
		generateSinglesFitnessTest(0, 3, 3, 9, 0);
	}
	
	public void orderedGenericTest(char character, int x, int y, int numFilled, int goal) {
		FitnessFunction f1 = new orderedFitness(character);
		int[][] geneticCode = createGeneticCode(x, y, numFilled);
		Chromosome crom = new Chromosome(geneticCode);
		assertEquals(goal, f1.fitness(crom));
	}

	@Test
	public void blankOrderedOnesTest() {
		orderedGenericTest('1', 3, 3, 0, 0);
	}
	
	@Test
	public void fullOrderedOnesTest() {
		orderedGenericTest('1', 3, 3, 9, 8);
	}
	
	@Test
	public void singleOrderedOnesTest() {
		orderedGenericTest('1', 3, 3, 1, 0);
	}
	
	@Test
	public void twoOrderedOnesTest() {
		orderedGenericTest('1', 3, 3, 2, 1);
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
		orderedGenericTest('0', 3, 3, 0, 8);
	}
	
	@Test
	public void fullOrderedzeroesTest() {
		orderedGenericTest('0', 3, 3, 9, 0);
	}
	
	@Test
	public void singleOrderedZerosTest() {
		orderedGenericTest('0', 3, 3, 8, 0);
	}
	
	@Test
	public void twoOrderedZerosTest() {
		orderedGenericTest('0', 3, 3, 7, 1);
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
	
	@Test
	public void addAllOnesFitnessType() {
		EvolutionLoop loop = new EvolutionLoop(10, 100);
		
		loop.changeFitnessType(FitnessType.ALLONES);
	
		allSinglesFitness test = (allSinglesFitness) loop.fitnessFunction;
		assertEquals(1, test.gene);
	}
	
	@Test
	public void addOrderedOnesFitnessType() {
		EvolutionLoop loop = new EvolutionLoop(10, 100);
		
		loop.changeFitnessType(FitnessType.ORDEREDONES);
	
		orderedFitness test = (orderedFitness) loop.fitnessFunction;
		assertEquals('1', test.gene);
	}
	
	@Test
	public void addAllZerosFitnessType() {
		EvolutionLoop loop = new EvolutionLoop(10, 100);
		
		loop.changeFitnessType(FitnessType.ALLZEROS);
	
		allSinglesFitness test = (allSinglesFitness) loop.fitnessFunction;
		assertEquals(0, test.gene);
	}
	
	@Test
	public void addOrderedZerosFitnessType() {
		EvolutionLoop loop = new EvolutionLoop(10, 100);
		
		loop.changeFitnessType(FitnessType.ORDEREDZEROS);
	
		orderedFitness test = (orderedFitness) loop.fitnessFunction;
		assertEquals('0', test.gene);
	}
}
