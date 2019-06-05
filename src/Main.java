import java.util.ArrayList;

public class Main {

    private static String parsingTarget = "Iris-versicolor";
    private static double percentTraining = 0.9;

    public static void main(String[] args) {

        Parser parser = new Parser(percentTraining, new DataLoader("data.csv").getData());

        ArrayList<FlowerData> trainingData = parser.getTrainingData();
        ArrayList<FlowerData> testData = parser.getTestingData();

        String[] featureVector = {"sepal_length", "sepal_width"};

        //Creating and testing perceptron
        Perceptron slp = new Perceptron(parsingTarget, featureVector, 10).setMaxEpochs(5000);
        slp.train(trainingData);

        //Generating Statistics by testing Perceptron
        PerceptronTester tester = new PerceptronTester(slp, testData, parsingTarget);
        System.out.println("% accuracy of model: " + tester.getPercentAccuracy());
        for (String line : tester.getStatus()) {
            System.out.println(line);
        }

        //Points for google spreadsheets (to copy paste into desmos)
        SpreadsheetsAdapter adapter = new SpreadsheetsAdapter(parser.getAllData(), featureVector);
        adapter.printData(parsingTarget);
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
