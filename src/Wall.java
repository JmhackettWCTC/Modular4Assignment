// Wall.java
import java.io.Serializable;

// Represents a single wall with width and height
public class Wall implements Serializable {
    // Fields for the dimensions of the wall
    private double width;
    private double height;

    // Constructor initializes width and height
    public Wall(double width, double height) {
        this.width = width;
        this.height = height;
    }

    // Returns the width of the wall
    public double getWidth() {
        return width;
    }

    // Returns the height of the wall
    public double getHeight() {
        return height;
    }

    // Returns the area of the wall (width * height)
    public double getArea() {
        return width * height;
    }
}
