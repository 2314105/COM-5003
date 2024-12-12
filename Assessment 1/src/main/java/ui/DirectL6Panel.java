package ui;

import javax.swing.*;
import java.awt.*;

public class DirectL6Panel extends JPanel {

    public DirectL6Panel() {
        setLayout(new BorderLayout());

        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridBagLayout());
        middlePanel.setBackground(Color.LIGHT_GRAY);
        middlePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 10, 5, 10);

        // Use Table with 7 rows
        Table inputFieldsPanel = new Table(7); // Dynamic row count
        gbc.gridy = 1;
        middlePanel.add(inputFieldsPanel, gbc);

        // Create Footer panel using the new Footer class
        Footer footer = new Footer(inputFieldsPanel);
        add(footer, BorderLayout.SOUTH);  // Add the footer to the bottom of the panel
        add(middlePanel, BorderLayout.CENTER);

        // Revalidate the layout to make sure it updates properly
        revalidate();
        repaint();
    }
}