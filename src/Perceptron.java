import java.util.Arrays;

public class Perceptron {

    private String identity; //will classify for identity

    private double learningRate = 0.05f;
    private double threshhold = 0;

    private double[] weights;

    private String old = ""; //to change

    //returns boolean value for whether or not a certain flower, eventually link to a bunch of perceptrons
    public Perceptron(String identity, int vectorLength) {
        this.identity = identity;
        weights = new double[vectorLength];
    }

    //TODO currently (as shown by virginica) the change to threshhold is incorrect
    public void train(FlowerData input) {

        System.out.println("-------------------");
        System.out.println("training for input: " + Arrays.toString(input.getSpecifiedVector(Main.attributes)));
        //change the weights;
        //change the threshhold;

        //check if is correct, if so exit call
        System.out.println("checking if guess is correct...");

        int guess = guess(input) > 0.5 ? 1 : 0;

        if (guess > 0.5) {
            System.out.println("guess is correct");
            System.out.println("weights are: " + Arrays.toString(weights));
            System.out.println("Threshhold is: " + threshhold);
            System.out.println(weights[0] + "x + " + weights[1] + "y + " + threshhold + " = 0");
            return;
        }
        System.out.println("... guess is incorrect");

//        error = guess - actual, get direction of error
        System.out.println("identity of input: " + input.getIdentity());
        System.out.println("call to getCorrectGuess(above): " + getCorrectGuess(input.getIdentity()));

        System.out.println("guess: " + guess(input));

        float error = getCorrectGuess(input.getIdentity()) - guess(input);
        System.out.println("error calculation: " + getCorrectGuess(input.getIdentity()) + " - " + guess(input));

        System.out.println("error: " + error);

        //adjust weights
        System.out.println("weights start as: " + Arrays.toString(weights));
        for (int i = 0; i < weights.length; i++) {
            weights[i] += error * learningRate * input.getSpecifiedVector(Main.attributes)[i];
            System.out.println("weights: " + Arrays.toString(weights));
        }

        if (!input.getIdentity().equals(old)) {
            old = input.getIdentity();
            System.out.println(old);
        }

        //adjust threshhold (vertical translation on 2D graph)
        threshhold += error * learningRate;

        System.out.println("Threshhold: " + threshhold);
        System.out.println("generated linear seperation model: ");
        System.out.println(weights[0] + "x + " + weights[1] + "y + " + threshhold + " = 0");
        System.out.println("-------------------");
    }

    //guess what feature vector could describe given current weights and threshhold
    //this method is the classifying method
    public float guess(FlowerData input) {

        float sum = 0;

        for (int i = 0; i < input.getSpecifiedVector(Main.attributes).length; i++) {
            sum += input.getSpecifiedVector(Main.attributes)[i] * weights[i];
        }

        return activation(sum);

    }
//
//    public int activation(double sum) {
//        if (sum > threshhold) return 1;
//        return 0;
//    }
//
    public float activation(float sum) {
        return (float) (1.0 / (1 + Math.exp(-sum)));
    }

    public int getCorrectGuess(String label) {
        if (label.equals(identity)) return 1;
        return 0;
    }

    public boolean guessIsCorrect(int guess, FlowerData data) {
        return guess == getCorrectGuess(data.getIdentity());
    }

    public String getIdentity() {
        return identity;
    }
}
