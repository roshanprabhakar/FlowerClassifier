import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Perceptron {

    private String identity; //will classify for identity

    private double learningRate = 0.05f;
    private double threshold = 0;
    private int maxEpochs = 1000;

    private double[] weights;
    private String[] featureVector;

    private int power;

    //Perceptron for quadratic separation
    //returns boolean value for whether or not a certain flower, eventually link to a bunch of perceptrons
    public Perceptron(String identity, String[] featureVector, int power) {
        this.identity = identity;
        this.featureVector = featureVector;
        this.power = power;
        weights = new double[featureVector.length * power]; //2 for each feature: 1 for normal 1 for squared
    }

    public void train(ArrayList<FlowerData> trainingData) {

        for (int i = 0; i < maxEpochs; i++) {
            for (FlowerData data : trainingData) {
                train(data);
            }
            Collections.shuffle(trainingData);
        }

        System.out.println("generated linear separation model: ");
        System.out.println(weights[0] + "x + " + weights[1] + "y + " + weights[2] + "x^2 + " + weights[3] + "y^2 + " + weights[4] + "x^3 + " + weights[5] + "y^3 + " + threshold + " = 0");
        System.out.println("-------------------");
        System.out.println();
    }

    private void train(FlowerData input) {

        //change the weights;
        //change the threshold;

        //check if is correct, if so exit call
        if (guessIsCorrect(guess(input), input)) {
            return;
        }

        int error = getCorrectGuess(input.getIdentity()) - guess(input);

//        //adjust weights
        for (int i = 0; i < 2; i++) { //the linear weights
            weights[i] += error * learningRate * Math.pow(input.getSpecifiedVector(featureVector)[i - 0], 1);
        }
        for (int i = 2; i < 4; i++) { //the quadratic weights
            weights[i] += error * learningRate * Math.pow(input.getSpecifiedVector(featureVector)[i - 2], 2);
        }
        for (int i = 4; i < weights.length; i++) { //the cubic weights
            weights[i] += error * learningRate * Math.pow(input.getSpecifiedVector(featureVector)[i - 4], 3);
        }


        //adjust generalized weights (needs to be generalized)
//        for (int i = 0; i < power; i++) {
//            int counter = 0;
//            int pow = 1;
//            for (int j = 0; j < weights.length; i++) {
//                weights[i] += error * learningRate * Math.pow(input.getSpecifiedVector(
//                        featureVector)[i - (featureVector.length * pow - featureVector.length)], pow);
//                counter++;
//                if (counter == featureVector.length) {
//                    pow++;
//                }
//            }
//        }

    }

    //guess what feature vector could describe given current weights and threshold
    //this method is the classifying method
    public int guess(FlowerData input) {

        double sum = 0;

//        for (int i = 0; i < 2; i++) {
//            sum += Math.pow(input.getSpecifiedVector(featureVector)[i], 1) * weights[i + 0]; //linear sum
//            sum += Math.pow(input.getSpecifiedVector(featureVector)[i], 2) * weights[i + 2]; //quadratic sum
//            sum += Math.pow(input.getSpecifiedVector(featureVector)[i], 3) * weights[i + 4]; //cubic sum
//        }

        for (int i = 0; i < featureVector.length; i++) {
            for (int j = 1; j <= power; j++) {
                sum += Math.pow(input.getSpecifiedVector(featureVector)[i], j) *
                        weights[i + (featureVector.length * j - featureVector.length)];
            }
        }

        return activation(sum);
    }

    private int guess(FlowerData input, double[] weights) {

        double sum = 0;

        for (int i = 0; i < input.getSpecifiedVector(featureVector).length; i++) {
            sum += input.getSpecifiedVector(featureVector)[i] * weights[i];
        }

        return activation(sum);
    }

    public int activation(double sum) {
        if (sum > threshold) return 1;
        return 0;
    }

    public int getCorrectGuess(String label) {
        if (label.equals(identity)) return 1;
        return 0;
    }

    public boolean guessIsCorrect(int guess, FlowerData data) {
        return guess == getCorrectGuess(data.getIdentity());
    }

    public Perceptron setMaxEpochs(int maxEpochs) {
        this.maxEpochs = maxEpochs;
        return this;
    }

    //create an error map to determine the smallest error size, ideal weights
    public int[][] errorMap(double x1, double x2, double y1, double y2, double increment, ArrayList<FlowerData> testData) {
        int[][] map = new int[(int)((x2 - x1) / increment)][(int)((y2 - y1) / increment)];
        for (double r = x1; r < x2; r += increment) {
            for (double c = y1; c < y2; c += increment) {
                double[] newWeights = new double[] {r, c};
                int error = 0;
                for (FlowerData data : testData) {
                    error += guess(data, newWeights);
                }
                map[(int)((r - x1)/(increment))][(int)((c - y1)/(increment))] = error;
            }
        }
        return map;
    }
}
