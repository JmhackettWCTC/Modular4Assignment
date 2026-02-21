// Room.java
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Represents a room made up of four walls
public class Room implements Serializable {
    // A list that holds all walls for this room
    private List<Wall> wallList;

    // Creates four walls:
    // two with width x height and two with length x height
    public Room(double width, double length, double height) {
        wallList = new ArrayList<>();

        // Two walls using width and height
        wallList.add(new Wall(width, height));
        wallList.add(new Wall(width, height));

        // Two walls using length and height
        wallList.add(new Wall(length, height));
        wallList.add(new Wall(length, height));
    }

    // Sums the area of all walls to get total paintable area
    public double getArea() {
        double total = 0.0;
        for (Wall w : wallList) {
            total += w.getArea();
        }
        return total;
    }

    // Returns a string describing the room's total wall area
    @Override
    public String toString() {
        return "Room area: " + getArea();
    }
}
