import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Name {
    public static void runFrame() {
        // Setup
        JFrame.setDefaultLookAndFeelDecorated(true);
        final JFrame frame = new JFrame("Name");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add content
        final JPanel panel = new JPanel();
        final JLabel label = new JLabel();
        label.setText("My name is Evan Parker!");
        panel.add(label);

        final JButton button = new JButton("Hide Name");
        button.addActionListener(e -> {
            if (label.isVisible()) {
                label.setVisible(false);
                button.setText("Show Name");
            } else {
                label.setVisible(true);
                button.setText("Hide Name");
            }
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