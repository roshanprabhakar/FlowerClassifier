import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static String[] attributes = new String[]{"sepal_length", "sepal_width"};
    private static String parsingTarget = "Iris-setosa";

    public static void main(String[] args) {

        DataLoader loader = new DataLoader("data.csv");
        loader.loadData();

        ArrayList<FlowerData> trainingData = loader.getData();
        Perceptron perceptron = new Perceptron(parsingTarget, 2);


        for (FlowerData data : trainingData) {
            perceptron.train(data);
        }

//        perceptron.train(trainingData.get(0).getSpecifiedVector(attributes));

        double[] testData = new double[]{5.4, 3.9}; //is setosa
        double[] testData2 = new double[]{6.1, 2.8}; //is versicolor

        System.out.println(perceptron.guess(testData2));
    }
}
