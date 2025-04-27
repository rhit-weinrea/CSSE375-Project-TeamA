package mainApp;

import java.util.HashMap;

public class PointsSet {
    private HashMap<Integer, Integer> pointsHigh = new HashMap<>();
    private HashMap<Integer, Integer> pointsAve = new HashMap<>();
    private HashMap<Integer, Integer> pointsLow = new HashMap<>();

    public HashMap<Integer, Integer> getPointsHigh() {
        return pointsHigh;
    }

    public void setPointsHigh(HashMap<Integer, Integer> pointsHigh) {
        this.pointsHigh = pointsHigh;
    }

    public HashMap<Integer, Integer> getPointsAve() {
        return pointsAve;
    }

    public void setPointsAve(HashMap<Integer, Integer> pointsAve) {
        this.pointsAve = pointsAve;
    }

    public HashMap<Integer, Integer> getPointsLow() {
        return pointsLow;
    }

    public void setPointsLow(HashMap<Integer, Integer> pointsLow) {
        this.pointsLow = pointsLow;
    }
}
