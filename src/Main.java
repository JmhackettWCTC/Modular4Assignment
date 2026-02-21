// Main.java
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

// Entry point and user interface for the paint calculator program
public class Main {

    // Shared Scanner for all user input
    private static Scanner keyboard = new Scanner(System.in);
    // Shared PaintCalculator that holds all rooms
    private static PaintCalculator paintCalculator = new PaintCalculator();

    // Prints the main menu options
    private static void printMenu() {
        System.out.println("1) Add room");
        System.out.println("2) View rooms");
        System.out.println("3) Read rooms from file");
        System.out.println("4) Write rooms to file");
        System.out.println("5) Configure paint settings");
        System.out.println("6) Exit program");
    }


    private static void configurePaintSettings() {
        double coverage = promptForDimension("coverage per gallon (square feet)");
        double price = promptForDimension("price per gallon");
        paintCalculator.setCoveragePerGallon(coverage);
        paintCalculator.setPricePerGallon(price);
        System.out.println("Paint settings updated.");
    }


    // Prompts user for a single dimension (e.g., length) and returns it
    // Repeats until a valid positive number is entered
    private static double promptForDimension(String dimensionName) {
        while (true) {
            System.out.print("Enter " + dimensionName + ": ");
            try {
                double value = keyboard.nextDouble();
                if (value <= 0) {
                    System.out.println("Value must be positive.");
                } else {
                    return value;
                }
            } catch (InputMismatchException e) {
                // Handle non-numeric input
                System.out.println("Please enter a valid number.");
                keyboard.nextLine(); // Clear invalid input from the buffer
            }
        }
    }

    // Creates a room using user-entered dimensions and adds it to the calculator
    private static void createRoom() {
        double length = promptForDimension("length");
        double width = promptForDimension("width");
        double height = promptForDimension("height");
        paintCalculator.addRoom(length, width, height);
        System.out.println("Room added.");
    }

    // Reads a PaintCalculator object from a file
    // Replaces the current paintCalculator if successful
    private static void readFile() {
        System.out.print("Enter file name to read from: ");
        keyboard.nextLine(); // Consume leftover newline from previous numeric input
        String fileName = keyboard.nextLine();

        // Try-with-resources ensures the stream is closed automatically
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(fileName))) {

            Object obj = ois.readObject();

            // Ensure the object in the file is a PaintCalculator
            if (obj instanceof PaintCalculator) {
                paintCalculator = (PaintCalculator) obj;
                System.out.println("Rooms loaded from file.");
            } else {
                System.out.println("File does not contain a PaintCalculator object.");
            }
        } catch (IOException | ClassNotFoundException e) {
            // Print a friendly error message if reading fails
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    // Writes the current PaintCalculator object to a file
    private static void writeFile() {
        System.out.print("Enter file name to write to: ");
        keyboard.nextLine(); // Consume leftover newline from previous numeric input
        String fileName = keyboard.nextLine();

        // Try-with-resources ensures the stream is closed automatically
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(fileName))) {

            // Serialize the paintCalculator to the file
            oos.writeObject(paintCalculator);
            System.out.println("Rooms saved to file.");
        } catch (IOException e) {
            // Print a friendly error message if writing fails
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Main loop: shows menu, reads user choice, and calls the appropriate method
    public static void main(String[] args) {
        boolean done = false;

        while (!done) {
            printMenu();

            int choice;
            try {
                choice = keyboard.nextInt();
            } catch (InputMismatchException e) {
                // Handle non-numeric menu choices
                System.out.println("Invalid choice.");
                keyboard.nextLine(); // Clear invalid input
                continue;
            }

            // Dispatch based on user's menu selection
            switch (choice) {
                case 1:
                    createRoom();
                    break;
                case 2:
                    System.out.println(paintCalculator.toString());
                    break;
                case 3:
                    readFile();
                    break;
                case 4:
                    writeFile();
                    break;
                case 5:
                    configurePaintSettings();
                    break;
                case 6:
                    done = true;
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
            System.out.println(); // Blank line for readability between iterations
        }
    }
}
