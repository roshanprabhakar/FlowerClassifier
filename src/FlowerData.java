import java.util.Arrays;
import java.util.HashMap;

/**
 * 1. sepal length in cm
 * 2. sepal width in cm
 * 3. petal length in cm
 * 4. petal width in cm
 * 5. class:
 */

public class FlowerData {

    private String identity;

    private double sepal_length;
    private double sepal_width;
    private double petal_length;
    private double petal_width;

    private HashMap<String, Double> features;
    private double[] featureVector = new double[] {sepal_length, sepal_width, petal_length, petal_width};

    public FlowerData(String identity, double sepal_length, double sepal_width, double petal_length, double petal_width) {

        this.identity = identity;
        this.sepal_length = sepal_length;
        this.sepal_width = sepal_width;
        this.petal_length = petal_length;
        this.petal_width = petal_width;

        initFeatures();

    }

    public FlowerData(String args) {

        String[] in = args.split(",");

        identity = in[4];
        sepal_length = Double.parseDouble(in[0]);
        sepal_width = Double.parseDouble(in[1]);
        petal_length = Double.parseDouble(in[2]);
        petal_width = Double.parseDouble(in[3]);

        initFeatures();

    }

    private void initFeatures() {

        features = new HashMap<>();

        features.put("sepal_length", sepal_length);
        features.put("sepal_width", sepal_width);
        features.put("petal_length", petal_length);
        features.put("petal_width", petal_width);
    }

    public double[] getFeatureVector() {
        return featureVector;
    }

    public double[] getSpecifiedVector(String[] attributes) {
        if (attributes.length > 4) {
            System.out.println("Not enough features!");
        }
        double[] vector = new double[attributes.length];
        for (int i = 0; i < attributes.length; i++) {
            vector[i] = features.get(attributes[i]);
        }
        return vector;
    }

    public String getIdentity() {return identity;}
}
