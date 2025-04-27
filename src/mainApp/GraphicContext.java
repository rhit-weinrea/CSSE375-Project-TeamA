package mainApp;

import java.awt.Graphics2D;

public class GraphicContext {
    private Graphics2D g2;
    private int offsetX;
    private int offsetY;
    private int scale;
    private int tickSize;
    private int textOffsetX;
    private int textOffsetY;

    public GraphicContext(Graphics2D g2, int offsetX, int offsetY, int scale, int tickSize, int textOffsetX, int textOffsetY) {
        this.g2 = g2;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.scale = scale;
        this.tickSize = tickSize;
        this.textOffsetX = textOffsetX;
        this.textOffsetY = textOffsetY;
    }

    public Graphics2D getG2() {
        return g2;
    }

    public void setG2(Graphics2D g2) {
        this.g2 = g2;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public int getTickSize() {
        return tickSize;
    }

    public void setTickSize(int tickSize) {
        this.tickSize = tickSize;
    }

    public int getTextOffsetX() {
        return textOffsetX;
    }

    public void setTextOffsetX(int textOffsetX) {
        this.textOffsetX = textOffsetX;
    }

    public int getTextOffsetY() {
        return textOffsetY;
    }

    public void setTextOffsetY(int textOffsetY) {
        this.textOffsetY = textOffsetY;
    }
}
