// PaintCalculator.java
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PaintCalculator implements Serializable {
    private List<Room> roomList;

    public PaintCalculator() {
        roomList = new ArrayList<>();
    }

    public void addRoom(double length, double width, double height) {
        Room room = new Room(width, length, height);
        roomList.add(room);
    }

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
