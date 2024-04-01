package SpeedTesting;

public class SpeedTesting {
    public static void main(String[] args) {
        int n = 1000000;
        long initTime = 0,
            runTime = 0;

        // Initialize the test
        initTime = System.nanoTime();
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = i;
        }
        initTime = System.nanoTime() - initTime;

        // Run the test
        runTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            data[i] = data[i] * 2;
        }
        runTime = System.nanoTime() - runTime;

        System.out.println("Init time: " + parseTime(initTime) + "ms");
        System.out.println("Test time: " + parseTime(runTime) + "ms");
    }

    public static String parseTime(long time) {
        return String.format("%.2f", time / 1000000.0);
    }
}