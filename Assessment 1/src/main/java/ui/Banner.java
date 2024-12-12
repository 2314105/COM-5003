package ui;

import javax.swing.*;
import java.awt.*;

public class Banner extends JLabel {
    public Banner(String text) {
        super(text, SwingConstants.CENTER);  // Set the initial text and alignment

        // Set the banner properties
        setFont(new Font("Arial", Font.BOLD, 34));
        setOpaque(true);
        setBackground(Color.decode("#391E22"));  // Dark color for the banner
        setForeground(Color.WHITE);  // White text
        setPreferredSize(new Dimension(1050, 50)); // Fixed height for the banner
    }

    // Method to update the banner text
    public void updateText(String text) {
        setText(text);  // Change the banner's text
    }
}