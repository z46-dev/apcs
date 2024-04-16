import javax.swing.BorderFactory;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Riddle {
    public static void runFrame() {
        // Setup
        JFrame.setDefaultLookAndFeelDecorated(true);
        final JFrame frame = new JFrame("Riddle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add content
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        final JLabel prompt = new JLabel("Enter a number:");
        prompt.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        final JTextField input = new JTextField(null);
        input.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
        
        final JLabel displayResult = new JLabel("It's not divisible by 3."); // Longer one so that it by default is big enough
        displayResult.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
        displayResult.setVisible(false);

        final JButton checkInput = new JButton("Check Input");
        checkInput.setAlignmentX(JButton.LEFT_ALIGNMENT);
        checkInput.addActionListener(e -> {
            double inputNumber = Double.parseDouble(input.getText());

            double correctDivision = inputNumber / 3.0;
            int intDivision = (int)inputNumber / 3;

            if (correctDivision == (double)intDivision) {
                displayResult.setText("It's divisible by 3.");
            } else {
                displayResult.setText("It's not divisible by 3.");
            }

            displayResult.setVisible(true);
        });

        panel.add(prompt);
        panel.add(input);
        panel.add(checkInput);
        panel.add(displayResult);

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
