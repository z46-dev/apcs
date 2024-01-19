package Midterm;

import javax.swing.*;
import java.awt.*;

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
    public boolean isPit, isDragon;
}

class Player {
    public Room room;
    public boolean hasRope, hasArrow;

    public Player() {
        hasRope = false;
        hasArrow = false;
    }
}

public class Midterm {
    public static JFrame frame;
    public static Graphics2D g;
    public static Room[] rooms;
    public static Player player;

    // Get room by coordinates
    public static Room getRoom(int x, int y) {
        if (x == -1) {
            x = 9;
        } else if (x == 10) {
            x = 0;
        }

        if (y == -1) {
            y = 9;
        } else if (y == 10) {
            y = 0;
        }

        return rooms[x + y * 10];
    }

    public static void main(String[] args) {
        init();
    }

    public static void init() {
        frame = new JFrame("Midterm");
        frame.setSize(500, 650);
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

        findValidPos().item = arrow;
        findValidPos().isDragon = true;

        player = new Player();
        player.hasRope = true;
        player.room = findValidPos();

        // Wait 1s, then paint
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        g = (Graphics2D) frame.getGraphics();
        paint();

        // Bind WASD to movement
        frame.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                int x = player.room.x;
                int y = player.room.y;

                switch (evt.getKeyCode()) {
                    case java.awt.event.KeyEvent.VK_W:
                        y--;
                        break;
                    case java.awt.event.KeyEvent.VK_A:
                        x--;
                        break;
                    case java.awt.event.KeyEvent.VK_S:
                        y++;
                        break;
                    case java.awt.event.KeyEvent.VK_D:
                        x++;
                        break;
                    case java.awt.event.KeyEvent.VK_F:
                        if (player.hasArrow) {
                            player.hasArrow = false;

                            if (getRoom(x - 1, y).isDragon || getRoom(x + 1, y).isDragon || getRoom(x, y - 1).isDragon || getRoom(x, y + 1).isDragon) {
                                JOptionPane.showMessageDialog(frame, "You killed the dragon!", "You win!", JOptionPane.INFORMATION_MESSAGE);
                                System.exit(0);
                            } else {
                                JOptionPane.showMessageDialog(frame, "You missed!", "You lose!", JOptionPane.INFORMATION_MESSAGE);
                                System.exit(0);
                            }
                        }
                        break;
                }

                player.room = getRoom(x, y);

                if (player.room.item != null) {
                    switch (player.room.item.name) {
                        case "Arrow":
                            player.hasArrow = true;
                            break;
                        case "Rope":
                            player.hasRope = true;
                            break;
                    }

                    player.room.item = null;
                }

                if (player.room.isPit) {
                    if (player.hasRope) {
                        player.hasRope = false;

                        // Move the player to a nearby non-pit room
                        if (!getRoom(x - 1, y).isPit) {
                            player.room = getRoom(x - 1, y);
                        } else if (!getRoom(x + 1, y).isPit) {
                            player.room = getRoom(x + 1, y);
                        } else if (!getRoom(x, y - 1).isPit) {
                            player.room = getRoom(x, y - 1);
                        } else if (!getRoom(x, y + 1).isPit) {
                            player.room = getRoom(x, y + 1);
                        }

                        JOptionPane.showMessageDialog(frame, "You fell into a pit, but you had a rope!");
                        findValidPos().item = rope;
                    } else {
                        JOptionPane.showMessageDialog(frame, "You fell into a pit!", "You lose!", JOptionPane.INFORMATION_MESSAGE);
                        System.exit(0);
                    }
                }

                if (player.room.isDragon) {
                    JOptionPane.showMessageDialog(frame, "You were eaten by the dragon!", "You lose!", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }
                
                paint();
            }
        });
    }

    public static void placePits() {
        int pitCount = 10;
        external: for (; pitCount > 0;) {
            int x = (int) (Math.random() * 10);
            int y = (int) (Math.random() * 10);
            int pos = x + y * 10;
            if (rooms[pos].isPit) {
                continue external;
            }

            internal: for (int i = 0; i < 100; i++) {
                if (i == pos) {
                    continue internal;
                }

                double dist = Math.sqrt(Math.pow(rooms[i].x - x, 2) + Math.pow(rooms[i].y - y, 2));
                if (dist <= 2 && rooms[i].isPit) {
                    continue external;
                }
            }

            rooms[pos].isPit = true;
            pitCount--;
        }
    }

    public static Room findValidPos() {
        for (;;) {
            int x = (int) (Math.random() * 10);
            int y = (int) (Math.random() * 10);
            int pos = x + y * 10;

            if (rooms[pos].isPit || rooms[pos].item != null || rooms[pos].isDragon || (player != null && rooms[pos] == player.room)) {
                continue;
            }

            return rooms[pos];
        }
    }

    public static void paint() {
        // Clear the screen
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 500, 650);

        // // Draw the rooms & items
        // for (int i = 0; i < 100; i++) {
        //     int x = i % 10;
        //     int y = i / 10;

        //     if (rooms[i].item != null) {
        //         g.translate(x * 50 + 25, y * 50 + 25);
        //         g.scale(.25, .25);
        //         rooms[i].item.draw(g);
        //         g.scale(4, 4);
        //         g.translate(-x * 50 - 25, -y * 50 - 25);
        //     }
        // }

        // Draw the pits
        // g.setColor(Color.GRAY);
        // for (int i = 0; i < 100; i++) {
        //     int x = i % 10;
        //     int y = i / 10;
        //     if (rooms[i].isPit) {
        //         g.fillRect(x * 50, y * 50, 50, 50);
        //     }
        // }

        // Draw the dragon
        g.setColor(Color.RED);
        for (int i = 0; i < 100; i++) {
            int x = i % 10;
            int y = i / 10;
            if (rooms[i].isDragon) {
                g.fillOval(x * 50, y * 50, 50, 50);
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

        // Draw the player
        g.setColor(Color.BLUE);
        g.fillRect(player.room.x * 50, player.room.y * 50, 50, 50);

        // If player is near pit, display warning
        if (getRoom(player.room.x - 1, player.room.y).isPit || getRoom(player.room.x + 1, player.room.y).isPit || getRoom(player.room.x, player.room.y - 1).isPit || getRoom(player.room.x, player.room.y + 1).isPit) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.PLAIN, 15));
            
            FontMetrics metrics = g.getFontMetrics(g.getFont());
            int y = 550 - metrics.getHeight() / 2 + metrics.getAscent();

            g.drawString("PIT NEARBY", 5, y);
        }

        if (player.hasArrow) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.PLAIN, 15));
            
            FontMetrics metrics = g.getFontMetrics(g.getFont());
            int y = 575 - metrics.getHeight() / 2 + metrics.getAscent();

            g.drawString("You have an arrow, press F to fire!", 5, y);
        } else if (
            (getRoom(player.room.x - 1, player.room.y).item != null && getRoom(player.room.x - 1, player.room.y).item.name.equals("Arrow")) ||
            (getRoom(player.room.x + 1, player.room.y).item != null && getRoom(player.room.x + 1, player.room.y).item.name.equals("Arrow")) ||
            (getRoom(player.room.x, player.room.y - 1).item != null && getRoom(player.room.x, player.room.y - 1).item.name.equals("Arrow")) ||
            (getRoom(player.room.x, player.room.y + 1).item != null && getRoom(player.room.x, player.room.y + 1).item.name.equals("Arrow"))
        ) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.PLAIN, 15));
            
            FontMetrics metrics = g.getFontMetrics(g.getFont());
            int y = 575 - metrics.getHeight() / 2 + metrics.getAscent();

            g.drawString("There's an arrow somewhere around!", 5, y);
        }

        if (player.hasRope) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.PLAIN, 15));
            
            FontMetrics metrics = g.getFontMetrics(g.getFont());
            int y = 600 - metrics.getHeight() / 2 + metrics.getAscent();

            g.drawString("You have a rope!", 5, y);
        } else if (
            (getRoom(player.room.x - 1, player.room.y).item != null && getRoom(player.room.x - 1, player.room.y).item.name.equals("Rope")) ||
            (getRoom(player.room.x + 1, player.room.y).item != null && getRoom(player.room.x + 1, player.room.y).item.name.equals("Rope")) ||
            (getRoom(player.room.x, player.room.y - 1).item != null && getRoom(player.room.x, player.room.y - 1).item.name.equals("Rope")) ||
            (getRoom(player.room.x, player.room.y + 1).item != null && getRoom(player.room.x, player.room.y + 1).item.name.equals("Rope"))
        ) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.PLAIN, 15));
            
            FontMetrics metrics = g.getFontMetrics(g.getFont());
            int y = 600 - metrics.getHeight() / 2 + metrics.getAscent();

            g.drawString("There's a rope somewhere around!", 5, y);
        }

        // If player is near dragon, display warning
        if (getRoom(player.room.x - 1, player.room.y).isDragon || getRoom(player.room.x + 1, player.room.y).isDragon || getRoom(player.room.x, player.room.y - 1).isDragon || getRoom(player.room.x, player.room.y + 1).isDragon) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.PLAIN, 15));
            
            FontMetrics metrics = g.getFontMetrics(g.getFont());
            int y = 550 - metrics.getHeight() / 2 + metrics.getAscent();

            g.drawString("DRAGON NEARBY", 200, y);
        }

        // Colour key 
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 15));

        FontMetrics metrics = g.getFontMetrics(g.getFont());
        int y = 635 - 15 - metrics.getHeight() / 2 + metrics.getAscent();

        g.drawString("Colour key:", 5, y);

        g.setColor(Color.BLUE);
        g.fillRect(100, 635 - 15, 15, 15);

        g.setColor(Color.RED);
        g.fillRect(200, 635 - 15, 15, 15);

        g.setColor(Color.GRAY);
        g.fillRect(300, 635 - 15, 15, 15);

        g.setColor(Color.BLACK);
        g.drawString("Player", 120, y);

        g.setColor(Color.RED);
        g.drawString("Dragon", 220, y);

        g.setColor(Color.GRAY);
        g.drawString("Pit    (Use WASD to move!)", 320, y);
    }
}
