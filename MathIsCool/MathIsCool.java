// Hello from GitHub!

package MathIsCool;

import java.util.Scanner;

public class MathIsCool {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Input a number and I'll tell you about the number!");
        int inputNumber = input.nextInt();

        evenOdd(inputNumber);
        prime(inputNumber);

        input.close();
    }

    public static void evenOdd(int number) {
        if (number % 2 == 0) {
            System.out.println("Your number is even!");
            return;
        }

        System.out.println("Your number is odd!");
    }

    public static void prime(int number) {
        if (number == 1) {
            System.out.println("Your number is not prime!");
            return;
        }

        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                System.out.println("Your number is not prime!");
                return;
            }
        }

        System.out.println("Your number is prime!");
    }
}
