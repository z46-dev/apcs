package MetricConversion;

import javax.swing.BorderFactory;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MetricConversion {
    public static MetricConversionFactor[] conversions = {
        new MetricConversionFactor("Foot", "Meter", "Feet", "Meters", .3048),
        new MetricConversionFactor("Meter", "Foot", "Meters", "Feet", 1/.3048),
        new MetricConversionFactor("Inch", "Centimeter", "Inches", "Centimeters", 2.54),
        new MetricConversionFactor("Centimeter", "Inch", "Centimeters", "Inches", 1/2.54),
        new MetricConversionFactor("Gallon", "Liter", "Gallons", "Liters", 4.5461),
        new MetricConversionFactor("Liter", "Gallon", "Liters", "Gallons", 1/4.5461),
        new MetricConversionFactor("Pound", "Kilogram", "Pounds", "Kilograms", .4536),
        new MetricConversionFactor("Kilogram", "Pound", "Kilograms", "Pounds", 1/.4536)
    };

    public static void runFrame() {
        // Setup
        JFrame.setDefaultLookAndFeelDecorated(true);
        final JFrame frame = new JFrame("MetricConversion");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add content
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] names = new String[conversions.length];

        for (int i = 0; i < conversions.length; i ++) {
            names[i] = conversions[i].getName();
        }

        final JComboBox<String> choiceBox = new JComboBox<String>(names);
        choiceBox.setAlignmentX(JComboBox.LEFT_ALIGNMENT);

        final JLabel label = new JLabel("ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ");
        label.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
        label.setVisible(false);

        choiceBox.addActionListener(e -> {
            String choice = (String)choiceBox.getSelectedItem();

            label.setVisible(true);
            for (MetricConversionFactor conversion : conversions) {
                if (choice.equals(conversion.getName())) {
                    label.setText(conversion.convert(1));
                }
            }
        });

        panel.add(choiceBox);
        panel.add(label);

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
