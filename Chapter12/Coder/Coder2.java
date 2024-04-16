package Coder;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Coder2 {
    final public static int SHIFT = 5;

    public static void main(String[] args) {
        File root = new File(System.getProperty("user.dir"));
        File inputFile = new File(root, "/Chapter12/Coder/assets/input.txt");
        File outputFile = new File(root, "/Chapter12/Coder/assets/output.txt");

        if (!inputFile.exists()) {
            try {
                inputFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (!outputFile.exists()) {
            try {
                outputFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            Scanner fileScanner = new Scanner(inputFile);
            FileWriter fileWriter = new FileWriter(outputFile);

            String contents = "";

            while (fileScanner.hasNextLine()) {
                contents += fileScanner.nextLine() + "\n";
            }

            fileWriter.write(encode(contents));

            fileScanner.close();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encode(String input) {
        String output = "";

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c >= 'a' && c <= 'z') {
                c += SHIFT;

                if (c > 'z') {
                    c -= 26;
                }
            }

            if (c >= 'A' && c <= 'Z') {
                c += SHIFT;

                if (c > 'Z') {
                    c -= 26;
                }
            }

            output += c;
        }

        return output;
    }

    public static String decode(String input) {
        String output = "";
        
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c >= 'a' && c <= 'z') {
                c -= SHIFT;

                if (c < 'a') {
                    c += 26;
                }
            }

            if (c >= 'A' && c <= 'Z') {
                c -= SHIFT;

                if (c < 'A') {
                    c += 26;
                }
            }

            output += c;
        }

        return output;
    }
}
