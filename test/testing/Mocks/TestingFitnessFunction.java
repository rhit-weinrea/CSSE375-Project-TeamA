package testing.Mocks;

import fitnessFunctions.FitnessFunction;
import mainApp.Chromosome;

public class TestingFitnessFunction extends FitnessFunction{

    @Override
    public int fitness(Chromosome chrom) {
        return 1;
    }
    
}
