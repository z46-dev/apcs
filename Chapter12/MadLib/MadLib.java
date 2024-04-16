package MadLib;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MadLib {
    static File root = new File(System.getProperty("user.dir"));
    static File storyFile = new File(root, "/Chapter12/MadLib/assets/story.txt");
    static File nounsFile = new File(root, "/Chapter12/MadLib/assets/nouns.txt");
    static File verbsFile = new File(root, "/Chapter12/MadLib/assets/verbs.txt");
    
    public static void main(String[] args) {
        System.out.println("Welcome to MadLib!");
        System.out.println("Here is the story:");

        try {
            Scanner scanner = new Scanner(storyFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split(" ");

                for (String word : words) {
                    if (word.startsWith("#")) {
                        System.out.print(word.replace("#", getNoun()) + " ");
                    } else if (word.equals("%")) {
                        System.out.print(word.replace("%", getVerb()) + " ");
                    } else {
                        System.out.print(word + " ");
                    }
                }

                System.out.println();
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static String getNoun() {
        ArrayList<String> nouns = new ArrayList<String>();

        try {
            Scanner scanner = new Scanner(nounsFile);

            while (scanner.hasNextLine()) {
                nouns.add(scanner.nextLine());
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return nouns.get((int) (Math.random() * nouns.size()));
    }

    public static String getVerb() {
        ArrayList<String> verbs = new ArrayList<String>();

        try {
            Scanner scanner = new Scanner(verbsFile);

            while (scanner.hasNextLine()) {
                verbs.add(scanner.nextLine());
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return verbs.get((int) (Math.random() * verbs.size()));
    }
}
