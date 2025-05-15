package testing.Mocks;

import java.util.ArrayList;

import fitnessFunctions.FitnessFunction;
import selectionStrategies.SelectionStrategy;
import mainApp.Chromosome;
import selectionStrategies.SelectionStrategy;

public class TestingSelectionStrategy extends SelectionStrategy {

    public boolean wasCalled = false;
    
    public TestingSelectionStrategy(FitnessFunction fitness) {
        super(fitness);
    }

    @Override
    public ArrayList<Chromosome> select(ArrayList<Chromosome> curPop) 
	{
        wasCalled = true;
        return new ArrayList<Chromosome>();
    }
    
}
