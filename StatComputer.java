import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Write a description of class StatComputer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StatComputer
{
    // instance variables - replace the example below with your own
    private static boolean running;
    private static String unitName;

    private static List<Student> students;

    // Scanner for inputs
    private static Scanner scanner;

    public StatComputer() {
        this.running = false;
        this.unitName = "";
        this.students = new ArrayList<Student>();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Main method to get started from
     */
    public static void main(String[] args) {
        running = false;
        scanner = new Scanner(System.in);   // Create a scanner object
        unitName = "";
        students = new ArrayList<Student>();

        // Start the loop
        running = true;

        while (running) {
            // Print the menu
            printMenu();
            System.out.print(">>> ");

            // Get the user's choice
            String choice = scanner.nextLine();

            // TODO: Find a way to clear the console after taking input

            // Execute the corresponding action
            switch (choice) {
                case "F1":
                    // F1: Read data from file
                    readFileMenu();
                    break;
                case "F2":
                    // F2: Calculate and print total marks of each student
                    printTotalMarks();
                    break;
                case "F3":
                    // F3: Print a list of students with marks below threshhold
                    printStudentsBelowThreshold();
                    break;
                case "F4":
                    // F4: Print the 5 students with highest and lowest marks
                    printHighestAndLowestMarks();
                    break;
                case "F5":
                    // Exit
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    // Invalid choice
                    System.err.println("Invalid choice");
                    break;
            }
        }

    }

    public static void readFileMenu() {
        System.out.print("Please enter the name of the file: ");
        String fileName = scanner.nextLine();

        // If an empty name was given, use default file name
        if (fileName.isEmpty()) {
            readFile();
            return;
        }

        // Otherwise read the given file
        readFile(fileName);
    }

    /**
     * Read the stats from the file and save them to the unitName and students.
     * File name defaults into "stats.txt". if "stats.txt" does not exist, ask
     * the user to enter the location of the file. Continuously asks for a file
     * name if the given file name does not exist on the disk
     *
     */
    public static void readFile(String fileName) {
        System.out.println("Reading file...");

        if (!checkIfFileExists(fileName)) {
            // Return to the main menu
            return;
        }

        // Reader for the file
        BufferedReader br = getReader(fileName);

        // Read lines from the file
        readLines(br);

        System.out.println("Finished reading \"%s\"".formatted(fileName));
    }

    /**
     * Read the stats from the file and save them to the unitName and students.
     * File name defaults into "stats.txt". if "stats.txt" does not exist, ask
     * the user to enter the location of the file. Continuously asks for a file
     * name if the given file name does not exist on the disk
     *
     */
    public static void readFile() {
        String fileName = "stats.txt";  // default filename
        readFile(fileName);
    }


    /**
     * Prints the menu for the console
     */
    private static void printMenu() {
        System.out.print(
                """
                
                Please  select one of the following options:
                F1: Read file
                F2: Print total marks of each student
                F3: Print a list of students with marks below threshold
                F4: Print the 5 students with highest and lowest marks
                F5: Exit
                """);
    }

    /**
     * Finds and prints the 5 students with highest and lowest total marks
     */
    private static void printHighestAndLowestMarks() {
        // Sort the students in decreasing order
        sortStudents();

        // Find the top 5 students
        List<Student> topStudents = new ArrayList<Student>();
        for (int i = 0; i < Math.min(students.size(), 5); i++) {
            topStudents.add(students.get(i));
        }

        // Find the bottom 5 students
        List<Student> bottomStudents = new ArrayList<Student>();
        for (int i = 0; i < Math.min(students.size(), 5); i++) {
            bottomStudents.add(students.get(students.size() - 1 - i));
        }


        // Print the top 5 students (or maximum possible amount)
        // Find the maximum length of each field
        List<Integer> maxLengths = new ArrayList<Integer>();
        maxLengths.add(0);
        maxLengths.add(0);
        for (Student student : topStudents) {
            if (student.getName().length() > maxLengths.get(0)) {
                maxLengths.set(0, student.getName().length());
            }
            if (student.getId().length() > maxLengths.get(1)) {
                maxLengths.set(1, student.getId().length());
            }
        }
        System.out.println("Students with highest marks");
        for (Student student : topStudents) {
            System.out.printf("%-" + maxLengths.get(0) + "s, %-" + maxLengths.get(1) + "s, %4.1f\n", student.getName(), student.getId(), student.getTotalMarks());
        }

        // Add a line break
        System.out.println();

        // Print the bottom 5 students (or maximum possible amount)
        // Find the maximum length of each field
        maxLengths = new ArrayList<Integer>();
        maxLengths.add(0);
        maxLengths.add(0);
        for (Student student : bottomStudents) {
            if (student.getName().length() > maxLengths.get(0)) {
                maxLengths.set(0, student.getName().length());
            }
            if (student.getId().length() > maxLengths.get(1)) {
                maxLengths.set(1, student.getId().length());
            }
        }
        System.out.println("Students with lowest marks");
        for (Student student : bottomStudents) {
            System.out.printf("%-" + maxLengths.get(0) + "s, %-" + maxLengths.get(1) + "s, %4.1f\n", student.getName(), student.getId(), student.getTotalMarks());
        }
    }

    /**
     * Sorts the students list in decreasing order using bubble sort
     */
    public static void sortStudents() {
        // Sort the students in decreasing order
        // This sorting algorithm uses bubble sort
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = i + 1; j < students.size(); j++) {
                if (students.get(i).getTotalMarks() < students.get(j).getTotalMarks()) {
                    // Swap the students
                    Student temp = students.get(i);
                    students.set(i, students.get(j));
                    students.set(j, temp);
                }
            }
        }
    }

    /**
     * Find and print the students with below threshold total score.
     * Threshold is read from the user input after running the function
     */
    private static void printStudentsBelowThreshold() {
        System.out.print("Please enter the threshold: ");
        String input = scanner.nextLine();

        // Convert the input to integer
        Float threshold;
        try {
            threshold = Float.parseFloat(input);
        } catch (NumberFormatException e) {
            System.err.println("Invalid input");
            return;
        }

        // Find and print the students with below threshold
        List<Student> studentsBelowThreshold = new ArrayList<Student>();
        System.out.println("Students below %.1f marks".formatted(threshold));
        for (Student student : students) {
            if (student.getTotalMarks() < threshold) {
                studentsBelowThreshold.add(student);
            }
        }

        // Find the maximum length of each field
        List<Integer> maxLengths = new ArrayList<Integer>();
        maxLengths.add(0);
        maxLengths.add(0);
        for (Student student : studentsBelowThreshold) {
            if (student.getName().length() > maxLengths.get(0)) {
                maxLengths.set(0, student.getName().length());
            }
            if (student.getId().length() > maxLengths.get(1)) {
                maxLengths.set(1, student.getId().length());
            }
        }

        // Print the student details
        for (Student student : studentsBelowThreshold) {
            System.out.println(("%-"+ maxLengths.get(0).toString() +"s, %"+ maxLengths.get(1).toString() + "s, %4.1f").formatted(student.getName(), student.getId(), student.getTotalMarks()));
        }
    }

    /**
     * Calculates and prints total marks for each student read from the stats file
     */
    private static void printTotalMarks() {
        System.out.println("Printing total marks...");

        // Find the maximum length of each field
        List<Integer> maxLengths = new ArrayList<Integer>();
        maxLengths.add(0);
        maxLengths.add(0);
        for (Student student : students) {
            if (student.getName().length() > maxLengths.get(0)) {
                maxLengths.set(0, student.getName().length());
            }
            if (student.getId().length() > maxLengths.get(1)) {
                maxLengths.set(1, student.getId().length());
            }
        }

        // Loop through each student
        for (Student student : students) {
            // Calculate the total marks
            Float totalMarks = student.getTotalMarks();

            // Print the total marks
            System.out.println(("%-"+ maxLengths.get(0).toString() +"s, %"+ maxLengths.get(1).toString() + "s, %4.1f").formatted(student.getName(), student.getId(), totalMarks));
        }
    }

    /**
     * Method to check if the file exists on disk
     * @param filePath path to the file
     * @return true if the file exists, false otherwise
     */
    public static boolean checkIfFileExists(String filePath) {
        File file = new File(filePath);
        // Check if the file exists on the disk
        if (!file.exists()) {
            // If the file does not exist
            System.err.println("\"%s\" does not exist".formatted(filePath));
            return false;
        }

        // Check if the file is empty
        if (file.length() == 0 ) {
            // If the file is empty
            System.err.println("\"%s\" is empty".formatted(filePath));
            return false;
        }

        // If the file exists and non-empty
        return true;
    }

    /**
     * Creates a BufferedReader from the file
     * @param filePath path to the file
     * @return BufferedReader object
     */
    public static BufferedReader getReader(String filePath) {
        try {
            List<Student> students1 = new ArrayList<Student>();
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            return br;
        }
        catch (FileNotFoundException e) {
            // We checked if the file exists. So this part is redundant.
            System.err.println("\"%s\" not found".formatted(filePath));
        }

        return null;
    }

    /**
     * Reads the lines from the file
     * @param reader BufferedReader object
     * @return List of Students
     */
    public static void readLines(BufferedReader reader) {
        unitName = "";
        String headers = "";
        try {
            // Read the lines
            String line;

            while ((line = reader.readLine()) != null) {
                // Check if the line is commented out
                if (line.startsWith("#")) {
                    continue;
                }

                // If it is the first line
                if (unitName.isEmpty()) {
                    // Get the unit name
                    unitName = line;
                    continue;
                }

                // If it is the headers lines
                if (headers.isEmpty()) {
                    headers = line;
                    continue;
                }

                // Check if the formatting is correct
                String[] parts = line.split(",", -1);
                if (parts.length != 6) {
                    System.err.println("Invalid input: \"%s\"".formatted(line));
                    continue;
                }
                // Check if the marks are valid
                try {
                    if (!parts[3].isEmpty()) {Float.parseFloat(parts[3]);}
                    if (!parts[4].isEmpty()) {Float.parseFloat(parts[4]);}
                    if (!parts[5].isEmpty()) {Float.parseFloat(parts[5]);}
                }
                catch (NumberFormatException e) {
                    System.err.println("Invalid input: \"%s\"".formatted(line));
                    continue;
                }

                // Create a new student
                Student student = new Student(line);

                // Add the student to the list
                students.add(student);
            }
        }
        catch (IOException e) {
            System.err.println("Error reading file");
            System.err.println(e.getMessage());
        }
    }

    public String getUnitName() {
        return unitName;
    }

    public List<Student> getStudents() {
        return students;
    }
}
