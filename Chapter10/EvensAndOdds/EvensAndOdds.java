package EvensAndOdds;

import java.util.ArrayList;

public class EvensAndOdds {
    final public static int COUNT = 32;
    final public static int MIN = 1; // Inclusive
    final public static int MAX = 100; // Inclusive

    public static void main(String[] args) {
        ArrayList<Integer> evens = new ArrayList<Integer>();
        ArrayList<Integer> odds = new ArrayList<Integer>();
        ArrayList<Integer> primes = new ArrayList<Integer>();

        int[] numbers = generate();

        for (int number : numbers) {
            if (isEven(number)) {
                evens.add(number);
            } else {
                odds.add(number);
            }

            if (isPrime(number)) {
                primes.add(number);
            }
        }

        System.out.println("Evens: " + stringify(evens));
        System.out.println("Odds: " + stringify(odds));
        System.out.println("Primes: " + stringify(primes));
    }

    public static int[] generate() {
        int[] output = new int[COUNT];

        for (int i = 0; i < COUNT; i ++) {
            output[i] = (int) (Math.random() * (MAX - MIN + 1)) + MIN;
        }

        return output;
    }

    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    public static boolean isPrime(int number) {
        if (number == 1) {
            return false;
        }

        for (int i = 2; i < number; i ++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static String stringify(ArrayList<Integer> arrayList) {
        String output = "";

        for (int i = 0; i < arrayList.size() - 1; i ++) {
            output += arrayList.get(i) + ", ";
        }

        output += arrayList.get(arrayList.size() - 1);

        return output;
    }
}
