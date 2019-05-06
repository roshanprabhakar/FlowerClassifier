import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static String[] attributes = new String[]{"sepal_length", "sepal_width"};
    private static String parsingTarget = "Iris-setosa";

    //end weights: 0.2550000037997961, 0.1750000026077032
    public static void main(String[] args) {

        DataLoader loader = new DataLoader("data.csv");
        loader.loadData();

        ArrayList<FlowerData> trainingData = loader.getData();
        Perceptron perceptron = new Perceptron(parsingTarget, 2);

        int line = 1;
        for (FlowerData data : trainingData) {
//            System.out.println("line: " + line);
            perceptron.train(data);
            line++;
        }

//        perceptron.train(trainingData.get(0).getSpecifiedVector(attributes));

        FlowerData virginicaTest = new FlowerData("6.7,3.3,5.7,2.1,Iris-virginica"); //is virginica
        FlowerData setosaTest = new FlowerData("4.8,3.4,1.9,0.2,Iris-setosa"); //is setosa

        System.out.println(perceptron.guess(virginicaTest));
    }
}
