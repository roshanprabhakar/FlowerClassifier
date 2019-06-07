import java.util.ArrayList;

public class MapGenerator {

    private Perceptron perceptron;
    private MapPoint[][] map;
    private ArrayList<FlowerData> flowerData;
    private double increment;

    //error map generator for 2D feature vector
    public MapGenerator(double x1, double x2, double y1, double y2, double increment, Perceptron p, ArrayList<FlowerData> data) {

        this.increment = increment;
        perceptron = p;
        flowerData = data;
        map = new MapPoint[(int) ((y2 - y1) / increment)][(int) ((x2 - x1) / increment)];

        for (double r = x1; r < x2; r+= increment) {
            for (double c = y1; c < y2; c += increment) {
                map[(int)((r - x1)/increment)][(int)((c - y1)/increment)] = new MapPoint(r, c, increment, Math.abs(perceptron.train(flowerData).getError()));
            }
        }

        calibrateMap();
    }

    //assumes map is not of size 0
    private void calibrateMap() {

        //determine cutoff
        int min = map[0][0].getValue();
        for (MapPoint[] row : map) {
            for (MapPoint point : row) {
                if (point.getValue() < min) min = point.getValue();
            }
        }

        //apply cutoff
        for (MapPoint[] row : map) {
            for (MapPoint point : row) {
                point.setValue(point.getValue() - min);
            }
        }
    }

    //displays the error map
    public void printMap() {
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length; c++) {
                System.out.print(map[r][c] + " ");
            }
            System.out.println();
        }
    }

    //points to feed into Geogebra
    public void printPoints() {
        for (MapPoint[] row : map) {
            for (MapPoint point : row) {
                System.out.println(point + ", ");
            }
        }
    }

    public double getIncrement() {
        return this.increment;
    }
}
