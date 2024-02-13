import java.io.*;
import java.util.ArrayList;
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
        File root = new File(System.getProperty("user.dir"));

        System.out.print("Enter the name of the file to create: ");
        String fileName = "/Chapter12/data/" + input.nextLine();

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

        File file = new File(root, fileName);
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
            FileWriter output = new FileWriter(root.getAbsolutePath() + fileName);
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

        System.out.println("File created successfully!");

        // Now we can read the file and display the scores
        read(root, fileName);
    }

    public static void read(File root, String filename) {
        ArrayList<StatsScore> scores = new ArrayList<StatsScore>();

        try {
            FileReader input = new FileReader(root.getAbsolutePath() + filename);
            BufferedReader reader = new BufferedReader(input);

            while (true) {
                String name = reader.readLine();

                if (name == null) {
                    break;
                }

                int score = Integer.parseInt(reader.readLine());

                scores.add(new StatsScore(name, score));
            }

            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
            System.exit(2);
        } catch (IOException ex) {
            System.out.println("Error reading file!");
            System.exit(3);
        }

        // Display scores
        int lowestScore = 10000,
                highestScore = 0,
                avgScore = 0;

        for (StatsScore score : scores) {
            System.out.println(score.name + ": " + score.score);

            if (score.score < lowestScore) {
                lowestScore = score.score;
            }

            if (score.score > highestScore) {
                highestScore = score.score;
            }

            avgScore += score.score;
        }

        avgScore /= scores.size();

        System.out.println("Lowest score: " + lowestScore);
        System.out.println("Highest score: " + highestScore);
        System.out.println("Average score: " + avgScore);
    }
}
