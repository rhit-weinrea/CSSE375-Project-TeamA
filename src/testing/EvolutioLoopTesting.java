package testing;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

import mainApp.EvolutionLoop;
import mainApp.EvolutionLoop.SelectionType;

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
}
