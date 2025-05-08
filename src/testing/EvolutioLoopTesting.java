package testing;

import java.util.ArrayList;
import mainApp.Chromosome;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import mainApp.EvolutionLoop;
import mainApp.EvolutionLoop.SelectionType;
import testing.Mocks.TestingFitnessFunction;
import testing.Mocks.TestingSelectionStrategy;

public class EvolutioLoopTesting {
	
	private EvolutionLoop createTestLoop() {
	    EvolutionLoop loop = new EvolutionLoop(10, 0);
	    loop.createPop();
	    return loop;
	}
	
	@Test
	public void ConstructionTest() {
	    EvolutionLoop EL = new EvolutionLoop(10, 9);
	    EL.createPop();
	    EL.changeSelectionStrategy(SelectionType.RANK);
	    EL.selection();
	    Chromosome fittest = EL.returnFittest();
	    assertTrue(fittest != null); 
	}

	@Test
	public void testReturnLowestAverage(){
		EvolutionLoop loop = createTestLoop();
		loop.fitnessFunction = new TestingFitnessFunction();

		int result = loop.returnLowestAverage();

		assertEquals(1, result);
	}

	@Test
	public void testReturnHighestAverage(){
		EvolutionLoop loop = createTestLoop();
		loop.fitnessFunction = new TestingFitnessFunction();

		int result = loop.returnHighestAverage();

		assertEquals(1, result);
	}

	@Test
	public void testReturnAverage(){
		EvolutionLoop loop = createTestLoop();
		loop.fitnessFunction = new TestingFitnessFunction();

		int result = loop.returnAverage();

		assertEquals(1, result);
	}

	@Test
	public void testSelection(){
		EvolutionLoop loop = createTestLoop();
		TestingSelectionStrategy dummySelect = new TestingSelectionStrategy(new TestingFitnessFunction());
		loop.selectionStrategy = dummySelect;

		loop.selection();

		assertEquals(true, dummySelect.wasCalled);
	}	
	
	@Test
	public void testGeneSize() {
	    EvolutionLoop EL = new EvolutionLoop(10, 9);
	    assertEquals(9, EL.returnGeneSize()); 
	}
	
	@Test
	public void testLoopElitismWithNoChange() {
	    EvolutionLoop EL = new EvolutionLoop(10, 9);
	    EL.createPop();
	    Chromosome fittest = EL.returnFittest();
	    EL.elitism(0, 10);
	    Chromosome newFittest = EL.returnFittest();
	    assertEquals(fittest.geneticCode(), newFittest.geneticCode()); 
	}
	
	@Test
	public void testLoopElitismWithChange() {
	    EvolutionLoop EL = new EvolutionLoop(10, 9);
	    EL.createPop();
	    Chromosome fittest = EL.returnFittest();
	    EL.elitism(0, 5);
	    Chromosome newFittest = EL.returnFittest();
	    assertFalse(fittest.geneticCode().equals(newFittest.geneticCode())); 
	}

}
