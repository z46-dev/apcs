import javax.swing.JFrame;
import javax.swing.JLabel;

public class Roll {
    final public static int DIE_SIZE = 64;
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

        final JLabel label = new JLabel("Click anywhere to roll dice");
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label.setAlignmentY(JLabel.TOP_ALIGNMENT);
        frame.getContentPane().add(label);

        // Show
        frame.setVisible(true);

        frame.getContentPane().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                g = frame.getGraphics();

                drawDie(128, 256, DIE_SIZE, (int)(Math.random() * 9) + 1);
                drawDie(256 + 128, 256, DIE_SIZE, (int)(Math.random() * 9) + 1);
            }
        });
    }

    public static void drawDie(int x, int y, int size, int sides) {
        final float backHue = (float)Math.random();
        final float spotHue = backHue > .5 ? backHue - .5f : backHue + .5f;
        
        g.setColor(java.awt.Color.getHSBColor(backHue, 1f, .75f));
        g.fillRoundRect(x - size / 2, y - size / 2, size, size, 10, 10);

        final int radius = size / 4;
        final java.awt.Color fill = java.awt.Color.getHSBColor(spotHue, 1f, .75f);
        switch (sides) {
            case 1:
                __circle(x, y, radius, fill);
                break;
            case 2:
                __circle(x - size / 3, y - size / 3, radius, fill);
                __circle(x + size / 3, y + size / 3, radius, fill);
                break;
            case 3:
                __circle(x, y, radius, fill);
                __circle(x - size / 3, y - size / 3, radius, fill);
                __circle(x + size / 3, y + size / 3, radius, fill);
                break;
            case 4:
                __circle(x - size / 3, y - size / 3, radius, fill);
                __circle(x - size / 3, y + size / 3, radius, fill);
                __circle(x + size / 3, y - size / 3, radius, fill);
                __circle(x + size / 3, y + size / 3, radius, fill);
                break;
            case 5:
                __circle(x, y, radius, fill);
                __circle(x - size / 3, y - size / 3, radius, fill);
                __circle(x - size / 3, y + size / 3, radius, fill);
                __circle(x + size / 3, y - size / 3, radius, fill);
                __circle(x + size / 3, y + size / 3, radius, fill);
                break;
            case 6:
                __circle(x - size / 3, y - size / 3, radius, fill);
                __circle(x - size / 3, y + size / 3, radius, fill);
                __circle(x + size / 3, y - size / 3, radius, fill);
                __circle(x + size / 3, y + size / 3, radius, fill);
                __circle(x + size / 3, y, radius, fill);
                __circle(x - size / 3, y, radius, fill);
                break;
            case 7:
                __circle(x, y, radius, fill);
                __circle(x - size / 3, y - size / 3, radius, fill);
                __circle(x - size / 3, y + size / 3, radius, fill);
                __circle(x + size / 3, y - size / 3, radius, fill);
                __circle(x + size / 3, y + size / 3, radius, fill);
                __circle(x + size / 3, y, radius, fill);
                __circle(x - size / 3, y, radius, fill);
                break;
            case 8:
                __circle(x - size / 3, y - size / 3, radius, fill);
                __circle(x - size / 3, y + size / 3, radius, fill);
                __circle(x + size / 3, y - size / 3, radius, fill);
                __circle(x + size / 3, y + size / 3, radius, fill);
                __circle(x + size / 3, y, radius, fill);
                __circle(x - size / 3, y, radius, fill);
                __circle(x, y + size / 3, radius, fill);
                __circle(x, y - size / 3, radius, fill);
                break;
            case 9:
                __circle(x, y, radius, fill);
                __circle(x - size / 3, y - size / 3, radius, fill);
                __circle(x - size / 3, y + size / 3, radius, fill);
                __circle(x + size / 3, y - size / 3, radius, fill);
                __circle(x + size / 3, y + size / 3, radius, fill);
                __circle(x + size / 3, y, radius, fill);
                __circle(x - size / 3, y, radius, fill);
                __circle(x, y + size / 3, radius, fill);
                __circle(x, y - size / 3, radius, fill);
                break;
            default:
                break;
        }
    }

    public static void __circle(int x, int y, int radius, java.awt.Color fill) {
        g.setColor(fill);
        g.fillArc(x - radius / 2, y - radius / 2, radius, radius, 0, 360);
    }

}
