public class Perceptron {

    private String identity; //will classify for identity

    private double learningRate = 0.05f;
    private double threshhold = 0;

    private double[] weights;

    //returns boolean value for whether or not a certain flower, eventually link to a bunch of perceptrons
    public Perceptron(String identity) {
        this.identity = identity;
    }

    public void train(double[] input) {

        //change the weights;
        //change the threshhold;

        //error = guess - actual, get direction of error
        int error = guess(input) - getCorrectGuess(identity);

        //adjust weights
        for (int i = 0; i < weights.length; i++) {
            weights[i] += error * learningRate * input[i];
        }

        threshhold += error * learningRate;
    }

    //guess what feature vector could describe given current weights and threshhold
    //this method is the classifying method
    public int guess(double[] trainingPoint) {

        double sum = 0;

        for (int i = 0; i < trainingPoint.length; i++) {
            sum += trainingPoint[i] * weights[i];
        }

        return activation(sum);

    }

    public int activation(double sum) {
        if (sum > threshhold) return 1;
        return 0;
    }

    public int getCorrectGuess(String label) {
        if (label.equals(this.identity)) return 1;
        return 0;
    }

}
