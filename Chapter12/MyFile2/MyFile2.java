package MyFile2;

import java.util.Scanner;
import java.io.*;

public class MyFile2 {
    public static void main(String[] args) {
        final String FILENAME = "zzz.txt";
        File root = new File(System.getProperty("user.dir"));

        // Create the file
        File file = new File(root, "/Chapter12/data/" + FILENAME);

        // Check if the file exists
        if (file.exists()) {
            System.out.println("File already exists!");
            askToDelete(file);
            System.exit(1);
        }

        // Create a file
        try {
            file.createNewFile();
        } catch (IOException ex) {
            System.out.println("Error writing to file " + FILENAME);
            System.exit(2);
        }

        askToDelete(file);
    }

    public static void askToDelete(File file) {
        // Ask if the user wants to delete the file
        Scanner input = new Scanner(System.in);
        
        System.out.println("Do you want to delete the file? (y/n)");

        String answer = input.nextLine();

        if (answer.equals("y")) {
            if (!file.delete()) {
                input.close();
                throw new RuntimeException("Failed to delete file!");
            }
            
            System.out.println("File deleted!");
        } else {
            System.out.println("File not deleted!");
        }

        input.close();
    }
}