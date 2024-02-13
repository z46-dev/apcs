package NonAbsolute;

import java.io.File;
import java.util.Scanner;

public class NonAbsolute {
    public static void main(String[] args) {
        // Get the current working directory.
        // In my case, it's going to be C:\Users\parke\Documents\apcs
        File root = new File(System.getProperty("user.dir"));
        System.out.println("Root: " + root);

        // Now we have a modifier to the directory where we store files
        String modifier = "/Chapter12/data/";

        // Get a file name from the user
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a file name: ");
        String filename = input.nextLine();
        input.close();

        // Create a file object using the root directory and the modifier
        // If I input "assignment.txt", the file object will be "C:\Users\parke\Documents\apcs/Chapter12/data/assignment.txt"
        File file = new File(root, modifier + filename);

        // Check if the file exists using a 1 line ternary operator
        System.out.println("File does " + (file.exists() ? "" : "not ") + "exist!");
    }
}

/*
 * My working directory where I keep all of my computer science projects is C:\Users\parke\Documents\apcs
 * However, you might be directly in something like C:\Users\parke\Documents\apcs\Chapter12
 * In that case, the root directory will be C:\Users\parke\Documents\apcs\Chapter12
 * Then, you should change your modifier to /data/ instead of /Chapter12/data/
 * Or if you just put your files in the Chapter12 directory, you can just use / as the modifier
 * Play around with it a little bit, it's an easier way to not have to remember all the directories across different computers,
 * especially since on the school ones we use a fluorine drive, and on a windows home computer, it's just C:\, but on linux it's /home/username
 * 
 * Hope this helps!
 */