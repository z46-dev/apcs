package Chapter9.Puck;

public class Puck extends Disk {
    private double weight;
    private boolean standard;
    private boolean youth;

    public Puck(double radius, double thickness, double weight) {
        super(radius, thickness);

        this.weight = weight;
        standard = thickness == 1.0 && (weight <= 5.5 && weight >= 5.0);
        youth = thickness == 0.75 && (weight <= 4.5 && weight >= 4.0);
    }

    public double getWeight() {
        return weight;
    }

    public boolean isStandard() {
        return standard;
    }

    public String getDivision() {
        if (standard) {
            return "Standard";
        }

        if (youth) {
            return "Youth";
        }

        return "Unknown";
    }

    public boolean equals(Object o) {
        if (o instanceof Puck) {
            Puck other = (Puck) o;

            return super.equals(other) && weight == other.weight;
        }

        return false;
    }

    public String toString() {
        return super.toString() + " and weight " + weight;
    }

    public static void main(String[] args) {
        Puck puck = new Puck(3.0, 1.0, 5.0);

        System.out.println(puck);
        System.out.println("Volume: " + puck.getVolume());
        System.out.println("Weight: " + puck.getWeight());
        System.out.println("Standard: " + puck.isStandard());
        System.out.println("Division: " + puck.getDivision());
    }
}
