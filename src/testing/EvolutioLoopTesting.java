package testing;

import java.util.ArrayList;
import mainApp.Chromosome;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import mainApp.EvolutionLoop;
import mainApp.EvolutionLoop.SelectionType;
import testing.Mocks.TestingFitnessFunction;
import testing.Mocks.TestingSelectionStrategy;

public class EvolutioLoopTesting {
	
	@Test
	public void ConstructionTest() {
		EvolutionLoop EL = new EvolutionLoop(10, 9);
		EL.createPop();
		EL.changeSelectionStrategy(SelectionType.RANK);
		EL.selection();
		EL.returnFittest();
		assertTrue(true);
	}

	@Test
	public void testReturnLowestAverage(){
		EvolutionLoop loop = new EvolutionLoop(10, 0);
		loop.createPop();
		loop.fitnessFunction = new TestingFitnessFunction();

		int result = loop.returnLowestAverage();

		assertEquals(1, result);
	}

	@Test
	public void testReturnHighestAverage(){
		EvolutionLoop loop = new EvolutionLoop(10, 0);
		loop.createPop();
		loop.fitnessFunction = new TestingFitnessFunction();

		int result = loop.returnHighestAverage();

		assertEquals(1, result);
	}

	@Test
	public void testReturnAverage(){
		EvolutionLoop loop = new EvolutionLoop(10, 0);
		loop.createPop();
		loop.fitnessFunction = new TestingFitnessFunction();

		int result = loop.returnAverage();

		assertEquals(1, result);
	}

	@Test
	public void testSelection(){
		EvolutionLoop loop = new EvolutionLoop(10, 0);
		loop.createPop();
		TestingSelectionStrategy dummySelect = new TestingSelectionStrategy(new TestingFitnessFunction());
		loop.selectionStrategy = dummySelect;

		loop.selection();

		assertEquals(true, dummySelect.wasCalled);
	}	

}
