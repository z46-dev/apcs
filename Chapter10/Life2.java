// PSA this is sample code, don't just copy paste it please.

import java.util.Scanner;

public class Life2 {
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    public static int[][] board = new int[WIDTH][HEIGHT];
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Input some cells you want to be alive. Give negative numbers to stop.");
        inputLiveCells();

        boolean doNextDay = true;
        while (doNextDay) {
            day();

            System.out.print("Do you want to continue? (y/n) ");
            String answer = input.next();

            if (answer.equals("n")) {
                doNextDay = false;
            }
        }
    }

    public static void inputLiveCells() {
        int x, y;

        do {
            System.out.print("x: ");
            x = input.nextInt();
            System.out.print("y: ");
            y = input.nextInt();

            if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT) {
                board[x][y] = 1;
            }
        } while (x >= 0 && y >= 0);
    }

    public static int getNeighbors(int x, int y) {
        int neighbors = 0;

        if (x > 0 && y > 0) {
            if (board[x - 1][y - 1] == 1) {
                neighbors++;
            }
        }

        // .. the rest of the neighbors go here

        return neighbors;
    }

    public static void day() {
        int[][] newBoard = new int[WIDTH][HEIGHT];

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                int neighbors = getNeighbors(x, y);

                // implement logic here
            }
        }

        board = newBoard;
        printBoard();
    }

    public static void printBoard() {
        // Print the board here
    }
}
