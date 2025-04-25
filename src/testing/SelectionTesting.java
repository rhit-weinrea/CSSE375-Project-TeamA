package testing;

import org.junit.Test;
import static org.junit.Assert.*;
import mainApp.EvolutionLoop;
import mainApp.EvolutionLoop.FitnessType;
import mainApp.EvolutionLoop.SelectionType;
import mainApp.EvolutionComponent;

public class SelectionTesting {
	
	private class SelectionData
	{
		public int preFitness;
		public int postFitness;
		
		public SelectionData(int preFitness, int postFitness) 
		{
			this.preFitness = preFitness;
			this.postFitness = postFitness;
		}
	}
	
	@Test
	public void ConstructionTest() 
	{
		EvolutionLoop testLoop = new EvolutionLoop(10, 9);
		EvolutionComponent testComponent = new EvolutionComponent(testLoop, 0);
		testComponent.startUp(100, 100);
		
		assertTrue(true);
	}
	
	public SelectionData setupHelper(boolean crossover, int mutate, int numElites, SelectionType type, FitnessType fType) 
	{
		EvolutionLoop testLoop = new EvolutionLoop(10, 9);
		EvolutionComponent testComponent = new EvolutionComponent(testLoop, 0);
		testComponent.startUp(100, 100);
		int preFitness = testComponent.getLoop().returnAverage();
		testComponent.run(crossover, mutate, numElites, type, fType);
		int postFitness = testComponent.getLoop().returnAverage();
		return new SelectionData(preFitness, postFitness);
	}
	
	@Test
	public void TruncationTestNoCrossoverNoElitism() 
	{
		SelectionData sData = setupHelper(false, 1, 0, SelectionType.TRUNCATION, FitnessType.ALLONES);
		//If the average has increased, then the selection type is working
		assertTrue(sData.postFitness > sData.preFitness);
	}
	
	@Test
	public void TruncationTestNoCrossoverElitism() 
	{
		SelectionData sData = setupHelper(false, 1, 1, SelectionType.TRUNCATION, FitnessType.ALLONES);
		assertTrue(sData.postFitness > sData.preFitness);
	}
	
	@Test
	public void TruncationTestCrossoverNoElitism() 
	{
		SelectionData sData = setupHelper(true, 1, 0, SelectionType.TRUNCATION, FitnessType.ALLONES);
		assertTrue(sData.postFitness > sData.preFitness);
	}
	
	@Test
	public void TruncationTestCrossoverElitism() 
	{
		SelectionData sData = setupHelper(true, 1, 1, SelectionType.TRUNCATION, FitnessType.ALLONES);
		assertTrue(sData.postFitness > sData.preFitness);
	}
	
	@Test
	public void RankTestNoCrossoverNoElitism() 
	{
		SelectionData sData = setupHelper(false, 1, 0, SelectionType.RANK, FitnessType.ALLONES);
		assertTrue(sData.postFitness > sData.preFitness);
	}
	
	@Test
	public void RankTestNoCrossoverElitism() 
	{
		SelectionData sData = setupHelper(false, 1, 1, SelectionType.RANK, FitnessType.ALLONES);
		assertTrue(sData.postFitness > sData.preFitness);
	}
	
	@Test
	public void RankTestCrossoverNoElitism() 
	{
		SelectionData sData = setupHelper(true, 1, 0, SelectionType.RANK, FitnessType.ALLONES);
		assertTrue(sData.postFitness > sData.preFitness);
	}
	
	@Test
	public void RankTestCrossoverElitism() 
	{
		SelectionData sData = setupHelper(true, 1, 1, SelectionType.RANK, FitnessType.ALLONES);
		assertTrue(sData.postFitness > sData.preFitness);
	}
	
	@Test
	public void RouletteTestNoCrossoverNoElitism() 
	{
		SelectionData sData = setupHelper(false, 1, 0, SelectionType.ROULETTE, FitnessType.ALLONES);
		//Since Roulette is volatile, just make sure these don't make the fitness notably worse for the time being
		assertTrue(sData.postFitness > sData.preFitness - 10);
	}
	
	@Test
	public void RouletteTestNoCrossoverElitism() 
	{
		SelectionData sData = setupHelper(false, 1, 1, SelectionType.ROULETTE, FitnessType.ALLONES);
		assertTrue(sData.postFitness > sData.preFitness - 10);
	}
	
	@Test
	public void RouletteTestCrossoverNoElitism() 
	{
		SelectionData sData = setupHelper(true, 1, 0, SelectionType.ROULETTE, FitnessType.ALLONES);
		assertTrue(sData.postFitness > sData.preFitness - 10);
	}
	
	@Test
	public void RouletteTestCrossoverElitism() 
	{
		SelectionData sData = setupHelper(true, 1, 1, SelectionType.ROULETTE, FitnessType.ALLONES);
		assertTrue(sData.postFitness > sData.preFitness - 10);
	}
	
	@Test
	public void TournamentTestNoCrossoverNoElitism() 
	{
		SelectionData sData = setupHelper(false, 1, 0, SelectionType.TOURNAMENT, FitnessType.ALLONES);
		assertTrue(sData.postFitness > sData.preFitness);
	}
	
	@Test
	public void TournamentTestNoCrossoverElitism() 
	{
		SelectionData sData = setupHelper(false, 1, 1, SelectionType.TOURNAMENT, FitnessType.ALLONES);
		assertTrue(sData.postFitness > sData.preFitness);
	}
	
	@Test
	public void TournamentTestCrossoverNoElitism() 
	{
		SelectionData sData = setupHelper(true, 1, 0, SelectionType.TOURNAMENT, FitnessType.ALLONES);
		assertTrue(sData.postFitness > sData.preFitness);
	}
	
	@Test
	public void TournamentTestCrossoverElitism() 
	{
		SelectionData sData = setupHelper(true, 1, 1, SelectionType.TOURNAMENT, FitnessType.ALLONES);
		assertTrue(sData.postFitness > sData.preFitness);
	}
	
}
