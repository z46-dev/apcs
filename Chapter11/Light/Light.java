import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Light {
    private static boolean[] board;
    private static JFrame frame;
    private static Graphics g;
    private static MouseListener mouseListener = new MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            int x = parseMouseCoord(evt.getX(), frame.getWidth(), WIDTH);
            int y = parseMouseCoord(evt.getY(), frame.getHeight(), HEIGHT);

            set(x, y, !get(x, y));
            set(x - 1, y, !get(x - 1, y));
            set(x + 1, y, !get(x + 1, y));
            set(x, y - 1, !get(x, y - 1));
            set(x, y + 1, !get(x, y + 1));
            tick();
        }
    };
    
    private static KeyListener keyListener = new KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
                frame.dispose();
                System.exit(0);
            }

            // If it's a number key
            if (evt.getKeyCode() >= java.awt.event.KeyEvent.VK_0 && evt.getKeyCode() <= java.awt.event.KeyEvent.VK_9) {
                int number = evt.getKeyCode() - java.awt.event.KeyEvent.VK_0;
                
                WIDTH = number;
                HEIGHT = number;

                board = new boolean[WIDTH * HEIGHT];
            }
        }
    };

    private static int WIDTH = 7;
    private static int HEIGHT = 7;
    final private static int CUBE_SIZE = 64;

    public static void main(String[] args) {
        board = new boolean[WIDTH * HEIGHT];
        frame = new JFrame("Game of Light");

        System.out.println("Welcome to the Game of Light!");

        init();
    }

    public static boolean get(int x, int y) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return false;
        }

        return board[x + y * HEIGHT];
    }

    public static void set(int x, int y, boolean value) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return;
        }

        board[x + y * HEIGHT] = value;
    }

    public static void init() {
        frame.setSize(WIDTH * CUBE_SIZE, HEIGHT * CUBE_SIZE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setVisible(true);

        frame.getContentPane().addMouseListener(mouseListener);
        frame.getContentPane().addKeyListener(null);

        tick();
    }

    public static int parseMouseCoord(int coord, int size, int multiplicity) {
        float coordFloat = (float) coord;
        float sizeFloat = (float) size;
        float multiplicityFloat = (float) multiplicity;

        float resultFloat = coordFloat * multiplicityFloat / sizeFloat;
        return (int) resultFloat;
    }

    public static void tick() {
        boolean allLiving = true;

        for (int i = 0; i < board.length; i++) {
            if (!get(i % WIDTH, i / HEIGHT)) {
                allLiving = false;
            }
        }

        paint();

        if (allLiving) {
            frame.removeMouseListener(mouseListener);
            finish();
        }
    }

    public static void paint() {
        if (g == null) {
            g = frame.getGraphics();
        }

        for (int i = 0; i < board.length; i++) {
            int x = i % WIDTH;
            int y = i / HEIGHT;

            g.setColor(get(x, y) ? Color.YELLOW : Color.BLACK);
            g.fillRect(x * CUBE_SIZE, y * CUBE_SIZE, CUBE_SIZE, CUBE_SIZE);
        }
    }

    public static void finish() {
        g.clearRect(0, 0, frame.getWidth(), frame.getHeight());

        // Make text align to center
        g.setColor(Color.BLACK);
        g.setFont(new Font("Sans-Serif", Font.BOLD, CUBE_SIZE / WIDTH));
        FontMetrics metrics = g.getFontMetrics(g.getFont());

        int x = (frame.getWidth() - metrics.stringWidth("You win!")) / 2;
        int y = ((frame.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();

        g.drawString("You win!", x, y);
        
        g.setFont(new Font("Sans-Serif", Font.BOLD, CUBE_SIZE / WIDTH / 2));
        metrics = g.getFontMetrics(g.getFont());
        x = (frame.getWidth() - metrics.stringWidth("Click anywhere to exit.")) / 2;
        y = ((frame.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent() + CUBE_SIZE / WIDTH;

        g.drawString("Click anywhere to exit.", x, y);

        frame.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                frame.dispose();
                System.exit(0);
            }
        });
    }
}