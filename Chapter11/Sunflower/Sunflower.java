import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Sunflower {
    public static void runFrame() {
        // Setup
        JFrame.setDefaultLookAndFeelDecorated(true);
        final JFrame frame = new JFrame("Sunflower");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add content
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        final JLabel label = new JLabel();
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label.setText("Sunflower");

        final JButton showLatin = new JButton("Latin");
        showLatin.setAlignmentX(JButton.CENTER_ALIGNMENT);
        showLatin.addActionListener(e -> {
            label.setText("Helianthus");
        });

        final JButton showEnglish = new JButton("English");
        showEnglish.setAlignmentX(JButton.CENTER_ALIGNMENT);
        showEnglish.addActionListener(e -> {
            label.setText("Sunflower");
        });

        panel.add(label);
        panel.add(showLatin);
        panel.add(showEnglish);

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