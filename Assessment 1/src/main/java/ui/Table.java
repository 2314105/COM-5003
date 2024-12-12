package ui;

import javax.swing.*;
import java.awt.*;

public class Table extends JPanel {

    public Table(int rowCount) {
        setLayout(new GridBagLayout()); // Using GridBagLayout for better control
        setBackground(Color.LIGHT_GRAY);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Create the title labels
        JLabel moduleTitle = new JLabel("Modules", SwingConstants.CENTER);
        moduleTitle.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel creditsTitle = new JLabel("Credits", SwingConstants.CENTER);
        creditsTitle.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel marksTitle = new JLabel("Marks", SwingConstants.CENTER);
        marksTitle.setFont(new Font("Arial", Font.BOLD, 16));

        // Add titles to the panel with GridBagConstraints
        gbc.gridy = 0;
        add(moduleTitle, gbc);
        gbc.gridx = 1;
        add(creditsTitle, gbc);
        gbc.gridx = 2;
        add(marksTitle, gbc);

        // Create the input fields based on rowCount
        for (int i = 0; i < rowCount; i++) {
            gbc.gridy = i + 1;
            gbc.gridx = 0;
            JTextField moduleField = new JTextField();
            moduleField.setPreferredSize(new Dimension(150, 30));
            add(moduleField, gbc);

            gbc.gridx = 1;
            JTextField creditsField = new JTextField();
            creditsField.setPreferredSize(new Dimension(100, 30));
            add(creditsField, gbc);

            gbc.gridx = 2;
            JTextField marksField = new JTextField();
            marksField.setPreferredSize(new Dimension(100, 30));
            add(marksField, gbc);
        }
    }
}