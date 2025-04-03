package testing;

import org.junit.Test;
import static org.junit.Assert.*;
import mainApp.EvolutionLoop;
import mainApp.EvolutionLoop.SelectionType;
import mainApp.EvolutionComponent;

public class SelectionTesting {

	
	@Test
	public void ConstructionTest() 
	{
		EvolutionLoop testLoop = new EvolutionLoop(10, 9);
		EvolutionComponent testComponent = new EvolutionComponent(testLoop, 0);
		testComponent.startUp(100, 100);
		
		assertTrue(true);
	}
	
	@Test
	public void TruncationTest() 
	{
		EvolutionLoop testLoop = new EvolutionLoop(10, 9);
		EvolutionComponent testComponent = new EvolutionComponent(testLoop, 0);
		
		testComponent.startUp(100, 100);
		int preFitness = testComponent.getLoop().returnAverage();
		testComponent.run(false, 1, 0, SelectionType.TRUNCATION);
		int postFitness = testComponent.getLoop().returnAverage();
		//If the average has increased, then the selection type is working
		assertTrue(postFitness > preFitness);
		
		testComponent.startUp(100, 100);
		preFitness = testComponent.getLoop().returnAverage();
		testComponent.run(true, 1, 0, SelectionType.TRUNCATION);
		postFitness = testComponent.getLoop().returnAverage();
		assertTrue(postFitness > preFitness);
		
		testComponent.startUp(100, 100);
		preFitness = testComponent.getLoop().returnAverage();
		testComponent.run(false, 1, 1, SelectionType.TRUNCATION);
		postFitness = testComponent.getLoop().returnAverage();
		assertTrue(postFitness > preFitness);
		
		testComponent.startUp(100, 100);
		preFitness = testComponent.getLoop().returnAverage();
		testComponent.run(true, 1, 1, SelectionType.TRUNCATION);
		postFitness = testComponent.getLoop().returnAverage();
		assertTrue(postFitness > preFitness);
	}
	
	@Test
	public void RankTest() 
	{
		EvolutionLoop testLoop = new EvolutionLoop(10, 9);
		EvolutionComponent testComponent = new EvolutionComponent(testLoop, 0);
		
		testComponent.startUp(100, 100);
		int preFitness = testComponent.getLoop().returnAverage();
		testComponent.run(false, 1, 0, SelectionType.RANK);
		int postFitness = testComponent.getLoop().returnAverage();
		assertTrue(postFitness > preFitness);
		
		testComponent.startUp(100, 100);
		preFitness = testComponent.getLoop().returnAverage();
		testComponent.run(true, 1, 0, SelectionType.RANK);
		postFitness = testComponent.getLoop().returnAverage();
		assertTrue(postFitness > preFitness);
		
		testComponent.startUp(100, 100);
		preFitness = testComponent.getLoop().returnAverage();
		testComponent.run(false, 1, 1, SelectionType.RANK);
		postFitness = testComponent.getLoop().returnAverage();
		assertTrue(postFitness > preFitness);
		
		testComponent.startUp(100, 100);
		preFitness = testComponent.getLoop().returnAverage();
		testComponent.run(true, 1, 1, SelectionType.RANK);
		postFitness = testComponent.getLoop().returnAverage();
		assertTrue(postFitness > preFitness);
	}
	
	//Only sometimes works, due to inherent randomness of roulette + how populations are made
	@Test
	public void RouletteTest() 
	{
		EvolutionLoop testLoop = new EvolutionLoop(10, 9);
		EvolutionComponent testComponent = new EvolutionComponent(testLoop, 0);
		
		testComponent.startUp(100, 100);
		int preFitness = testComponent.getLoop().returnAverage();
		testComponent.run(false, 1, 0, SelectionType.ROULETTE);
		int postFitness = testComponent.getLoop().returnAverage();
		//If the average has increased, then the selection type is working
		assertTrue(postFitness > preFitness);
		
		testComponent.startUp(100, 100);
		preFitness = testComponent.getLoop().returnAverage();
		testComponent.run(true, 1, 0, SelectionType.ROULETTE);
		postFitness = testComponent.getLoop().returnAverage();
		assertTrue(postFitness > preFitness);
		
		testComponent.startUp(100, 100);
		preFitness = testComponent.getLoop().returnAverage();
		testComponent.run(false, 1, 1, SelectionType.ROULETTE);
		postFitness = testComponent.getLoop().returnAverage();
		assertTrue(postFitness > preFitness);
		
		testComponent.startUp(100, 100);
		preFitness = testComponent.getLoop().returnAverage();
		testComponent.run(true, 1, 1, SelectionType.ROULETTE);
		postFitness = testComponent.getLoop().returnAverage();
		assertTrue(postFitness > preFitness);
	}
	
	@Test
	public void TournamentTest() 
	{
		EvolutionLoop testLoop = new EvolutionLoop(10, 9);
		EvolutionComponent testComponent = new EvolutionComponent(testLoop, 0);
		
		testComponent.startUp(100, 100);
		int preFitness = testComponent.getLoop().returnAverage();
		testComponent.run(false, 1, 0, SelectionType.TOURNAMENT);
		int postFitness = testComponent.getLoop().returnAverage();
		//If the average has increased, then the selection type is working
		assertTrue(postFitness > preFitness);
		
		testComponent.startUp(100, 100);
		preFitness = testComponent.getLoop().returnAverage();
		testComponent.run(true, 1, 0, SelectionType.TOURNAMENT);
		postFitness = testComponent.getLoop().returnAverage();
		assertTrue(postFitness > preFitness);
		
		testComponent.startUp(100, 100);
		preFitness = testComponent.getLoop().returnAverage();
		testComponent.run(false, 1, 1, SelectionType.TOURNAMENT);
		postFitness = testComponent.getLoop().returnAverage();
		assertTrue(postFitness > preFitness);
		
		testComponent.startUp(100, 100);
		preFitness = testComponent.getLoop().returnAverage();
		testComponent.run(true, 1, 1, SelectionType.TOURNAMENT);
		postFitness = testComponent.getLoop().returnAverage();
		assertTrue(postFitness > preFitness);
	}
	
}
