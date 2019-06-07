public class MapPoint {

    //values for the different weights represented by x, y
    //exact weight values don't matter as long as increment is known
    private int x;
    private int y;
    private int value;

    public MapPoint(double w1, double w2, double increment, int value) {
        this.x = (int)(w1 / increment);
        this.y = (int)(w2 / increment);
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String toString() {
        return "Point({" + x + ", " + y + ", " + value + "})";
    }
}
