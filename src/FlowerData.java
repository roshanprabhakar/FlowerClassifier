/**
 * 1. sepal length in cm
 * 2. sepal width in cm
 * 3. petal length in cm
 * 4. petal width in cm
 * 5. class:
 */

public class FlowerData {

    String identity;

    double sepal_length;
    double sepal_width;
    double petal_length;
    double petal_width;

    public FlowerData(String identity, double sepal_length, double sepal_width, double petal_length, double petal_width) {
        this.identity = identity;
        this.sepal_length = sepal_length;
        this.sepal_width = sepal_width;
        this.petal_length = petal_length;
        this.petal_width = petal_width;
    }

    public FlowerData(String args) {

        String[] in = args.split(",");

        identity = in[4];
        sepal_length = Double.parseDouble(in[0]);
        sepal_width = Double.parseDouble(in[1]);
        petal_length = Double.parseDouble(in[2]);
        petal_width = Double.parseDouble(in[3]);

    }
}
