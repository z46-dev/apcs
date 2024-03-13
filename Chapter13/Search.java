package Chapter13;

public class Search {
    public static int binarySearch(int[] list, int start, int end, int key) {
        if (end >= start) {
            int mid = start + (end - start) / 2;
            if (list[mid] == key) {
                return mid;
            }
            if (list[mid] > key) {
                return binarySearch(list, start, mid - 1, key);
            }
            return binarySearch(list, mid + 1, end, key);
        }

        return -1;
    }

    public static int linearSearch(int[] list, int key) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == key) {
                return i;
            }
        }

        return -1;
    }
}
