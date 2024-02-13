package Chapter13;

import java.util.ArrayList;

public class ArrayListSort {
    public static void main(String[] args) {
        ArrayList<Double> arr = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            arr.add(Math.round(Math.random() * 100 * 100.0) / 100.0);
        }

        System.out.println("Before sorting: " + arr);
        Sorter.selectionSort(arr);
        System.out.println("After sorting: " + arr);
    }
}
