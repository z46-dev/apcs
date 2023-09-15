package Demo.Assignments;

public class Assignment {
    public String name, class, dueDate = "Unknown";
    public int points = 0;

    public Assignment(String name, String class, String dueDate, int points) {
        this.name = name;
        this.class = class;
        this.dueDate = dueDate;
        this.points = points;
    }

    public String toString() {
        return this.class + " | " + this.name + " | " + this.points + "pts (" + this.dueDate + ")";
    }
}