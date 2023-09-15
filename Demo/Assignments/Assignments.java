package Demo.Assignments;

public class Assignments {
    public static void main(String[] args) {
        Assignment[] assignments = new Assignment[5];

        for (int i = 0; i < 5; i ++) {
            assignments[i] = new Assignment("Assignment #" + String.valueOf(i), "UCONN Physics 1", "10/31/23", 10 + i * 5);
        }

        System.out.println("You have " + assignments.length + " outstanding assignments!");

        for (int i = 0; i < 5; i ++) {
            System.out.println(assignments[i]);
        }
    }
}