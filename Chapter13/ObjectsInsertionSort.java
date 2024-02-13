package Chapter13;

public class ObjectsInsertionSort {
    public static void main(String[] args) {
        Circle[] circles = new Circle[10];

        for (int i = 0; i < circles.length; i++) {
            circles[i] = new Circle(Math.round(Math.random() * 100 * 100.0) / 100.0);
        }

        System.out.println("Before sorting: ");
        print(circles);

        Sorter.insertionSort(circles);

        System.out.println("After sorting: ");
        print(circles);
    }

    public static void print(Circle[] circles) {
        for (int i = 0; i < circles.length; i++) {
            System.out.print(circles[i].getRadius());

            if (i < circles.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.println();
    }
}
