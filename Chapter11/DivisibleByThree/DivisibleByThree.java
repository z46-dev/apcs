package DivisibleByThree;

import javax.swing.BorderFactory;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DivisibleByThree {
    public static void runFrame() {
        // Setup
        JFrame.setDefaultLookAndFeelDecorated(true);
        final JFrame frame = new JFrame("DivisibleByThree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add content
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        final JLabel riddle = new JLabel("Why did the chicken cross the road?");
        riddle.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        final JLabel answer = new JLabel("To get to the other side!");
        answer.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        answer.setVisible(false);

        final JButton showAnswer = new JButton("Show Answer");
        showAnswer.setAlignmentX(JButton.RIGHT_ALIGNMENT);
        showAnswer.addActionListener(e -> {
            answer.setVisible(true);
            showAnswer.setEnabled(false);
        });

        panel.add(riddle);
        panel.add(answer);
        panel.add(showAnswer);

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
