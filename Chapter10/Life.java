import java.util.Scanner;
import javax.swing.JFrame;

public class Life {
    private static Scanner input;
    private static boolean[] board;
    private static JFrame frame;
    private static java.awt.Graphics g;

    final private static int WIDTH = 256;
    final private static int HEIGHT = 256;
    final private static int CUBE_SIZE = 4;

    private static boolean tickBegan;

    public static void main(String[] args) {
        input = new Scanner(System.in);
        board = new boolean[WIDTH * HEIGHT];
        frame = new JFrame("Game of Life");
        tickBegan = false;

        // Randomly place cells
        for (int i = 0; i < board.length; i++) {
            board[i] = Math.random() < 0.5;
        }

        System.out.println("Welcome to the Game of Life!");
        inputLiveCells();
    }

    public static boolean get(int x, int y) {
        return board[x + y * HEIGHT];
    }

    public static void set(int x, int y, boolean value) {
        board[x + y * HEIGHT] = value;
    }

    public static int getNeighbors(int x, int y) {
        int neighbors = 0;

        if (x > 0 && y > 0) {
            if (get(x - 1, y - 1))
                neighbors++;
        }
        if (y > 0) {
            if (get(x, y - 1))
                neighbors++;
        }
        if (x < WIDTH - 1 && y > 0) {
            if (get(x + 1, y - 1))
                neighbors++;
        }
        if (x > 0) {
            if (get(x - 1, y))
                neighbors++;
        }
        if (x < WIDTH - 1) {
            if (get(x + 1, y))
                neighbors++;
        }
        if (x > 0 && y < HEIGHT - 1) {
            if (get(x - 1, y + 1))
                neighbors++;
        }
        if (y < HEIGHT - 1) {
            if (get(x, y + 1))
                neighbors++;
        }
        if (x < WIDTH - 1 && y < HEIGHT - 1) {
            if (get(x + 1, y + 1))
                neighbors++;
        }

        return neighbors;
    }

    public static void inputLiveCells() {
        frame.setSize(WIDTH * CUBE_SIZE, HEIGHT * CUBE_SIZE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setVisible(true);

        frame.getContentPane().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3) {
                    beginTickLoop();
                    return;
                }

                int x = parseMouseCoord(evt.getX(), frame.getWidth(), WIDTH);
                int y = parseMouseCoord(evt.getY(), frame.getHeight(), HEIGHT);

                set(x, y, true);
                printBoard();
            }
        });
    }

    public static int parseMouseCoord(int coord, int size, int multiplicity) {
        float coordFloat = (float) coord;
        float sizeFloat = (float) size;
        float multiplicityFloat = (float) multiplicity;

        float resultFloat = coordFloat * multiplicityFloat / sizeFloat;
        return (int) resultFloat;
    }

    public static void beginTickLoop() {
        if (tickBegan) {
            return;
        }

        tickBegan = true;
        javax.swing.Timer timer = new javax.swing.Timer(1000 / 60, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tick();
            }
        });

        timer.start();
    }

    public static void tick() {
        boolean[] newBoard = new boolean[board.length];

        for (int i = 0; i < board.length; i++) {
            int x = i % WIDTH;
            int y = i / HEIGHT;
            int neighbors = getNeighbors(x, y);

            if (get(x, y)) {
                if (neighbors == 2 || neighbors == 3) {
                    newBoard[i] = true;
                }
            } else {
                if (neighbors == 3) {
                    newBoard[i] = true;
                }
            }
        }

        board = newBoard;

        printBoard();
    }

    public static void printBoard() {
        if (g == null) {
            g = frame.getGraphics();
        }

        for (int i = 0; i < board.length; i++) {
            int x = i % WIDTH;
            int y = i / HEIGHT;

            if (get(x, y)) {
                g.setColor(java.awt.Color.getHSBColor((float)Math.max((double)x/(double)WIDTH, (double)y/(double)HEIGHT), 1.0f, 0.75f));
                g.fillRect(x * CUBE_SIZE, y * CUBE_SIZE, CUBE_SIZE, CUBE_SIZE);
            } else {
                g.clearRect(x * CUBE_SIZE, y * CUBE_SIZE, CUBE_SIZE, CUBE_SIZE);
            }
        }
    }

    public static void finish() {
        input.close();
        frame.dispose();
        System.out.println("Thanks for playing!");
        System.exit(0);
    }
}