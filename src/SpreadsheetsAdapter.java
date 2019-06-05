import java.util.ArrayList;

public class SpreadsheetsAdapter {

    private ArrayList<FlowerData> data;
    private String[] featureVector;

    public SpreadsheetsAdapter(ArrayList<FlowerData> data, String[] featureVector) {
        this.data = data;
        this.featureVector = featureVector;
    }

    public void printData(String identity) {
        for (FlowerData data : this.data) {
            if (data.getIdentity().equals(identity)) {
                double[] specified = data.getSpecifiedVector(featureVector);
                System.out.print("=SPLIT");
                System.out.print("(\"");
                for (int i = 0; i < specified.length; i++) {
                    System.out.print(specified[i]);
                    if (i < specified.length - 1) System.out.print(", ");
                }
                System.out.print("\"");
                System.out.print(", \", \")");
                System.out.println();
            }
        }
    }
}
