package Extra.RunAway;

public class Vector2D {
    public double x, y;

    Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void add(Vector2D other) {
        x += other.x;
        y += other.y;
    }

    public void sub(Vector2D other) {
        x -= other.x;
        y -= other.y;
    }

    public void mult(double scalar) {
        x *= scalar;
        y *= scalar;
    }

    public void div(double scalar) {
        x /= scalar;
        y /= scalar;
    }

    public double mag() {
        return Math.sqrt(x * x + y * y);
    }

    public void normalize() {
        double m = mag();
        if (m != 0) {
            div(m);
        }
    }

    public double direction() {
        return Math.atan2(y, x);
    }
}
