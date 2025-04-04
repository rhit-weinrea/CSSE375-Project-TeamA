package testing;

import org.junit.After;
import org.junit.Before;

import mainApp.Chromosome;
import mainApp.ChromosomeGenerator;

import java.io.*;
import java.nio.file.*;
import org.junit.Test;
import static org.junit.Assert.*;


public class ChromosomeTesting {
    private static final String TEST_DIR = "ChromosomesText";

    @Before
    public void setup() throws IOException {
        Files.createDirectories(Paths.get(TEST_DIR));
    }

    @After
    public void cleanup() throws IOException {
        Files.walk(Paths.get(TEST_DIR))
                .filter(Files::isRegularFile)
                .forEach(path -> path.toFile().delete());
    }

    private void createTestFile(String filename, String content) throws IOException {
        Path filePath = Paths.get(TEST_DIR, filename + ".txt");
        Files.write(filePath, content.getBytes());
    }

    @SuppressWarnings("deprecation")
	@Test
	public void testValidChromosome() throws Exception {
        createTestFile("validChromosome", "010011001");

        ChromosomeGenerator generator = new ChromosomeGenerator("validChromosome");
        Chromosome chromosome = generator.getChromosome();
        int[][] grid = chromosome.geneticCode();

        int[][] expected = {
                {0, 1, 0},
                {0, 1, 1},
                {0, 0, 1}
        };

        assertEquals(expected, grid);
    }

    @Test
    public void testNonPerfectSquareThrowsException() throws Exception {
        createTestFile("badLength", "010101010"); // length = 9, but let's make it 8 instead
        Files.write(Paths.get(TEST_DIR, "badLength.txt"), "01010101".getBytes()); // length = 8

        assertThrows(IllegalArgumentException.class, () -> {
            new ChromosomeGenerator("badLength");
        });
    }

    @Test
    public void testInvalidCharacterThrowsException() throws Exception {
        createTestFile("invalidChar", "0102A1001");

        assertThrows(IllegalArgumentException.class, () -> {
            new ChromosomeGenerator("invalidChar");
        });
    }

    @Test
    public void testEmptyFileThrowsException() throws Exception {
        createTestFile("empty", "");

        assertThrows(IllegalArgumentException.class, () -> {
            new ChromosomeGenerator("empty");
        });
    }

    @Test
    public void testOnlyZerosAndOnesAccepted() throws Exception {
        createTestFile("allOnes", "111111111");

        ChromosomeGenerator generator = new ChromosomeGenerator("allOnes");
        int[][] grid = generator.getChromosome().geneticCode();

        for (int[] row : grid) {
            for (int val : row) {
                assertEquals(1, val);
            }
        }
    }
}
