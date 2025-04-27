package mainApp;



import mainApp.EvolutionLoop.SelectionType;
import mainApp.EvolutionLoop.FitnessType;

public class EvolutionInputs {
    private boolean crossoverOption;
    private int mutate;
    private int numElites;
    private SelectionType selectionStrategy;
    private FitnessType fitnessFunction;

    public EvolutionInputs() {
        }

	public FitnessType getFitnessFunction() {
		return fitnessFunction;
	}

	public SelectionType getSelectionStrategy() {
		return selectionStrategy;
	}

	public int getNumElites() {
		return numElites;
	}

	public boolean isCrossoverOption() {
		return crossoverOption;
	}

	public int getMutate() {
		return mutate;
	}

	public void setCrossoverOption(boolean crossoverOption) {
		this.crossoverOption = crossoverOption;
	}

	public void setMutate(int mutate) {
		this.mutate = mutate;
	}

	public void setNumElites(int numElites) {
		this.numElites = numElites;
	}

	public void setSelectionStrategy(SelectionType selectionStrategy) {
		this.selectionStrategy = selectionStrategy;
	}

	public void setFitnessFunction(FitnessType fitnessFunction) {
		this.fitnessFunction = fitnessFunction;
	}
}
