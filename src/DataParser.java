import java.util.ArrayList;

public class DataParser {

    private String target;
    private ArrayList<FlowerData> targetData;
    private ArrayList<FlowerData> otherData;
    private ArrayList<FlowerData> allData;

    public DataParser(ArrayList<FlowerData> allData, String target) {

        this.target = target;
        this.allData = allData;

        this.targetData = new ArrayList<>();
        this.otherData = new ArrayList<>();

        for (FlowerData data : allData) {
            if (data.getIdentity().equals(target)) targetData.add(data);
            else otherData.add(data);
        }
    }

    public String getTarget() {
        return target;
    }

    public ArrayList<FlowerData> getTargetData() {
        return targetData;
    }

    public ArrayList<FlowerData> getOtherData() {
        return otherData;
    }

    public ArrayList<FlowerData> getAllData() {
        return allData;
    }
}
