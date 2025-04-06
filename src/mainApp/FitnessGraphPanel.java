package mainApp;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashMap;

public class FitnessGraphPanel extends JComponent {
    
    private EvolutionComponent evolutionComponent;  

    private int i = 0;


    public FitnessGraphPanel(EvolutionComponent evolutionComponent) {
    	this.evolutionComponent = evolutionComponent;
        setOpaque(false); 
    }
    
    public void setComponent(EvolutionComponent component) {
    	this.evolutionComponent = component;
    }


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
		drawAxis(g2, offsetX, offsetY, tickSize, textOffsetX, scale, textOffsetY);

		// Adds text to graph
		addLabels(g2, offsetX, offsetY, textOffsetX, textOffsetY);

		if(evolutionComponent != null) {
			drawFitnessLines(g2, offsetX, offsetY, scale, pointsHigh, pointsAve, pointsLow);
		}

    }

	private void drawFitnessLines(Graphics2D g2, int offsetX, int offsetY, int scale,
		HashMap<Integer, Integer> pointsHigh, HashMap<Integer, Integer> pointsAve,
		HashMap<Integer, Integer> pointsLow) {
		ArrayList<Integer> highFit = evolutionComponent.highFit;
		ArrayList<Integer> averageFit = evolutionComponent.averageFit;
		ArrayList<Integer> lowFit = evolutionComponent.lowFit;
		EvolutionLoop evoLoop = evolutionComponent.getLoop();
		if (highFit.size() > 0 && i < highFit.size()) {

		// Draws horizontal line at max fitness
			drawMaxFitness(g2, offsetX, offsetY, evoLoop);
			// Graphs fitness
			int n = 0;
			for (int j = 0; j < i; j++) {
	
				BasicStroke brush = new BasicStroke(3);
				System.out.println("painting?");
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

	private void drawMaxFitness(Graphics2D g2, int offsetX, int offsetY, EvolutionLoop evoLoop) {
		Font smallFont = new Font(null, Font.PLAIN, 10);
		g2.setFont(smallFont);
		g2.drawString("Max Fitness", offsetX + 20, offsetY - evoLoop.returnGeneSize());
		g2.drawLine(offsetX, offsetY - evoLoop.returnGeneSize(), this.getWidth() - offsetX,
				offsetY - evoLoop.returnGeneSize());
	}

	private void addLabels(Graphics2D g2, int offsetX, int offsetY, int textOffsetX, int textOffsetY) {
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.rotate(Math.toRadians(-90), 0, 0);
		Font font2 = new Font(null, Font.PLAIN, 20);
		Font rotatedFont = font2.deriveFont(affineTransform);
		g2.setFont(rotatedFont);
		g2.drawString("Fitness", offsetX - textOffsetX * 3, this.getHeight() / 2);
		g2.setFont(font2);
		g2.drawString("Number of Generations", this.getWidth() / 2 - offsetX, offsetY + textOffsetY * 9);
	}

	private void drawAxis(Graphics2D g2, int offsetX, int offsetY, int tickSize, int textOffsetX, int scale,
			int textOffsetY) {
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
	}
    


}
