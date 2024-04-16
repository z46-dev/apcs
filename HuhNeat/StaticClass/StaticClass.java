package HuhNeat.StaticClass;

class Vector2D {
    double x, y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D subtract(Vector2D other) {
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    public Vector2D multiply(int scalar) {
        return new Vector2D(this.x * scalar, this.y * scalar);
    }

    public Vector2D divide(int scalar) {
        return new Vector2D(this.x / scalar, this.y / scalar);
    }

    public double magnitude() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public double direction() {
        return Math.atan2(this.y, this.x);
    }

    public Vector2D normalize() {
        return this.divide((int) this.magnitude());
    }

    public double dot(Vector2D other) {
        return this.x * other.x + this.y * other.y;
    }
}

public class StaticClass {
    public static void main(String[] args) {
        Vector2D v1 = new Vector2D(3, 4);
        Vector2D v2 = new Vector2D(5, 6);

        System.out.println("v1: " + v1.x + ", " + v1.y);
        System.out.println("v2: " + v2.x + ", " + v2.y);

        System.out.println("v1 + v2: " + v1.add(v2).x + ", " + v1.add(v2).y);
        System.out.println("v1 - v2: " + v1.subtract(v2).x + ", " + v1.subtract(v2).y);
        System.out.println("v1 * 2: " + v1.multiply(2).x + ", " + v1.multiply(2).y);
        System.out.println("v1 / 2: " + v1.divide(2).x + ", " + v1.divide(2).y);
        System.out.println("|v1|: " + v1.magnitude());
        System.out.println("dir(v1): " + v1.direction());
        System.out.println("v1 normalized: " + v1.normalize().x + ", " + v1.normalize().y);
        System.out.println("v1 dot v2: " + v1.dot(v2));
    }
}
