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
        super.paintComponent(g);

        Font font = new Font(null, Font.PLAIN, 10);
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(font);

        // Create the graphic context
        GraphicContext gc = new GraphicContext(
            g2,
            100,                          // offsetX
            this.getHeight() - 100,        // offsetY
            2,                             // scale
            3,                             // tickSize
            15,                            // textOffsetX
            5                              // textOffsetY
        );

        // Create the points holder
        PointsSet points = new PointsSet();

        gc.getG2().setColor(Color.LIGHT_GRAY);
        gc.getG2().drawRect(0, 0, this.getWidth(), this.getHeight());

        drawAxis(gc);
        addLabels(gc);

        if (evolutionComponent != null) {
            drawFitnessLines(gc, points);
        }
    }

    private void drawFitnessLines(GraphicContext gc, PointsSet points) {
        ArrayList<Integer> highFit = evolutionComponent.highFit;
        ArrayList<Integer> averageFit = evolutionComponent.averageFit;
        ArrayList<Integer> lowFit = evolutionComponent.lowFit;
        EvolutionLoop evoLoop = evolutionComponent.getLoop();

        if (highFit.size() > 0 && i < highFit.size()) {
            drawMaxFitness(gc, evoLoop);

            int n = 0;
            for (int j = 0; j < i; j++) {
                BasicStroke brush = new BasicStroke(3);
                gc.getG2().setStroke(brush);

                // Draw average line
                gc.getG2().setColor(Color.BLACK);
                gc.getG2().drawLine(
                    n + gc.getOffsetX(), gc.getOffsetY() - averageFit.get(j),
                    n + 5 + gc.getOffsetX(), gc.getOffsetY() - averageFit.get(j + 1)
                );

                // Draw low line
                gc.getG2().setColor(Color.GREEN);
                int low = lowFit.get(j);
                gc.getG2().drawLine(
                    n + gc.getOffsetX(), gc.getOffsetY() - low,
                    n + 5 + gc.getOffsetX(), gc.getOffsetY() - lowFit.get(j + 1)
                );

                // Draw high line
                gc.getG2().setColor(Color.MAGENTA);
                gc.getG2().drawLine(
                    n + gc.getOffsetX(), gc.getOffsetY() - highFit.get(j),
                    n + 5 + gc.getOffsetX(), gc.getOffsetY() - highFit.get(j + 1)
                );

                // Save points
                points.getPointsAve().put(j + gc.getOffsetX(), gc.getOffsetY() - averageFit.get(j));
                points.getPointsHigh().put(j + gc.getOffsetX(), gc.getOffsetY() - highFit.get(j));
                points.getPointsLow().put(j + gc.getOffsetX(), gc.getOffsetY() - low);

                n += gc.getScale();
            }

            i++;
        }
    }

    private void drawMaxFitness(GraphicContext gc, EvolutionLoop evoLoop) {
        Font smallFont = new Font(null, Font.PLAIN, 10);
        gc.getG2().setFont(smallFont);
        gc.getG2().drawString(
            "Max Fitness", gc.getOffsetX() + 20,
            gc.getOffsetY() - evoLoop.returnGeneSize()
        );
        gc.getG2().drawLine(
            gc.getOffsetX(),
            gc.getOffsetY() - evoLoop.returnGeneSize(),
            this.getWidth() - gc.getOffsetX(),
            gc.getOffsetY() - evoLoop.returnGeneSize()
        );
    }

    private void addLabels(GraphicContext gc) {
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(Math.toRadians(-90), 0, 0);

        Font font2 = new Font(null, Font.PLAIN, 20);
        Font rotatedFont = font2.deriveFont(affineTransform);

        gc.getG2().setFont(rotatedFont);
        gc.getG2().drawString(
            "Fitness",
            gc.getOffsetX() - gc.getTextOffsetX() * 3,
            this.getHeight() / 2
        );

        gc.getG2().setFont(font2);
        gc.getG2().drawString(
            "Number of Generations",
            this.getWidth() / 2 - gc.getOffsetX(),
            gc.getOffsetY() + gc.getTextOffsetY() * 9
        );
    }

    private void drawAxis(GraphicContext gc) {
        gc.getG2().setColor(Color.BLACK);
        gc.getG2().drawLine(
            gc.getOffsetX(), gc.getOffsetY(),
            this.getWidth() - gc.getOffsetX(), gc.getOffsetY()
        );
        gc.getG2().drawLine(
            gc.getOffsetX(), gc.getOffsetY(),
            gc.getOffsetX(), this.getHeight() - gc.getOffsetY()
        );

        for (int i = 20 + gc.getOffsetX(); i < this.getWidth() - gc.getOffsetX(); i += 20) {
            gc.getG2().drawLine(i, gc.getOffsetY() + gc.getTickSize(), i, gc.getOffsetY() - gc.getTickSize());
            if ((i - gc.getOffsetX()) % 40 == 0) {
                gc.getG2().drawString(
                    String.valueOf((i - gc.getOffsetX()) / gc.getScale()),
                    i - 10,
                    gc.getOffsetY() + gc.getTextOffsetX()
                );
            }
        }

        for (int i = gc.getOffsetY() - 20; i > gc.getOffsetX(); i -= 20) {
            gc.getG2().drawLine(gc.getOffsetX() + gc.getTickSize(), i, gc.getOffsetX() - gc.getTickSize(), i);
        }

        for (int i = 40; i < 360; i += 40) {
            gc.getG2().drawString(
                String.valueOf(i),
                gc.getOffsetX() - gc.getTextOffsetX() * 2,
                gc.getOffsetY() - i + gc.getTextOffsetY()
            );
        }
    }
}
