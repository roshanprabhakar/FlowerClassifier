import java.util.ArrayList;
import java.util.Collections;

public class Parser {

    private ArrayList<FlowerData> trainingData;
    private ArrayList<FlowerData> testingData;
    private ArrayList<FlowerData> allData;

    public Parser(double percentTraining, ArrayList<FlowerData> allData) {

        trainingData = new ArrayList<>();
        testingData = new ArrayList<>();
        this.allData = allData;

        Collections.shuffle(allData);
        for (int i = 0; i < allData.size(); i++) {
            if (i < percentTraining * allData.size()) trainingData.add(allData.get(i));
            else testingData.add(allData.get(i));
        }
    }

    public ArrayList<FlowerData> getTrainingData() {
        return trainingData;
    }

    public ArrayList<FlowerData> getTestingData() {
        return testingData;
    }

    public ArrayList<FlowerData> getAllData() {
        return allData;
    }


}
