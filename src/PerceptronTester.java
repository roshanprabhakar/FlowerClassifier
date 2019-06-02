import java.util.ArrayList;

public class PerceptronTester {

    private String testingIdentity;
    private ArrayList<FlowerData> testData;
    private Perceptron trainedPerceptron;

    private double percentAccuracy;
    private ArrayList<String> status;

    public PerceptronTester(Perceptron trainedPerceptron, ArrayList<FlowerData> testData, String testingIdentity) {
        this.testingIdentity = testingIdentity;
        this.testData = testData;
        this.trainedPerceptron = trainedPerceptron;
        status = new ArrayList<>();
        runDiagnostics();
    }

    private void runDiagnostics() {
        int correct = 0;
        StringBuilder line;
        for (FlowerData data : testData) {
            line = new StringBuilder();
            line.append("correct: " + getCorrectGuess(data) + ", ");
            line.append("guessed: " + trainedPerceptron.guess(data) + ", ");
            if (trainedPerceptron.guess(data) == getCorrectGuess(data)) {
                correct++;
                line.append("CORRECT");
            } else {
                line.append("WRONG");
            }
            status.add(line.toString());
        }
        percentAccuracy = (double) correct / testData.size();

    }

    public double getPercentAccuracy() {
        return percentAccuracy;
    }

    public ArrayList<String> getStatus() {
        return status;
    }

    private int getCorrectGuess(FlowerData data) {
        if (data.getIdentity().equals(testingIdentity)) return 1;
        else return 0;
    }
}
