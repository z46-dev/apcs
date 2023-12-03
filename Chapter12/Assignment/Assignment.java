package Assignment;
import java.io.*;

public class Assignment {
    public static void main(String[] args) {
        File file = new File("\\\\fluorine/2024/HOME/20240629/CompSci/Chapter12/data/assignment.txt");

        if (!file.exists()) {
            System.out.println("File does not exist!");
            System.exit(1);
        }

        try {
            FileReader input = new FileReader(file);
            BufferedReader reader = new BufferedReader(input);

            while (true) {
                String line = reader.readLine();

                if (line == null) {
                    break;
                }

                System.out.println(line);
            }

            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
            System.exit(2);
        } catch (IOException ex) {
            System.out.println("Error reading file!");
            System.exit(3);
        }
    }
}
