import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    private static String parsingTarget = "Iris-setosa";
    private static double percentTraining = 0.5;

    public static void main(String[] args) {

        Parser parser = new Parser(percentTraining, new DataLoader("data.csv").getData());

        ArrayList<FlowerData> trainingData = parser.getTrainingData();
        ArrayList<FlowerData> testData = parser.getTestingData();

        String[] featureVector = new String[]{"sepal_length", "sepal_width"};

        Perceptron slp = new Perceptron(parsingTarget, featureVector).setMaxEpochs(50000);

        slp.train(trainingData);

        PerceptronTester tester = new PerceptronTester(slp, testData, parsingTarget);

        System.out.println("% accuracy of model: " + tester.getPercentAccuracy());
        for (String line : tester.getStatus()) {
            System.out.println(line);
        }

        System.out.println("Depression map: ");
        int[][] errorMap = slp.errorMap(0, 0.02, 0, 0.02, 0.0001, testData);
        for (int[] row : errorMap) {
            for (int col : row) {
                System.out.print(generateSpaces(col, 3));
            }
            System.out.println();
        }
    }

    private static String generateSpaces(Integer input, int total) {
        StringBuilder out = new StringBuilder();
        out.append(input.toString());
        for (int i = 0; i < total - input.toString().length(); i++) {
            out.append(" ");
        }
        return out.toString();
    }
}
