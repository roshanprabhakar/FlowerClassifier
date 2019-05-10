import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    public static String[] attributes = new String[]{"sepal_length", "sepal_width"};
    private static String parsingTarget = "Iris-setosa";

    public static void main(String[] args) {

        DataLoader loader = new DataLoader("data.csv");
        loader.loadData();

        ArrayList<FlowerData> trainingData = loader.getData();
        Collections.shuffle(trainingData);

        Perceptron perceptron = new Perceptron(parsingTarget, 2);

        int line = 1;
        for (int i = 0; i < 100; i++) {
            Collections.shuffle(trainingData);
            for (FlowerData data : trainingData) {
//            System.out.println("line: " + line);
                perceptron.train(data);
                line++;
            }
        }

//        perceptron.train(trainingData.get(0).getSpecifiedVector(attributes));

        FlowerData virginicaTest = new FlowerData("6.7,3.3,5.7,2.1,Iris-virginica");
        FlowerData setosaTest = new FlowerData("4.8,3.4,1.9,0.2,Iris-setosa");

        System.out.println();
        System.out.println("prediction: " + perceptron.guess(setosaTest));
    }

    private void shuffle(ArrayList<Object> list) {
        ArrayList<Integer> remainingIndexes = getOrderedList(list.size());
        int indexToSwap;
        for (int i = 0; i < list.size(); i++) {
            indexToSwap = remainingIndexes.get((int) (Math.random() * remainingIndexes.size()));
            swap(i, indexToSwap, list);
            remainingIndexes.remove(indexToSwap);
        }
    }

    private ArrayList<Integer> getOrderedList(int size) {
        ArrayList<Integer> out = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            out.add(size);
        }
        return out;
    }

    private void swap(int index1, int index2, ArrayList<Object> list) {
        Object temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }
}
