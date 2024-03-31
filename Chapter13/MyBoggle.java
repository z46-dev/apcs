package Chapter13;

import java.util.Scanner;

public class MyBoggle {
    final static boolean SUPPORT_DIAGONAL = true;

    public static void main(String[] args) {
        char[][] board = generateBoard(5);
        printBoard(board);

        Scanner scanner = new Scanner(System.in);
        String inputWord;

        while (true) {
            System.out.print("Enter a word (or 0 to quit): ");
            inputWord = scanner.nextLine().toUpperCase();

            if (inputWord.equals("0")) {
                break;
            }

            System.out.println("We " + (findWord(board, inputWord) ? "found" : "didn't find") + " the word.");
        }

        scanner.close();
    }

    public static char[][] generateBoard(int size) {
        char[][] board = new char[size][size];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = (char) (Math.random() * 26 + 65);
            }
        }

        return board;
    }

    public static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }

            System.out.println();
        }
    }

    public static boolean findWord(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (findWord(board, word, visited, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean findWord(char[][] board, String word, boolean[][] visited, int i, int j, int index) {
        if (index == word.length()) {
            return true;
        }

        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length || visited[i][j]
                || board[i][j] != word.charAt(index)) {
            return false;
        }

        visited[i][j] = true;

        if (
            findWord(board, word, visited, i + 1, j, index + 1) ||
            findWord(board, word, visited, i - 1, j, index + 1) ||
            findWord(board, word, visited, i, j + 1, index + 1) ||
            findWord(board, word, visited, i, j - 1, index + 1) ||
            (
                SUPPORT_DIAGONAL && (
                    findWord(board, word, visited, i + 1, j + 1, index + 1) ||
                    findWord(board, word, visited, i - 1, j - 1, index + 1) ||
                    findWord(board, word, visited, i + 1, j - 1, index + 1) ||
                    findWord(board, word, visited, i - 1, j + 1, index + 1)
                )
            )
        ) {
            return true;
        }

        visited[i][j] = false;

        return false;
    }
}
