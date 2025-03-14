package mainApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 * Replaces the current ChromosomeViewer frame with a new one displaying the
 * mutated Chromosome.
 * 
 * @author Wynesakia Akamah & Abby Weinreb
 *
 */
public class MutateListener implements ActionListener {

	private Double mutationRate;
	private Chromosome chromosome;
	private JFrame frame;

	public MutateListener(Chromosome chromosome, JFrame frame, double mutationRate) {
		this.chromosome = chromosome;
		this.frame = frame;
		this.mutationRate = mutationRate;
	}

	public void setMutationRate(double rate) {
		this.mutationRate = rate;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.dispose();
		System.out.println("Mutation Rate: " + mutationRate);
		System.out.println();
		int numberOfMutations = (int) (mutationRate * this.chromosome.numberOfGenes() / 100);
		Chromosome mutatedOffspring = chromosome.mutatedOffspring(numberOfMutations);
		System.out.println(mutatedOffspring.asString());
		new ChromosomeViewer(mutatedOffspring, mutationRate);
	}

}
