package Chapter13;

import java.util.ArrayList;

public class Sorter {
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void selectionSort(Comparable[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            Comparable temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    public static void selectionSort(ArrayList<Double> arr) {
        for (int i = 0; i < arr.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.size(); j++) {
                if (arr.get(j) < arr.get(minIndex)) {
                    minIndex = j;
                }
            }
            double temp = arr.get(minIndex);
            arr.set(minIndex, arr.get(i));
            arr.set(i, temp);
        }
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int currentElement = arr[i];
            int k;
            for (k = i - 1; k >= 0 && arr[k] > currentElement; k--) {
                arr[k + 1] = arr[k];
            }
            arr[k + 1] = currentElement;
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void insertionSort(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Comparable currentElement = arr[i];
            int k;
            for (k = i - 1; k >= 0 && arr[k].compareTo(currentElement) > 0; k--) {
                arr[k + 1] = arr[k];
            }
            arr[k + 1] = currentElement;
        }
    }

    public static void merge(int[] arr, int start, int mid, int end) {
        int[] temp = new int[arr.length];
        int i = start;
        int j = mid + 1;
        int k = start;

        while (i <= mid && j <= end) {
            if (arr[i] < arr[j]) {
                temp[k] = arr[i];
                i++;
            } else {
                temp[k] = arr[j];
                j++;
            }
            k++;
        }

        while (i <= mid) {
            temp[k] = arr[i];
            i++;
            k++;
        }

        while (j <= end) {
            temp[k] = arr[j];
            j++;
            k++;
        }

        for (k = start; k <= end; k++) {
            arr[k] = temp[k];
        }
    }

    public static void bogoSort(int[] arr) {
        while (!isSorted(arr)) {
            shuffle(arr);
        }
    }

    public static void shuffle(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int randomIndex = (int) (Math.random() * arr.length);
            int temp = arr[i];
            arr[i] = arr[randomIndex];
            arr[randomIndex] = temp;
        }
    }

    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
