package HTMLViewer;

import java.io.File;
import java.util.Scanner;

public class HTMLViewer {
    public static void main(String[] args) {
        File root = new File(System.getProperty("user.dir"));
        File htmlFile = new File(root, "/Chapter12/HTMLViewer/assets/index.html");

        try {
            Scanner scanner = new Scanner(htmlFile);

            String contents = "";

            while (scanner.hasNextLine()) {
                contents += scanner.nextLine() + "\n";
            }

            System.out.println(contents.replaceAll("<br>", "\n").replaceAll("<p>", "\n\n").replaceAll("<hr>", "\n----------------------------------------"));

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("Would you like to see it in the browser? (y/n) ");
        String choice = scanner.next();

        if (choice.equalsIgnoreCase("y")) {
            try {
                java.awt.Desktop.getDesktop().browse(htmlFile.toURI());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        scanner.close();
    }
}
