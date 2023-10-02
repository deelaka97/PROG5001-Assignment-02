import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class StatComputerTest {

    private StatComputer statComputer;
    private ByteArrayOutputStream outputStream;
    private ByteArrayInputStream inputStream;
    private ByteArrayOutputStream errStream;

    @BeforeEach
    public void setUp() {
        // Initialize the StatComputer instance or perform any necessary setup.
        // You may want to create temporary files or data for testing purposes.
        statComputer = new StatComputer();

        // Set up output streams for testing. (input streams are set up inside test functions)
        inputStream = new ByteArrayInputStream("".getBytes());
        outputStream = new ByteArrayOutputStream();
        errStream = new ByteArrayOutputStream();

        // Direct the standard out and error streams
        System.setOut(new PrintStream(outputStream));
        System.setErr(new PrintStream(errStream));

        // ... add any other setup ...
    }

    @AfterEach
    public void cleanUp() {
        // Clean up any temporary files or data created for testing.
        statComputer = new StatComputer();


        // Reset buffers (So they can be reused)
        inputStream.reset();
        outputStream.reset();
        errStream.reset();

        // Restore standard input and output streams.
        System.setIn(System.in);
        System.setOut(System.out);
        System.setErr(System.err);
    }

    @Test
    public void testF5Exit() {
        // Set the inputs
        String input = "F5\n";
        // Create the input stream
        inputStream = new ByteArrayInputStream(input.getBytes());

        // Set the input stream
        System.setIn(inputStream);

        // Run the program
        StatComputer.main(new String[]{});

        // Check the output
        String expectedOutput = """
            Please  select one of the following options:
            F1: Read file
            F2: Print total marks of each student
            F3: Print a list of students with marks below threshold
            F4: Print the 5 students with highest and lowest marks
            F5: Exit
            >>> Exiting...
            """;

        // Test the output
        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
    }

    @Test
    public void testF1ReadingData() {
        // Set the inputs
        String input = """
        F1
        stats.txt
        F5
        """;
        // Create input stream
        inputStream = new ByteArrayInputStream(input.getBytes());

        // Set the input stream
        System.setIn(inputStream);

        // Run the program
        StatComputer.main(new String[]{});

        // Check the output
        String expectedOutput = """
            Please  select one of the following options:
            F1: Read file
            F2: Print total marks of each student
            F3: Print a list of students with marks below threshold
            F4: Print the 5 students with highest and lowest marks
            F5: Exit
            >>> Please enter the name of the file: Reading file...\r\nFinished reading "stats.txt"\r\n            
            Please  select one of the following options:
            F1: Read file
            F2: Print total marks of each student
            F3: Print a list of students with marks below threshold
            F4: Print the 5 students with highest and lowest marks
            F5: Exit
            >>> Exiting...
            """;

        // Test the output
        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
        // Test the values loaded
        assertEquals("Unit Name 01 - UNIT CONTROL SYSTEMS 101", statComputer.getUnitName());
        assertEquals(11, statComputer.getStudents().size());
    }

    @Test
    public void testF2TotalMarks() {
        // Set the inputs
        String input = """
        F1
        stats.txt
        F2
        F5
        """;
        // Create input stream
        inputStream = new ByteArrayInputStream(input.getBytes());

        // Set the input stream
        System.setIn(inputStream);

        // Run the program
        StatComputer.main(new String[]{});

        // Check the output
        String expectedOutput = """
            Please  select one of the following options:
            F1: Read file
            F2: Print total marks of each student
            F3: Print a list of students with marks below threshold
            F4: Print the 5 students with highest and lowest marks
            F5: Exit
            >>> Please enter the name of the file: Reading file...\r\nFinished reading "stats.txt"\r\n                        
            Please  select one of the following options:
            F1: Read file
            F2: Print total marks of each student
            F3: Print a list of students with marks below threshold
            F4: Print the 5 students with highest and lowest marks
            F5: Exit
            >>> Printing total marks...\r
            Name 01, AB010, 39.0\r
            Name 02, AB011, 87.0\r
            Name 03, AB001, 12.0\r
            Name 04, AB002,  3.0\r
            Name 05, AB003,  4.0\r
            Name 06, AB004,  5.0\r
            Name 07, AB005,  6.0\r
            Name 08, AB006,  7.0\r
            Name 09, AB007,  8.0\r
            Name 10, AB008,  9.0\r
            Name 11, AB009, 10.0\r\n                        
            Please  select one of the following options:
            F1: Read file
            F2: Print total marks of each student
            F3: Print a list of students with marks below threshold
            F4: Print the 5 students with highest and lowest marks
            F5: Exit
            >>> Exiting...
            """;

        // Test the output
        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
    }

    @Test
    public void testF3BelowThreshold() {
        // Set the inputs
        String input = """
        F1
        stats.txt
        F3
        15
        F5
        """;
        // Create input stream
        inputStream = new ByteArrayInputStream(input.getBytes());

        // Set the input stream
        System.setIn(inputStream);

        // Run the program
        StatComputer.main(new String[]{});

        // Check the output
        String expectedOutput = """
                Please  select one of the following options:
                F1: Read file
                F2: Print total marks of each student
                F3: Print a list of students with marks below threshold
                F4: Print the 5 students with highest and lowest marks
                F5: Exit
                >>> Please enter the name of the file: Reading file...\r\nFinished reading "stats.txt"\r\n
                Please  select one of the following options:
                F1: Read file
                F2: Print total marks of each student
                F3: Print a list of students with marks below threshold
                F4: Print the 5 students with highest and lowest marks
                F5: Exit
                >>> Please enter the threshold: Students below 15.0 marks\r
                Name 03, AB001, 12.0\r
                Name 04, AB002,  3.0\r
                Name 05, AB003,  4.0\r
                Name 06, AB004,  5.0\r
                Name 07, AB005,  6.0\r
                Name 08, AB006,  7.0\r
                Name 09, AB007,  8.0\r
                Name 10, AB008,  9.0\r
                Name 11, AB009, 10.0\r\n
                Please  select one of the following options:
                F1: Read file
                F2: Print total marks of each student
                F3: Print a list of students with marks below threshold
                F4: Print the 5 students with highest and lowest marks
                F5: Exit
                >>> Exiting...
                """;

        // Test the output
        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
    }

    @Test
    public void testF4HighestAndLowestMarks() {
        // Set the inputs
        String input = """
        F1
        stats.txt
        F4
        F5
        """;
        // Create input stream
        inputStream = new ByteArrayInputStream(input.getBytes());

        // Set the input stream
        System.setIn(inputStream);

        // Run the program
        StatComputer.main(new String[]{});

        // Check the output
        String expectedOutput = """
                Please  select one of the following options:
                F1: Read file
                F2: Print total marks of each student
                F3: Print a list of students with marks below threshold
                F4: Print the 5 students with highest and lowest marks
                F5: Exit
                >>> Please enter the name of the file: Reading file...\r\nFinished reading "stats.txt"\r\n        
                Please  select one of the following options:
                F1: Read file
                F2: Print total marks of each student
                F3: Print a list of students with marks below threshold
                F4: Print the 5 students with highest and lowest marks
                F5: Exit
                >>> Students with highest marks\r
                Name 02, AB011, 87.0
                Name 01, AB010, 39.0
                Name 03, AB001, 12.0
                Name 11, AB009, 10.0
                Name 10, AB008,  9.0\n\r
                Students with lowest marks\r
                Name 04, AB002,  3.0
                Name 05, AB003,  4.0
                Name 06, AB004,  5.0
                Name 07, AB005,  6.0
                Name 08, AB006,  7.0\n
                Please  select one of the following options:
                F1: Read file
                F2: Print total marks of each student
                F3: Print a list of students with marks below threshold
                F4: Print the 5 students with highest and lowest marks
                F5: Exit
                >>> Exiting...
                """;

        // Test the output
        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
    }

    @Test
    public void test05InvalidMenuOption() {
        // Set the inputs
        String input = """
        X2
        F5
        """;
        // Create input stream
        inputStream = new ByteArrayInputStream(input.getBytes());

        // Set the input stream
        System.setIn(inputStream);

        // Run the program
        StatComputer.main(new String[]{});

        // Check the output
        String expectedOutput = "Invalid choice";

        // Test the output
        assertEquals(expectedOutput.trim(), errStream.toString().trim());
    }

    @Test
    public void test03ReadFileDefaultFile() {
        // Arrange: Give an empty input file.

        // Act: Call the readFile() method.
        StatComputer.readFile();

        // Assert: Verify that the unitName and students have been populated correctly.
        assertEquals("Unit Name 01 - UNIT CONTROL SYSTEMS 101", statComputer.getUnitName());
        assertEquals(11, statComputer.getStudents().size());
    }

    @Test
    public void test02ReadFileNonExistingFile(){
        // Arrange: Create a non-existing data file and set it as the input.
        String inputFileName = "Does not exist.txt";

        // Act: Call the readFile() method with a non-existing file name.
        StatComputer.readFile(inputFileName);

        // Assert: Verify that the error is printed
        String output = errStream.toString();
        assertEquals("\"%s\" does not exist".formatted(inputFileName), output.trim());

    }

    @Test
    public void test01ReadFileEmptyFile(){
        // Arrange: Create an empty data file and set it as the input.
        String inputFileName = "EmptyFile.txt";

        // Act: Call the readFile() method with a non-existing file name.
        StatComputer.readFile(inputFileName);

        // Assert: Verify that the error is printed
        String output = errStream.toString();
        assertEquals("\"%s\" is empty".formatted(inputFileName), output.trim());

    }

    @Test
    public void test04SpecialCharactersInFileName() {
        // Set the inputs
        String inputFileName = "!~`@#$%^&*()_+=-[]{};':\\\",./<>?stats.txt";
        String input = """
        F1
        %s
        F5
        """.formatted(inputFileName);
        // Create input stream
        inputStream = new ByteArrayInputStream(input.getBytes());

        // Set the input stream
        System.setIn(inputStream);

        // Run the program
        StatComputer.main(new String[]{});

        // Check the output
        String expectedOutput = "\"%s\" does not exist".formatted(inputFileName);

        // Test the output
        assertEquals(expectedOutput.trim(), errStream.toString().trim());
    }

    @Test
    public void test06CommentedLinesInDataFile() {
        // Set the inputs
        String input = """
        F1
        statsWithComments.txt
        F5
        """;
        // Create input stream
        inputStream = new ByteArrayInputStream(input.getBytes());

        // Set the input stream
        System.setIn(inputStream);

        // Run the program
        StatComputer.main(new String[]{});

        // Assert: Verify that the unitName and students have been populated correctly.
        assertEquals("Unit Name 01 - UNIT CONTROL SYSTEMS 101", statComputer.getUnitName());
        assertEquals(1, statComputer.getStudents().size());
    }

    @Test
    public void test07NonNumericThreshold() {
        // Set the inputs
        String input = """
        F1
        stats.txt
        F3
        A
        F5
        """;
        // Create input stream
        inputStream = new ByteArrayInputStream(input.getBytes());

        // Set the input stream
        System.setIn(inputStream);

        // Run the program
        StatComputer.main(new String[]{});

        // Check the output
        String expectedOutput = "Invalid input";

        // Test the output
        assertEquals(expectedOutput.trim(), errStream.toString().trim());
    }

    @Test
    public void test08SortingAlgorithm() {
        // Set the inputs
        String input = """
        F1
        stats.txt
        F5
        """;
        // Create input stream
        inputStream = new ByteArrayInputStream(input.getBytes());

        // Set the input stream
        System.setIn(inputStream);

        // Run the program
        StatComputer.main(new String[]{});

        // Sort the students by their marks
        StatComputer.sortStudents();

        // Check if they have been sorted correctly
        // Test the sorting
        assertTrue(checkSort());
    }

    private boolean checkSort() {
        // Loop through the students and check if they are sorted
        Float max = Float.MAX_VALUE;
        for (Student student : statComputer.getStudents())
        {
            // If the student has total marks more than the previous student, fail
            if (student.getTotalMarks() > max)
            {
                return false;
            }
            else {
                max = student.getTotalMarks();
                continue;
            }
        }
        // IF every student has fewer marks than the previous student, pass
        return true;
    }

    @Test
    public void test09InvalidDataInDataFile() {
        // Set the inputs
        String input = """
        F1
        statsWithInvalidData.txt
        F5
        """;
        // Create input stream
        inputStream = new ByteArrayInputStream(input.getBytes());

        // Set the input stream
        System.setIn(inputStream);

        // Run the program
        StatComputer.main(new String[]{});

        // Find the lines with errors
        String expectedOutput = "Invalid input: \"Name,04,AB002,AB,01,01\"\r\nInvalid input: \"Name,07,AB005,04,01\"";

        // Test the output
        assertEquals(expectedOutput.trim(), errStream.toString().trim());

        // Assert: Verify that the unitName and students have been populated correctly.
        assertEquals("Unit Name 01 - UNIT CONTROL SYSTEMS 101", statComputer.getUnitName());
        assertEquals(9, statComputer.getStudents().size());

    }

    @Test
    public void test10AlwaysFail() {
        // This test always fails
        fail();
    }

    // Define more test methods for other test cases in your test plan...

}
