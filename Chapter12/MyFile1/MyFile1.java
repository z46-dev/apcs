import java.util.Scanner;
import java.io.File;

public class MyFile1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        File root = new File(System.getProperty("user.dir"));
        System.out.println("Root: " + root);

        String filename;

        while (true) {
            System.out.println("Enter a file name (-1 to stop): ");

            filename = input.nextLine();

            System.out.println("You entered " + filename);

            if (filename.equals("-1")) {
                break;
            }

            File file = new File(root, "/Chapter12/data/" + filename);

            System.out.println("File does " + (file.exists() ? "" : "not ") + "exist!");
        }

        input.close();
    }
}