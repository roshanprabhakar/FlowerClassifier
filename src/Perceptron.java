import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Perceptron {

    private String identity; //will classify for identity

    private double learningRate = 0.05f;
    private double threshold = 0;
    private int maxEpochs = 1000;

    private double[] weights;
    private String[] featureVector;

    private int power;

    private HashMap<String, String> keys;
    private String[] variables = {"x","y","z","p"};

    //Perceptron for quadratic separation
    //returns boolean value for whether or not a certain flower, eventually link to a bunch of perceptrons
    public Perceptron(String identity, String[] featureVector, int power) {
        this.identity = identity;
        this.featureVector = featureVector;
        this.power = power;

        weights = new double[featureVector.length * power];

        keys = new HashMap<>();
        for (int i = 0; i < featureVector.length; i++) {
            keys.put(featureVector[i], variables[i]);
        }
    }

    public void train(ArrayList<FlowerData> trainingData) {

        for (int i = 0; i < maxEpochs; i++) {
            for (FlowerData data : trainingData) {
                train(data);
            }
            Collections.shuffle(trainingData);
        }

        System.out.println("generated linear separation model: ");
        System.out.println(getSeparationCurve());
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

////        //adjust weights
//        for (int i = 0; i < 2; i++) { //the linear weights
//            weights[i] += error * learningRate * Math.pow(input.getSpecifiedVector(featureVector)[i - 0], 1);
//        }
//        for (int i = 2; i < 4; i++) { //the quadratic weights
//            weights[i] += error * learningRate * Math.pow(input.getSpecifiedVector(featureVector)[i - 2], 2);
//        }
//        for (int i = 4; i < weights.length; i++) { //the cubic weights
//            weights[i] += error * learningRate * Math.pow(input.getSpecifiedVector(featureVector)[i - 4], 3);
//        }

        //adjusting weights generalized
        /**
         * structure of weights = [x,y,x2,y2,x3,y3]
         * structure of input = [x,y]
         */

        int pow;
        for (int i = 0; i < input.getSpecifiedVector(featureVector).length; i++) {
            pow = 0;
            for (int j = i; j < weights.length; j += input.getSpecifiedVector(featureVector).length) {
                pow++;
                weights[j] += error * learningRate * Math.pow(input.getSpecifiedVector(featureVector)[i], pow);
            }
        }

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

    //creates an equation that can be fed into any adequate graphing software
    public String getSeparationCurve() {
        StringBuilder equation = new StringBuilder();
        int weight = 0;
        for (int pow = 1; pow <= power; pow++) {
            for (int i = 0; i < featureVector.length; i++) {
                equation.append(weights[weight] + keys.get(featureVector[i]) + "^" + pow);
                weight++;
                if (weight != weights.length) equation.append(" + ");
            }
        }
        equation.append(" = 0");

        //format for Desmos

        String[] equationArray = equation.toString().split("");

        StringBuilder formatted = new StringBuilder();
        for (int i = 0; i < equationArray.length; i++) {
            if (equationArray[i].equals("E")) {
                formatted.append("*10^");
            } else {
                formatted.append(equationArray[i]);
            }
        }

        return formatted.toString();
    }
}
