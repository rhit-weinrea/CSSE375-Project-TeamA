//package mainApp;
//
//import java.awt.*;
//import java.awt.geom.AffineTransform;
//import javax.swing.*;
//
//@SuppressWarnings("serial")
//public class ComponentPainter extends JComponent {
//
//    private static final int FRAME_WIDTH = 1800;
//    public static final int STATS_HEIGHT = 200;
//
//    private static final int AXIS_OFFSET_X = 100;
//    private static final int AXIS_OFFSET_Y_FROM_BOTTOM = 100;
//    private static final int TICK_SIZE = 3;
//    private static final int SCALE = 2;
//    private static final int X_LABEL_INTERVAL = 40;
//    private static final int Y_LABEL_INTERVAL = 40;
// 
//
//    private final EvolutionComponent evoComp;
//
//    public ComponentPainter(EvolutionComponent evoComp) {
//        this.evoComp = evoComp;
//        setPreferredSize(new Dimension(FRAME_WIDTH, STATS_HEIGHT));
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        if (evoComp.getEvoLoop() == null) return;
//
//        Graphics2D g2 = (Graphics2D) g;
//        g2.setFont(new Font(null, Font.PLAIN, 10));
//
//        int width = getWidth();
//        int height = getHeight();
//        int offsetY = height - AXIS_OFFSET_Y_FROM_BOTTOM;
//
//        drawAxes(g2, width, height, offsetY);
//        drawLabels(g2, width, height, offsetY);
//        drawFitnessLines(g2, offsetY);
//    }
//
//    private void drawAxes(Graphics2D g2, int width, int height, int offsetY) {
//        g2.setColor(Color.LIGHT_GRAY);
//        g2.drawRect(0, 0, width, height);
//
//        g2.setColor(Color.BLACK);
//        g2.drawLine(AXIS_OFFSET_X, offsetY, width - AXIS_OFFSET_X, offsetY); // X-axis
//        g2.drawLine(AXIS_OFFSET_X, offsetY, AXIS_OFFSET_X, height - offsetY); // Y-axis
//
//        for (int x = AXIS_OFFSET_X + 20; x < width - AXIS_OFFSET_X; x += 20) {
//            g2.drawLine(x, offsetY + TICK_SIZE, x, offsetY - TICK_SIZE);
//            if ((x - AXIS_OFFSET_X) % X_LABEL_INTERVAL == 0) {
//                g2.drawString(String.valueOf((x - AXIS_OFFSET_X) / SCALE), x - 10, offsetY + 15);
//            }
//        }
//
//        for (int y = offsetY - 20; y > AXIS_OFFSET_X; y -= 20) {
//            g2.drawLine(AXIS_OFFSET_X + TICK_SIZE, y, AXIS_OFFSET_X - TICK_SIZE, y);
//        }
//
//        for (int y = 40; y < 360; y += Y_LABEL_INTERVAL) {
//            g2.drawString(String.valueOf(y), AXIS_OFFSET_X - 30, offsetY - y + 5);
//        }
//    }
//
//    private void drawLabels(Graphics2D g2, int width, int height, int offsetY) {
//        Font originalFont = g2.getFont();
//        Font titleFont = new Font(null, Font.PLAIN, 20);
//
//        AffineTransform rotate = new AffineTransform();
//        rotate.rotate(Math.toRadians(-90), 0, 0);
//        Font verticalFont = titleFont.deriveFont(rotate);
//
//        g2.setFont(verticalFont);
//        g2.drawString("Fitness", AXIS_OFFSET_X - 45, height / 2);
//
//        g2.setFont(titleFont);
//        g2.drawString("Number of Generations", width / 2 - AXIS_OFFSET_X, offsetY + 45);
//
//        g2.setFont(originalFont);
//    }
//
//    private void drawFitnessLines(Graphics2D g2, int offsetY) {
//        if (evoComp.highFit.isEmpty()) return;
//
//        EvolutionLoop evoLoop = evoComp.getEvoLoop();
//        int maxFitnessY = offsetY - evoLoop.returnGeneSize();
//
//        g2.setFont(new Font(null, Font.PLAIN, 10));
//        g2.drawString("Max Fitness", AXIS_OFFSET_X + 20, maxFitnessY);
//        g2.drawLine(AXIS_OFFSET_X, maxFitnessY, getWidth() - AXIS_OFFSET_X, maxFitnessY);
//
//        BasicStroke stroke = new BasicStroke(3);
//        g2.setStroke(stroke);
//
//        for (int i = 0; i < evoComp.highFit.size() - 1; i++) {
//            int x1 = i * SCALE + AXIS_OFFSET_X;
//            int x2 = (i + 1) * SCALE + AXIS_OFFSET_X;
//
//            int high1 = offsetY - evoComp.highFit.get(i);
//            int high2 = offsetY - evoComp.highFit.get(i + 1);
//            int low1 = offsetY - evoComp.lowFit.get(i);
//            int low2 = offsetY - evoComp.lowFit.get(i + 1);
//            int avg1 = offsetY - evoComp.averageFit.get(i);
//            int avg2 = offsetY - evoComp.averageFit.get(i + 1);
//
//            g2.setColor(Color.MAGENTA);
//            g2.drawLine(x1, high1, x2, high2);
//
//            g2.setColor(Color.GREEN);
//            g2.drawLine(x1, low1, x2, low2);
//
//            g2.setColor(Color.BLACK);
//            g2.drawLine(x1, avg1, x2, avg2);
//        }
//    }
//}
