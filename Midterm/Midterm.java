package Midterm;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class DrawInstructions {
    int[] XPoints;
    int[] YPoints;
    int nPoints;

    public DrawInstructions(int[] XPoints, int[] YPoints, int nPoints) {
        this.XPoints = XPoints;
        this.YPoints = YPoints;
        this.nPoints = nPoints;
    }

    // Instructions
    public static DrawInstructions arrow = new DrawInstructions(new int[] { 100, 60, 60, -50, -100, -50, -100, -50, 60, 60 }, new int[] { 0, 20, 5, 5, 30, 0, -40, -5, -5, -20 }, 10);
}

class Item {
    public int x, y;
    public String name;
    public String description;
    public int value;
    public int weight;

    public void draw(Graphics2D g) {
        switch (name) {
            case "Arrow":
                g.setColor(Color.BLACK);
                g.setStroke(new BasicStroke(10));
                g.drawPolygon(DrawInstructions.arrow.XPoints, DrawInstructions.arrow.YPoints, DrawInstructions.arrow.nPoints);
                break;
            case "Rope":
                g.setColor(Color.BLACK);
                g.setFont(new Font("Arial", Font.PLAIN, 50));
                
                FontMetrics metrics = g.getFontMetrics(g.getFont());
                int x = -metrics.stringWidth("Rope") / 2;
                int y = -metrics.getHeight() / 2 + metrics.getAscent();

                g.drawString("Rope", x, y);
                break;
        }
    }
}

class Room {
    public int x, y;
    public Item item; // why tf doesn't java support native direct pointer manipulation???
    public boolean isPit;
}

class Player {
    public Room room;
    public ArrayList<Item> inventory;

    public Player() {
        inventory = new ArrayList<>();
    }
}

public class Midterm {
    public static JFrame frame;
    public static Graphics2D g;
    public static Room[] rooms;

    // Get room by coordinates
    public static Room getRoom(int x, int y) {
        return rooms[x + y * 10];
    }

    public static void main(String[] args) {
        init();
    }

    public static void init() {
        frame = new JFrame("Midterm");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setVisible(true);

        rooms = new Room[100];

        // Initialize rooms
        for (int i = 0; i < 100; i++) {
            int x = i % 10;
            int y = i / 10;
            rooms[i] = new Room();
            rooms[i].x = x;
            rooms[i].y = y;
        }

        placePits();

        Item arrow = new Item();
        arrow.name = "Arrow";
        arrow.description = "A sharp arrow.";
        arrow.value = 5;
        arrow.weight = 1;

        Item rope = new Item();
        rope.name = "Rope";
        rope.description = "A long rope.";
        rope.value = 10;
        rope.weight = 5;

        placeItem(arrow);
        placeItem(rope);

        frame.getContentPane().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                g = (Graphics2D) frame.getGraphics();
                paint();
            }
        });
    }

    public static void placePits() {
        int pitCount = 10;
        // Position sampling
        external: for (; pitCount > 0;) {
            int x = (int) (Math.random() * 10);
            int y = (int) (Math.random() * 10);
            int pos = x + y * 10;

            // If not empty, continue
            if (rooms[pos].isPit) {
                continue external;
            }

            // Check for adjacent pits with a "radius" of 2
            internal: for (int i = 0; i < 100; i++) {
                if (i == pos) {
                    continue internal;
                }

                double dist = Math.sqrt(Math.pow(rooms[i].x - x, 2) + Math.pow(rooms[i].y - y, 2));

                if (dist <= 2 && rooms[i].isPit) {
                    continue external;
                }
            }

            // If we got here, we can place a pit
            rooms[pos].isPit = true;
            pitCount--;
        }
    }

    public static void placeItem(Item item) {
        for (;;) {
            int x = (int) (Math.random() * 10);
            int y = (int) (Math.random() * 10);
            int pos = x + y * 10;

            if (rooms[pos].isPit || rooms[pos].item != null) {
                continue;
            }

            rooms[pos].item = item;
            break;
        }
    }

    public static void paint() {
        // Clear the screen
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 500, 500);

        // Draw the rooms & items
        for (int i = 0; i < 100; i++) {
            int x = i % 10;
            int y = i / 10;

            if (rooms[i].item != null) {
                g.translate(x * 50 + 25, y * 50 + 25);
                g.scale(.25, .25);
                rooms[i].item.draw(g);
                g.scale(4, 4);
                g.translate(-x * 50 - 25, -y * 50 - 25);
            }
        }

        // Draw the pits
        g.setColor(Color.RED);
        for (int i = 0; i < 100; i++) {
            int x = i % 10;
            int y = i / 10;
            if (rooms[i].isPit) {
                g.fillRect(x * 50, y * 50, 50, 50);
            }
        }

        // Linear grid
        BasicStroke stroke = new BasicStroke(2);
        g.setStroke(stroke);
        g.setColor(Color.BLACK);
        for (int i = 0; i < 10; i++) {
            g.drawLine(i * 50, 0, i * 50, 500);
            g.drawLine(0, i * 50, 500, i * 50);
        }
    }
}
