package mainApp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public class ChromosomeGenerator {
    private final Chromosome translatedChromosome;

    public ChromosomeGenerator(String filename) throws FileNotFoundException {
        String filePath = "ChromosomesText/" + filename + ".txt";
        String rawGeneticCode;

        try (Scanner scanner = new Scanner(new FileReader(filePath))) {
            if (!scanner.hasNextLine()) {
                throw new IllegalArgumentException("The file is empty.");
            }
            rawGeneticCode = scanner.nextLine().trim();
        }

        this.translatedChromosome = parseRawGeneticCode(rawGeneticCode);
    }

    public Chromosome getChromosome() {
        return translatedChromosome;
    }

    private Chromosome parseRawGeneticCode(String rawGeneticCode) {
        int totalGenes = rawGeneticCode.length();

        if (!isPerfectSquare(totalGenes)) {
            throw new IllegalArgumentException("Gene sequence length must be a perfect square.");
        }

        int dimension = (int) Math.sqrt(totalGenes);
        int[][] geneticCode = new int[dimension][dimension];

        for (int i = 0; i < totalGenes; i++) {
            char geneChar = rawGeneticCode.charAt(i);
            if (geneChar != '0' && geneChar != '1') {
                throw new IllegalArgumentException("Invalid gene character: " + geneChar);
            }

            int row = i / dimension;
            int col = i % dimension;
            geneticCode[row][col] = Character.getNumericValue(geneChar);
        }

        return new Chromosome(geneticCode);
    }

    private boolean isPerfectSquare(int n) {
        int root = (int) Math.sqrt(n);
        return root * root == n;
    }
}
