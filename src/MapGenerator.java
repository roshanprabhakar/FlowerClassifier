import java.util.ArrayList;

public class MapGenerator {

    private Perceptron perceptron;
    private double[][] map;
    private ArrayList<FlowerData> flowerData;

    //error map generator for 2D feature vector
    public MapGenerator(double x1, double x2, double y1, double y2, double increment, Perceptron p, ArrayList<FlowerData> data) {

        perceptron = p;
        flowerData = data;
        map = new double[(int) ((y2 - y1) / increment)][(int) ((x2 - x1) / increment)];

        for (double r = x1; r < x2; r+= increment) {
            for (double c = y1; c < y2; c += increment) {
                map[(int)((r - x1)/increment)][(int)((c - y1)/increment)] = Math.abs(perceptron.train(flowerData));
            }
        }
    }

    public void printMap() {
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length; c++) {
                System.out.print(map[r][c] + " ");
            }
            System.out.println();
        }
    }
}
