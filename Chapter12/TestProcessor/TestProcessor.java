package TestProcessor;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

class StatsScore {
    public String answers = "";
    public String name = "";

    public StatsScore(String name, String score) {
        this.name = name;
        this.answers = score;
    }

    public int getCorrectPercentage(String correctAnswers) {
        int correct = 0;

        for (int i = 0; i < correctAnswers.length(); i++) {
            if (answers.charAt(i) == correctAnswers.charAt(i)) {
                correct++;
            }
        }

        return (correct * 100) / correctAnswers.length();
    }
}

public class TestProcessor {
    static File root = new File(System.getProperty("user.dir"));
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    createTestFile();
                    break;
                case 2:
                    readTestFile();
                    break;
                case 3:
                    generateRandomTestFile();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void displayMenu() {
        System.out.println("1. Create a new test file with scores");
        System.out.println("2. Display scores from a test file");
        System.out.println("3. Generate a random test file with scores");
        System.out.println("4. Exit");
    }

    public static void createTestFile() {
        System.out.println("Enter the name of the test file: ");
        String fileName = input.next();
        File file = new File(root, "/Chapter12/data/" + fileName + ".dat");

        try {
            if (file.exists()) {
                System.out.println("File already exists!");
                return;
            }

            file.createNewFile();
        } catch (Exception ex) {
            System.out.println("Error creating file!");
            return;
        }

        System.out.print("Enter the correct answers: ");
        String correctAnswers = input.next();

        System.out.print("Enter the number of responses: ");
        int numScores = input.nextInt();

        ArrayList<StatsScore> responses = new ArrayList<StatsScore>();

        try {
            for (int i = 0; i < numScores; i++) {
                System.out.println("Enter the name of the student: ");
                String name = input.next();
                System.out.print("Enter the answers of the student: ");
                String answers = input.next();

                StatsScore statsScore = new StatsScore(name, answers);
                responses.add(statsScore);
            }
        } catch (Exception ex) {
            System.out.println("Error writing to file!");
            return;
        }

        try {
            java.io.PrintWriter output = new java.io.PrintWriter(file);

            output.println(correctAnswers);
            output.println(numScores);

            for (StatsScore score : responses) {
                output.println(score.name);
                output.println(score.answers);
            }

            output.close();
        } catch (Exception ex) {
            System.out.println("Error writing to file!");
            return;
        }
    }

    public static void readTestFile() {
        System.out.println("Enter the name of the test file: ");
        String fileName = input.next();
        File file = new File(root, "/Chapter12/data/" + fileName + ".dat");

        try {
            if (!file.exists()) {
                System.out.println("File does not exist!");
                return;
            }

            Scanner fileInput = new Scanner(file);

            String correctAnswers = fileInput.nextLine();
            int numScores = fileInput.nextInt();
            fileInput.nextLine();

            ArrayList<StatsScore> responses = new ArrayList<StatsScore>();

            for (int i = 0; i < numScores; i++) {
                String name = fileInput.nextLine();
                String answers = fileInput.nextLine();

                StatsScore statsScore = new StatsScore(name, answers);
                responses.add(statsScore);
            }

            System.out.println("Correct answers: " + correctAnswers);
            System.out.println("Number of responses: " + numScores);

            for (StatsScore score : responses) {
                System.out.println("Name: " + score.name + " | " + score.getCorrectPercentage(correctAnswers) + "%");
            }

            fileInput.close();
        } catch (Exception ex) {
            System.out.println("Error reading file!");
            return;
        }
    }

    public static void generateRandomTestFile() {
        System.out.println("Enter the name of the test file: ");
        String fileName = input.next();
        File file = new File(root, "/Chapter12/data/" + fileName + ".dat");

        try {
            if (file.exists()) {
                System.out.println("File already exists!");
                return;
            }

            file.createNewFile();
        } catch (Exception ex) {
            System.out.println("Error creating file!");
            return;
        }

        System.out.print("Enter the correct answers: ");
        String correctAnswers = input.next();

        System.out.print("Enter the number of responses: ");
        int numScores = input.nextInt();

        ArrayList<StatsScore> responses = new ArrayList<StatsScore>();

        try {
            for (int i = 0; i < numScores; i++) {
                String name = "Student" + i;
                String answers = "";

                for (int j = 0; j < correctAnswers.length(); j++) {
                    answers += (char) (65 + (int) (Math.random() * 4));
                }

                StatsScore statsScore = new StatsScore(name, answers);
                responses.add(statsScore);
            }
        } catch (Exception ex) {
            System.out.println("Error writing to file!");
            return;
        }

        try {
            java.io.PrintWriter output = new java.io.PrintWriter(file);

            output.println(correctAnswers);
            output.println(numScores);

            for (StatsScore score : responses) {
                output.println(score.name);
                output.println(score.answers);
            }

            output.close();
        } catch (Exception ex) {
            System.out.println("Error writing to file!");
            return;
        }
    }
}
