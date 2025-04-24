package mainApp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ChromosomeDrawer {
	public static final Color PHENOTYPE_OF_1_GENE = new Color(37, 244, 137);
	public static final Color PHENOTYPE_OF_0_GENE = new Color(242, 235, 226);
	public static final int PHENOTYPE_SIDE_LENGTH_1 = 50;
	public static final int PHENOTYPE_SIDE_LENGTH_2 = 5;
	
	static public void drawLongPhenotype(Chromosome chrom, JPanel panel) {
		drawOn(chrom, PHENOTYPE_SIDE_LENGTH_1, panel);
	}
	
	static public void drawShortPhenotype(Chromosome chrom, JPanel panel) {
		drawOn(chrom, PHENOTYPE_SIDE_LENGTH_2, panel);
	}
	
	static private void drawOn(Chromosome chrom, int chomosomePhenotypeLength, JPanel panel) {
		int[][] genCode = chrom.geneticCode();
		for (int j = 0; j < genCode.length; j++) {
			for (int k = 0; k < genCode[0].length; k++) {
				Color backgroundPhenotype;
				if (genCode[j][k] == 0) {
					backgroundPhenotype = PHENOTYPE_OF_0_GENE;
				}
				else {
					backgroundPhenotype = PHENOTYPE_OF_1_GENE;
				}
				
				JButton geneButton = new JButton();
				geneButton.setPreferredSize(new Dimension(chomosomePhenotypeLength, chomosomePhenotypeLength));
				geneButton.setBackground(backgroundPhenotype);
				geneButton.addActionListener(
						new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								if (geneButton.getBackground().equals(PHENOTYPE_OF_0_GENE)) {
									geneButton.setBackground(PHENOTYPE_OF_1_GENE);
								} else {
									geneButton.setBackground(PHENOTYPE_OF_0_GENE);
								}
							}
							
						});
				panel.add(geneButton);
			}
		}

	}
}
