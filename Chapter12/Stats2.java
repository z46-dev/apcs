import java.io.*;
import java.util.Scanner;

public class Stats2 {
    public static void main(String[] args) {
        // Modify the Stats application to allow the user to enter the names and grades of the students. The user should be prompted for the name of the file to create and for the number of student grades that will be entered. After the data has been entered and the written to a file, the file should be read and the lowest, highest, and average score displayed.

        Scanner input = new Scanner(System.in);

        System.out.print("Enter the name of the file to create: ");
        String fileName = "\\fluorine\\2024\\HOME\\20240629\\CompSci\\Chapter12\\data\\" + input.nextLine();

        System.out.print("Enter the number of students: ");
        int numStudents = input.nextInt();

        StatsScore[] scores = new StatsScore[numStudents];

        for (int i = 0; i < numStudents; i++) {
            System.out.println("Enter the name of student " + (i + 1) + ": ");
            String name = input.next();

            System.out.println("Enter the score of student " + (i + 1) + ": ");
            int score = input.nextInt();

            scores[i] = new StatsScore(name, score);
        }

        try {
            FileWriter output = new FileWriter(fileName);
            BufferedWriter writer = new BufferedWriter(output);

            for (StatsScore score : scores) {
                writer.write(score.name + "\n");
                writer.write(score.score + "\n");
            }

            writer.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file!");
            System.exit(4);
        }
    }
}
