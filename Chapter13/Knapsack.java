package Chapter13;

public class Knapsack {
    public static void main(String[] args) {
        int[] weights = new int[6];
        int sum = 0;

        for (int i = 0; i < weights.length; i++) {
            weights[i] = (int) (Math.random() * 31) + 1;
            sum += weights[i];
        }

        int goal = (int) (Math.random() * sum) + 1;

        System.out.println("Weights: ");
        for (int i = 0; i < weights.length; i++) {
            System.out.print(weights[i]);

            if (i < weights.length - 1) {
                System.out.print(", ");
                continue;
            }

            System.out.println();
        }

        System.out.println("Goal: " + goal);

        System.out.println(fillKnapsack(weights, goal));
        System.out.println(fillKnapsack(weights, goal, 0));
    }

    // Non-Recursive solution
    public static boolean fillKnapsack(int[] weights, int goal) {
        boolean[] possible = new boolean[goal + 1];
        possible[0] = true;

        for (int i = 0; i < weights.length; i++) {
            for (int j = goal; j >= weights[i]; j--) {
                if (possible[j - weights[i]]) {
                    possible[j] = true;
                }
            }
        }

        return possible[goal];
    }

    // Recursive solution
    public static boolean fillKnapsack(int[] weights, int goal, int index) {
        if (goal == 0) {
            return true;
        }

        if (goal < 0 || index >= weights.length) {
            return false;
        }

        return fillKnapsack(weights, goal - weights[index], index + 1) || fillKnapsack(weights, goal, index + 1);
    }
}
