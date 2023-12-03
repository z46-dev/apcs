import java.io.*;
import java.util.Scanner;

class StatsScore {
    public int score = 0;
    public String name = "";

    public StatsScore(String name, int score) {
        this.name = name;
        this.score = score;
    }
}

public class Stats2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the name of the file to create: ");
        String fileName = "\\\\fluorine\\2024\\HOME\\20240629\\CompSci\\Chapter12\\data\\" + input.nextLine();

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

        input.close();

        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                System.out.println("Error creating file!");
                System.out.println(ex.getMessage());
                System.exit(1);
            }
        }

        try {
            FileWriter output = new FileWriter(fileName);
            BufferedWriter writer = new BufferedWriter(output);

            for (StatsScore score : scores) {
                writer.write(score.name);
                writer.newLine();
                writer.write(String.valueOf(score.score));
                writer.newLine();
            }

            writer.close();
            output.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file!");
            System.out.println(ex.getMessage());
            System.exit(4);
        }
    }
}
