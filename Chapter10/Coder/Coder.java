package Coder;

import java.util.Scanner;

public class Coder {
    final public static int SHIFT = 3;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Coder!");
        System.out.print("Enter a string to encode: ");
        String inputString = input.nextLine();
        System.out.println("Encoded string: " + encode(inputString));
        System.out.println("Decoded string: " + decode(encode(inputString)));
        input.close();
    }

    public static String encode(String input) {
        String output = "";

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c >= 'a' && c <= 'z') {
                c += SHIFT;

                if (c > 'z') {
                    c -= 26;
                }
            }

            if (c >= 'A' && c <= 'Z') {
                c += SHIFT;

                if (c > 'Z') {
                    c -= 26;
                }
            }

            output += c;
        }

        return output;
    }

    public static String decode(String input) {
        String output = "";
        
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c >= 'a' && c <= 'z') {
                c -= SHIFT;

                if (c < 'a') {
                    c += 26;
                }
            }

            if (c >= 'A' && c <= 'Z') {
                c -= SHIFT;

                if (c < 'A') {
                    c += 26;
                }
            }

            output += c;
        }

        return output;
    }
}
