
/**
 * Write a description of class Student here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Student
{
    // instance variables
    private String firstName;
    private String lastName;
    private String id;
    private Float marks[];

    /**
     * Constructor for Student from a line from stats.txt file
     * @param line line from stats.txt file
     * @return Student object
     */
    public Student(String line)
    {
        String parts[] = line.split(",", -1);

        // Set the name and the id number
        this.firstName = parts[0];
        this.lastName = parts[1];
        this.id = parts[2];

        // Set the marks
        this.marks = new Float[3];
        // If the marks are empty, set them to 0
        if (parts[3].isEmpty()) { marks[0] = 0f; } else { marks[0] = Float.parseFloat(parts[3]); }
        if (parts[4].isEmpty()) { marks[1] = 0f; } else { marks[1] = Float.parseFloat(parts[4]); }
        if (parts[5].isEmpty()) { marks[2] = 0f; } else { marks[2] = Float.parseFloat(parts[5]); }
    }


    /**
     * Calculates the total marks of the student
     * @return total marks of the student
     */
    public Float getTotalMarks() {
        Float total = 0f;
        for (Float mark : marks) {
            total += mark;
        }
        return total;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getName() {
        return firstName + " " + lastName;
    }
    public String getId() {
        return id;
    }
    public String toString() {
        return firstName + " (" + id + ")";
    }
}
