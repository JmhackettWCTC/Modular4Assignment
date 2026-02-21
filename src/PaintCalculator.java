// PaintCalculator.java
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PaintCalculator implements Serializable {
    private List<Room> roomList;

    // coverage in square feet per gallon (commonly 350)
    private double coveragePerGallon = 350.0;  // one common assumption [web:35][web:38][web:41]
    private double pricePerGallon = 0.0;

    public PaintCalculator() {
        roomList = new ArrayList<>();
    }

    public void setCoveragePerGallon(double coveragePerGallon) {
        this.coveragePerGallon = coveragePerGallon;
    }

    public void setPricePerGallon(double pricePerGallon) {
        this.pricePerGallon = pricePerGallon;
    }

    // total wall area of all rooms
    public double getTotalArea() {
        double total = 0.0;
        for (Room r : roomList) {
            total += r.getArea();
        }
        return total;
    }

    // gallons needed = total area / coverage
    public double getGallonsNeeded() {
        if (coveragePerGallon <= 0) {
            return 0.0;
        }
        return getTotalArea() / coveragePerGallon;  // same idea as area / coverage examples [web:35][web:38][web:41]
    }

    // cost = gallons * price per gallon
    public double getTotalPaintCost() {
        return getGallonsNeeded() * pricePerGallon;  // similar to cost formulas in paint estimators [web:34][web:37][web:40]
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
            sb.append("Room ").append(i + 1).append(" area: ")
                    .append(String.format("%.2f", roomList.get(i).getArea()))  // 2 decimal places [web:36][web:39][web:42]
                    .append("\n");
        }
        sb.append("Total area: ")
                .append(String.format("%.2f", getTotalArea()))
                .append("\n");

        sb.append("Coverage per gallon: ")
                .append(String.format("%.2f", coveragePerGallon))
                .append(" sq ft\n");

        sb.append("Gallons needed: ")
                .append(String.format("%.2f", getGallonsNeeded()))
                .append("\n");

        sb.append("Price per gallon: $")
                .append(String.format("%.2f", pricePerGallon))
                .append("\n");

        sb.append("Total paint cost: $")
                .append(String.format("%.2f", getTotalPaintCost()))
                .append("\n");

        return sb.toString();
    }
}
