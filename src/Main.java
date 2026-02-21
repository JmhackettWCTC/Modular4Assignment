// Main.java
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static Scanner keyboard = new Scanner(System.in);
    private static PaintCalculator paintCalculator = new PaintCalculator();

    private static void printMenu() {
        System.out.println("1) Add room");
        System.out.println("2) View rooms");
        System.out.println("3) Read rooms from file");
        System.out.println("4) Write rooms to file");
        System.out.println("5) Exit program");
        System.out.print("Enter choice: ");
    }

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
                System.out.println("Please enter a valid number.");
                keyboard.nextLine(); // clear bad input
            }
        }
    }

    private static void createRoom() {
        double length = promptForDimension("length");
        double width = promptForDimension("width");
        double height = promptForDimension("height");
        paintCalculator.addRoom(length, width, height);
        System.out.println("Room added.");
    }

    private static void readFile() {
        System.out.print("Enter file name to read from: ");
        keyboard.nextLine(); // consume leftover newline
        String fileName = keyboard.nextLine();
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(fileName))) {
            Object obj = ois.readObject();
            if (obj instanceof PaintCalculator) {
                paintCalculator = (PaintCalculator) obj;
                System.out.println("Rooms loaded from file.");
            } else {
                System.out.println("File does not contain a PaintCalculator object.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    private static void writeFile() {
        System.out.print("Enter file name to write to: ");
        keyboard.nextLine(); // consume leftover newline
        String fileName = keyboard.nextLine();
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(paintCalculator);
            System.out.println("Rooms saved to file.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        boolean done = false;
        while (!done) {
            printMenu();
            int choice;
            try {
                choice = keyboard.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice.");
                keyboard.nextLine(); // clear bad input
                continue;
            }

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
                    done = true;
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
            System.out.println();
        }
    }
}
