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

    @SuppressWarnings("deprecation")
	@Test
	public void testGeneticCode() throws Exception {

        int[][] expected = {
                {0, 1, 0},
                {0, 1, 1},
                {0, 0, 1}
        };

        Chromosome testChromosome = new Chromosome(new int[][]{{0, 1, 0}, {0, 1, 1}, {0, 0, 1}});

        int[][] actual = testChromosome.geneticCode();

        assertEquals(expected, actual);
    }

    @Test
    public void testGetDecimal(){
        Chromosome testChromosome = new Chromosome(new int[][]{{0, 1, 0}, {0, 1, 1}, {0, 0, 1}});

        assertEquals(306, testChromosome.getDecimal());
    }

    @Test
    public void testAsStringRaw(){
        Chromosome testChromosome = new Chromosome(new int[][]{{0, 1, 0}, {0, 1, 1}, {0, 0, 1}});

        assertEquals("010011001", testChromosome.asStringRaw());
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testSetGeneticCode(){
        
        int[][] expected = {
            {0, 1, 0},
            {0, 1, 1},
            {0, 0, 1}
        };

        Chromosome testChromosome = new Chromosome(0);
        testChromosome.setGeneticCode(expected);

        int[][] actual = testChromosome.geneticCode();
        assertEquals(expected, actual);
    }

    @Test
    public void testAsString(){
        Chromosome testChromosome = new Chromosome(new int[][]{{0, 1, 0}, {0, 1, 1}, {0, 0, 1}});

        String expected = "Genetic Code: 0 1 0 \n              0 1 1 \n              0 0 1 \n              ";

        assertEquals(expected, testChromosome.asString());
    }

    @Test
    public void testNumberOfGenes(){
        Chromosome testChromosome = new Chromosome(new int[][]{{0, 1, 0}, {0, 1, 1}, {0, 0, 1}});

        assertEquals(9, testChromosome.numberOfGenes());
    }
}   
