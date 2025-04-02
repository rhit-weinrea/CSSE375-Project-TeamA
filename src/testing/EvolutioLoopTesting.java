package testing;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

import mainApp.EvolutionLoop;

public class EvolutioLoopTesting {
	
	@Test
	public void ConstructionTest() {
		EvolutionLoop EL = new EvolutionLoop(10, 9);
		EL.createPop();
		EL.rankSelection();
		EL.returnFittest();
		assertTrue(true);
	}
}
