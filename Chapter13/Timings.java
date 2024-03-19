package Chapter13;

public class Timings {
    public static void main(String[] args) {
        int[][] tests = new int[100][];

        for (int i = 0; i < 100; i++) {
            tests[i] = generateRandomArray(1000);
        }

        long insertionSortTotal = 0,
                mergeSortTotal = 0;

        for (int i = 0; i < tests.length; i++) {
            insertionSortTotal += insertionSortTime(tests[i]);
            mergeSortTotal += mergeSortTime(tests[i]);
        }

        System.out.println("Insertion sort average time: " + insertionSortTotal / tests.length + ", " + insertionSortTotal / tests.length / 1000000.0 + "ms");
        System.out.println("Merge sort average time: " + mergeSortTotal / tests.length + ", " + mergeSortTotal / tests.length / 1000000.0 + "ms");
    }

    public static int[] generateRandomArray(int size) {
        int[] array = new int[size];

        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 100);
        }

        return array;
    }

    public static long insertionSortTime(int[] arr) {
        long startTime = System.nanoTime();
        Sorter.insertionSort(arr);
        long endTime = System.nanoTime();

        return endTime - startTime;
    }

    public static long mergeSortTime(int[] arr) {
        long startTime = System.nanoTime();
        Sorter.merge(arr, 0, arr.length / 2, arr.length - 1);
        long endTime = System.nanoTime();

        return endTime - startTime;
    }
}
