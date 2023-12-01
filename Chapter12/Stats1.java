import java.io.*;
import java.util.ArrayList;

public class Stats1 {
    public static void main(String[] args) {
        File file = new File("\\\\fluorine\\2024\\HOME\\20240629\\CompSci\\Chapter12\\data\\test1.dat");

        if (!file.exists()) {
            System.out.println("File does not exist!");
            System.exit(1);
        }

        ArrayList<StatsScore> scores = new ArrayList<StatsScore>();

        try {
            FileReader input = new FileReader(file);
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
