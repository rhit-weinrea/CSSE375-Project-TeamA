package mainApp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JComponent;

import mainApp.EvolutionLoop.SelectionType;

@SuppressWarnings("serial")
public class EvolutionComponent extends JComponent {

	public static final int LINE_WIDTH = 2;
	private static final int FRAME_WIDTH = 1800;
	public static final int STATS_HEIGHT = 200;
	public static final int SIDE_OFFSET = 100;
	private EvolutionLoop evoLoop;
	public int genSize;
	public int i;
	public Chromosome fittestChrom;

	// Lists for three values to be graphed
	public ArrayList<Integer> highFit;
	public ArrayList<Integer> averageFit;
	public ArrayList<Integer> lowFit;
	public ArrayList<Chromosome> fittest;

//	public EvolutionComponent1(EvolutionLoop evoLoop, int numPop) {
//		this.setPreferredSize(new Dimension(EvolutionViewer.FRAME_WIDTH, STATS_HEIGHT));
//		//this.evoLoop = new EvolutionLoop(numPop);
//		this.lowFit = new ArrayList<Integer>();
//		this.averageFit = new ArrayList<Integer>();
//		this.highFit = new ArrayList<Integer>();
//		this.updated = new HashMap<Integer, Integer>();
//		//this.image = ImageIO.read(new File("darwin.jpg"));
//	}

	public EvolutionComponent(EvolutionLoop evoLoop2, int populationVal) {
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(FRAME_WIDTH, STATS_HEIGHT));
		// this.evoLoop = new EvolutionLoop(numPop);
		this.lowFit = new ArrayList<Integer>();
		this.averageFit = new ArrayList<Integer>();
		this.highFit = new ArrayList<Integer>();
		this.fittest = new ArrayList<Chromosome>();
		this.i = 0;
	}

	public void setUpLoop(int numPop, int genomeSize) {
		this.evoLoop = new EvolutionLoop(numPop, genomeSize);

	}

	// Adds values from evolution loop
	public void startUp(int numPop, int genomeSize) {
		this.evoLoop = new EvolutionLoop(numPop, genomeSize);
		evoLoop.createPop();

	}
	
	public EvolutionLoop getLoop() 
	{
		return this.evoLoop;
	}

	public void run(boolean crossoverOption, int mutate, int numElites, SelectionType selectionStrategy) 
	{
		evoLoop.changeSelectionStrategy(selectionStrategy);
		evoLoop.selection();
		
		if(numElites == 0) 
		{
			runNoElitism(crossoverOption, mutate);
		}
		else 
		{
			runElitism(crossoverOption, mutate, numElites);
		}
		this.highFit.add(evoLoop.returnHighestAverage());
		this.averageFit.add(evoLoop.returnAverage());
		this.lowFit.add(evoLoop.returnLowestAverage());
		this.fittest.add(evoLoop.returnFittest());
	}
	
	public void runNoElitism(boolean crossoverOption, int mutate) 
	{
		if (crossoverOption) 
		{
			evoLoop.flipMutation(mutate);
		}
		else
		{
			evoLoop.crossoverMutation(50);
		}
	}
	
	public void runElitism(boolean crossoverOption, int mutate, int numElites) 
	{
		if (crossoverOption) 
		{
			evoLoop.crossoverMutation(50);
		}
		
		evoLoop.elitism(mutate, numElites);
	}

	/**
	 * pop sort pop give evoloop truncate mutates and creates new stuff return that
	 * give new thing to evoloop
	 */

	@Override
	protected void paintComponent(Graphics g) {

		Font font = new Font(null, Font.PLAIN, 10);
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(font);
		int offsetX = 100;
		int offsetY = this.getHeight() - 100;
		int tickSize = 3;
		g2.setColor(Color.LIGHT_GRAY);
		g2.drawRect(0, 0, this.getWidth(), this.getHeight());
		int textOffsetX = 15;
		int scale = 2;
		int textOffsetY = 5;
		HashMap<Integer, Integer> pointsHigh = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> pointsAve = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> pointsLow = new HashMap<Integer, Integer>();

		//// Draws X-axis and tics
		g2.setColor(Color.BLACK);
		g2.drawLine(offsetX, offsetY, this.getWidth() - offsetX, offsetY);
		g2.drawLine(offsetX, offsetY, offsetX, this.getHeight() - offsetY);
		for (int i = 20 + offsetX; i < this.getWidth() - offsetX; i += 20) {
			g2.drawLine(i, offsetY + tickSize, i, offsetY - tickSize);
			if ((i - offsetX) % 40 == 0) {
				g2.drawString(String.valueOf((i - offsetX) / scale), i - 10, offsetY + textOffsetX);
			}

		}

		// Draws Y-axis and tics
		for (int i = offsetY - 20; i > offsetX; i -= 20) {
			g2.drawLine(offsetX + tickSize, i, offsetX - tickSize, i);
		}
		for (int i = 40; i < 360; i += 40) {
			g2.drawString(String.valueOf(i), offsetX - textOffsetX * 2, offsetY - i + textOffsetY);
		}

		// Adds text to graph
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.rotate(Math.toRadians(-90), 0, 0);
		Font font2 = new Font(null, Font.PLAIN, 20);
		Font rotatedFont = font2.deriveFont(affineTransform);
		g2.setFont(rotatedFont);
		g2.drawString("Fitness", offsetX - textOffsetX * 3, this.getHeight() / 2);
		g2.setFont(font2);
		g2.drawString("Number of Generations", this.getWidth() / 2 - offsetX, offsetY + textOffsetY * 9);

		if (highFit.size() > 0 && i < highFit.size()) {

			// Draws horizontal line at max fitness
			Font smallFont = new Font(null, Font.PLAIN, 10);
			g2.setFont(smallFont);
			g2.drawString("Max Fitness", offsetX + 20, offsetY - evoLoop.returnGeneSize());
			g2.drawLine(offsetX, offsetY - evoLoop.returnGeneSize(), this.getWidth() - offsetX,
					offsetY - evoLoop.returnGeneSize());
			// Graphs fitness
			int n = 0;
			for (int j = 0; j < i; j++) {

				BasicStroke brush = new BasicStroke(3);
				g2.setStroke(brush);
				g2.setColor(Color.BLACK);
				g2.drawLine(n + offsetX, offsetY - averageFit.get(j), n + 5 + offsetX, offsetY - averageFit.get(j + 1));
				g2.setColor(Color.GREEN);
				int low = lowFit.get(j);
				g2.drawLine(n + offsetX, offsetY - low, n + 5 + offsetX, offsetY - lowFit.get(j + 1));
				g2.setColor(Color.MAGENTA);
				g2.drawLine(n + offsetX, offsetY - highFit.get(j), n + 5 + offsetX, offsetY - highFit.get(j + 1));

				pointsAve.put(j + offsetX, offsetY - averageFit.get(j));
				pointsHigh.put(j + offsetX, offsetY - highFit.get(j));
				pointsLow.put(j + offsetX, offsetY - low);
				n += scale;

			}
			
			i++;
		}

	}

}