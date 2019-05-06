import java.util.Arrays;

public class Perceptron {

    private String identity; //will classify for identity

    private double learningRate = 0.05f;
    private double threshhold = 0;

    private double[] weights;

    //returns boolean value for whether or not a certain flower, eventually link to a bunch of perceptrons
    public Perceptron(String identity, int vectorLength) {
        this.identity = identity;
        weights = new double[vectorLength];
    }

    public void train(FlowerData input) {

        System.out.println("-------------------");
        System.out.println("training for input: " + Arrays.toString(input.getSpecifiedVector(Main.attributes)));
        //change the weights;
        //change the threshhold;

        //error = guess - actual, get direction of error
        System.out.println("identity of input: " + identity);
        System.out.println("call to getCorrectGuess(above): " + getCorrectGuess(input.getIdentity()));

        int error = guess(input) - getCorrectGuess(identity);
        System.out.println("error: " + error);

        //adjust weights
        for (int i = 0; i < weights.length; i++) {
            weights[i] += error * learningRate * input.getSpecifiedVector(Main.attributes)[i];
            System.out.println("weights: " + Arrays.toString(weights));
        }

        //adjust threshhold (vertical translation on 2D graph)
        threshhold += error * learningRate;
        System.out.println("Threshhold: " + threshhold);
        System.out.println("-------------------");
    }

    //guess what feature vector could describe given current weights and threshhold
    //this method is the classifying method
    public int guess(FlowerData input) {

        double sum = 0;

        for (int i = 0; i < input.getSpecifiedVector(Main.attributes).length; i++) {
            sum += input.getSpecifiedVector(Main.attributes)[i] * weights[i];
        }

        return activation(sum);

    }

    public int activation(double sum) {
        if (sum > threshhold) return 1;
        return 0;
    }

    public int getCorrectGuess(String label) {
        if (label.equals(identity)) return 1;
        return 0;
    }

    public String getIdentity() {return identity;}
}
