package testing.Mocks;

import java.util.ArrayList;

import fitnessFunctions.FitnessFunction;
import selectionStrategies.SelectionStrategy;
import mainApp.Chromosome;
import mainApp.EvolutionLoop.SelectionType;
import selectionStrategies.SelectionStrategy;

public class TestingSelectionStrategy extends SelectionStrategy {

    public boolean wasCalled = false;
    
    public TestingSelectionStrategy(FitnessFunction fitness) {
        super(fitness, SelectionType.RANK);
    }

    @Override
    public ArrayList<Chromosome> select(ArrayList<Chromosome> curPop) 
	{
        wasCalled = true;
        return new ArrayList<Chromosome>();
    }
    
}
