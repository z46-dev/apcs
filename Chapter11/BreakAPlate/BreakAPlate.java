package BreakAPlate;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class BreakAPlate {
    final public static int PLATE_SIZE = 64;
    private static JFrame frame;
    private static java.awt.Graphics g;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                runFrame();
            }
        });
    }

    public static void runFrame() {
        // Setup
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("MetricConversion");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(512, 512);

        frame.setLayout(new java.awt.FlowLayout());

        final JLabel label = new JLabel("Click anywhere to break some plates! (Maybe lol)");
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label.setAlignmentY(JLabel.TOP_ALIGNMENT);
        label.setFont(new java.awt.Font("sans-serif", java.awt.Font.BOLD, 16));
        frame.getContentPane().add(label);

        final JLabel prizeText = new JLabel("You won a prize!");
        prizeText.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        prizeText.setAlignmentY(JLabel.TOP_ALIGNMENT + 28);
        prizeText.setFont(new java.awt.Font("sans-serif", java.awt.Font.BOLD, 14));
        prizeText.setVisible(false);
        frame.getContentPane().add(prizeText);

        // Show
        frame.setVisible(true);

        frame.getContentPane().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (g == null) {
                    g = frame.getGraphics();
                }


                g.setColor(java.awt.Color.decode("#505050"));
                g.fillRect(0, 96, 512, 512 - 96);

                boolean[] broken = new boolean[3];

                for (int i = 0; i < 3; i++) {
                    broken[i] = Math.random() < .5;
                }

                int brokenCount = 0;

                for (int i = 0; i < 3; i ++) {
                    int x = 120 + 96 * i;
                    int y = 256;

                    if (!broken[i]) {
                        drawNormalPlate(x, y, PLATE_SIZE);
                    } else {
                        drawBrokenPlateShards(x, y, PLATE_SIZE);
                        brokenCount ++;
                    }
                }

                if (brokenCount > 1) {
                    prizeText.setVisible(true);

                    if (brokenCount == 3) {
                        prizeText.setText("You won the answer key to the second semester!");
                    } else {
                        prizeText.setText("You won a sticker!");
                    }
                } else {
                    prizeText.setVisible(false);
                }
            }
        });
    }

    public static void drawNormalPlate(int x, int y, int radius) {
        final int innerRadius = (int)(radius * .85);

        g.setColor(java.awt.Color.decode("#DFDFDF"));
        g.fillArc(x - radius / 2, y - radius / 2, radius, radius, 0, 360);
        g.setColor(java.awt.Color.decode("#EEEEEE"));
        g.fillArc(x - innerRadius / 2, y - innerRadius / 2, innerRadius, innerRadius, 0, 360);
    }

    public static void drawBrokenPlateShards(int x, int y, int radius) {
        final int shardCount = 5 + (int)(Math.random() * 5);
        final int shardRadius = radius;

        for (int i = 0; i < shardCount; i++) {
            final int shardX = x + (int)(Math.cos(Math.PI * 2 / (double)shardCount * (double)(shardCount - i)) * (double)radius / 8.0);
            final int shardY = y + (int)(Math.sin(Math.PI * 2 / (double)shardCount * (double)(shardCount - i)) * (double)radius / 8.0);
            final int innerRadius = (int)(shardRadius * .85);

            final int startAngle = 360 / shardCount * i - 360 / shardCount / 2;
            final int endAngle = 360 / shardCount;

            g.setColor(java.awt.Color.decode("#DFDFDF"));
            g.fillArc(shardX - shardRadius / 2, shardY - shardRadius / 2, shardRadius, shardRadius, startAngle, endAngle);
            g.setColor(java.awt.Color.decode("#EEEEEE"));
            g.fillArc(shardX - innerRadius / 2, shardY - innerRadius / 2, innerRadius, innerRadius, startAngle, endAngle);
        }
    }
}
