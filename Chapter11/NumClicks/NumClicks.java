import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class NumClicks {
    public static int clicks = 0;
    public static void runFrame() {
        // Setup
        JFrame.setDefaultLookAndFeelDecorated(true);
        final JFrame frame = new JFrame("NumClicks");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add content
        final JPanel panel = new JPanel();

        final JButton button = new JButton("You've clicked 0 times!");
        button.addActionListener(e -> {
            clicks ++;

            button.setText("You've clicked " + clicks + " times!");
        });

        panel.add(button);

        // Show
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                runFrame();
            }
        });
    }
}