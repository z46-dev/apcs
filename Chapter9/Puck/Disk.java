package Chapter9.Puck;

public class Disk {
    private double radius;
    private double thickness;

    public Disk(double radius, double thickness) {
        this.radius = radius;
        this.thickness = thickness;
    }
    
    public double getRadius() {
        return radius;
    }

    public double getThickness() {
        return thickness;
    }

    public double getVolume() {
        return Math.PI * radius * radius * thickness;
    }

    public String toString() {
        return "Disk with radius " + radius + " and thickness " + thickness;
    }

    public boolean equals(Object o) {
        if (o instanceof Disk) {
            Disk other = (Disk) o;

            return radius == other.radius && thickness == other.thickness;
        }

        return false;
    }
}
