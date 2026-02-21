// Room.java
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Room implements Serializable {
    private List<Wall> wallList;

    public Room(double width, double length, double height) {
        wallList = new ArrayList<>();
        // Two walls: width x height
        wallList.add(new Wall(width, height));
        wallList.add(new Wall(width, height));
        // Two walls: length x height
        wallList.add(new Wall(length, height));
        wallList.add(new Wall(length, height));
    }

    public double getArea() {
        double total = 0.0;
        for (Wall w : wallList) {
            total += w.getArea();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Room area: " + getArea();
    }
}
