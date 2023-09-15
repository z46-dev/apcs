package Demo.Assignments;

public class Assignment {
    public String name, classString, dueDate = "Unknown";
    public int points = 0;

    public Assignment(String name, String classString, String dueDate, int points) {
        this.name = name;
        this.classString = classString;
        this.dueDate = dueDate;
        this.points = points;
    }

    public String toString() {
        return this.classString + " | " + this.name + " | " + this.points + "pts (" + this.dueDate + ")";
    }
}