package Chapter13;

public class RecursiveFactorial {
    public static void main(String[] args) {
        System.out.println("Factorial of 5: " + factorial(5));
    }

    public static long factorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }
}
