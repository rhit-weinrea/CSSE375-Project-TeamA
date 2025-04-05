package testing;

import org.junit.Test;
import static org.junit.Assert.*;
import mainApp.EvolutionLoop;
import mainApp.EvolutionLoop.FitnessType;
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
		testComponent.run(false, 1, 0, SelectionType.TRUNCATION, FitnessType.ALLONES);
		int postFitness = testComponent.getLoop().returnAverage();
		//If the average has increased, then the selection type is working
		assertTrue(postFitness > preFitness);
		
		testComponent.startUp(100, 100);
		preFitness = testComponent.getLoop().returnAverage();
		testComponent.run(true, 1, 0, SelectionType.TRUNCATION, FitnessType.ALLONES);
		postFitness = testComponent.getLoop().returnAverage();
		assertTrue(postFitness > preFitness);
		
		testComponent.startUp(100, 100);
		preFitness = testComponent.getLoop().returnAverage();
		testComponent.run(false, 1, 1, SelectionType.TRUNCATION, FitnessType.ALLONES);
		postFitness = testComponent.getLoop().returnAverage();
		assertTrue(postFitness > preFitness);
		
		testComponent.startUp(100, 100);
		preFitness = testComponent.getLoop().returnAverage();
		testComponent.run(true, 1, 1, SelectionType.TRUNCATION, FitnessType.ALLONES);
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
		testComponent.run(false, 1, 0, SelectionType.RANK, FitnessType.ALLONES);
		int postFitness = testComponent.getLoop().returnAverage();
		assertTrue(postFitness > preFitness);
		
		testComponent.startUp(100, 100);
		preFitness = testComponent.getLoop().returnAverage();
		testComponent.run(true, 1, 0, SelectionType.RANK, FitnessType.ALLONES);
		postFitness = testComponent.getLoop().returnAverage();
		assertTrue(postFitness > preFitness);
		
		testComponent.startUp(100, 100);
		preFitness = testComponent.getLoop().returnAverage();
		testComponent.run(false, 1, 1, SelectionType.RANK, FitnessType.ALLONES);
		postFitness = testComponent.getLoop().returnAverage();
		assertTrue(postFitness > preFitness);
		
		testComponent.startUp(100, 100);
		preFitness = testComponent.getLoop().returnAverage();
		testComponent.run(true, 1, 1, SelectionType.RANK, FitnessType.ALLONES);
		postFitness = testComponent.getLoop().returnAverage();
		assertTrue(postFitness > preFitness);
	}
	
	//Due to the volatile nature of Roulette selection, these tests just make sure the fitness hasn't drastically gone down
	@Test
	public void RouletteTest() 
	{
		EvolutionLoop testLoop = new EvolutionLoop(10, 9);
		EvolutionComponent testComponent = new EvolutionComponent(testLoop, 0);
		
		testComponent.startUp(100, 100);
		int preFitness = testComponent.getLoop().returnAverage();
		testComponent.run(false, 1, 0, SelectionType.ROULETTE, FitnessType.ALLONES);
		int postFitness = testComponent.getLoop().returnAverage();
		assertTrue(postFitness > preFitness - 10);
		
		testComponent.startUp(100, 100);
		preFitness = testComponent.getLoop().returnAverage();
		testComponent.run(true, 1, 0, SelectionType.ROULETTE, FitnessType.ALLONES);
		postFitness = testComponent.getLoop().returnAverage();
		assertTrue(postFitness > preFitness - 10);
		
		testComponent.startUp(100, 100);
		preFitness = testComponent.getLoop().returnAverage();
		testComponent.run(false, 1, 1, SelectionType.ROULETTE, FitnessType.ALLONES);
		postFitness = testComponent.getLoop().returnAverage();
		assertTrue(postFitness > preFitness - 10);
		
		testComponent.startUp(100, 100);
		preFitness = testComponent.getLoop().returnAverage();
		testComponent.run(true, 1, 1, SelectionType.ROULETTE, FitnessType.ALLONES);
		postFitness = testComponent.getLoop().returnAverage();
		assertTrue(postFitness > preFitness - 10);
	}
	
	@Test
	public void TournamentTest() 
	{
		EvolutionLoop testLoop = new EvolutionLoop(10, 9);
		EvolutionComponent testComponent = new EvolutionComponent(testLoop, 0);
		
		testComponent.startUp(100, 100);
		int preFitness = testComponent.getLoop().returnAverage();
		testComponent.run(false, 1, 0, SelectionType.TOURNAMENT, FitnessType.ALLONES);
		int postFitness = testComponent.getLoop().returnAverage();
		//If the average has increased, then the selection type is working
		assertTrue(postFitness > preFitness);
		
		testComponent.startUp(100, 100);
		preFitness = testComponent.getLoop().returnAverage();
		testComponent.run(true, 1, 0, SelectionType.TOURNAMENT, FitnessType.ALLONES);
		postFitness = testComponent.getLoop().returnAverage();
		assertTrue(postFitness > preFitness);
		
		testComponent.startUp(100, 100);
		preFitness = testComponent.getLoop().returnAverage();
		testComponent.run(false, 1, 1, SelectionType.TOURNAMENT, FitnessType.ALLONES);
		postFitness = testComponent.getLoop().returnAverage();
		assertTrue(postFitness > preFitness);
		
		testComponent.startUp(100, 100);
		preFitness = testComponent.getLoop().returnAverage();
		testComponent.run(true, 1, 1, SelectionType.TOURNAMENT, FitnessType.ALLONES);
		postFitness = testComponent.getLoop().returnAverage();
		assertTrue(postFitness > preFitness);
	}
	
}
