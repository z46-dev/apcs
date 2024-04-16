package Chapter13;

public class ObjectsInsertionSort {
    public static void main(String[] args) {
        String[] strings = new String[10];

        for (int i = 0; i < strings.length; i++) {
            String s = "";

            for (int j = 0; j < 5; j++) {
                s += (char) (Math.random() * 26 + 65);
            }

            strings[i] = s;
        }

        System.out.println("Before sorting: ");
        print(strings);

        Sorter.insertionSort(strings);

        System.out.println("After sorting: ");
        print(strings);
    }

    public static void print(String[] strings) {
        for (int i = 0; i < strings.length; i++) {
            System.out.print(strings[i]);

            if (i < strings.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.println();
    }
}
