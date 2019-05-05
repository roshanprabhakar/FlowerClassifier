import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataLoader {

    private File input;
    private ArrayList<FlowerData> data;

    public DataLoader(String filepath) {
        input = new File(filepath);
        data = new ArrayList<>();
    }

    public void loadData() {

        try {

            BufferedReader reader = new BufferedReader(new FileReader(input));

            String line;
            while ((line = reader.readLine()) != null) {
                data.add(new FlowerData(line));
            }

        } catch (IOException e) {
            System.out.println("Could not load data from: " + input.getAbsolutePath());
        }

    }

    public ArrayList<FlowerData> getData() {return this.data;}


}
