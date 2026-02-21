// PaintCalculator.java
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Manages a collection of rooms to be painted
public class PaintCalculator implements Serializable {
    // List of rooms being tracked
    private List<Room> roomList;

    // Initializes an empty list of rooms
    public PaintCalculator() {
        roomList = new ArrayList<>();
    }

    // Creates a Room from the given dimensions and adds it to the list
    public void addRoom(double length, double width, double height) {
        // Note: Room constructor expects (width, length, height)
        Room room = new Room(width, length, height);
        roomList.add(room);
    }

    // Builds a string with the area of each room or a message if none exist
    @Override
    public String toString() {
        if (roomList.isEmpty()) {
            return "No rooms have been added.";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < roomList.size(); i++) {
            sb.append("Room ").append(i + 1).append(": ")
                    .append(roomList.get(i).getArea()).append("\n");
        }
        return sb.toString();
    }
}
