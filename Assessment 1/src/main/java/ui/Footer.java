package ui;

import javax.swing.*;
import java.awt.*;

public class Footer extends JPanel {
    private JTextArea resultsDisplay; // Text area for showing results

    public Footer(Table inputFieldsPanel) {
        // Use BorderLayout for organizing footer components
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Add padding around the footer panel
        setBorder(BorderFactory.createEmptyBorder(5, 15, 30, 15));

        // Panel for the results display (left side)
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBackground(Color.WHITE);
        displayPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding inside the display panel

        resultsDisplay = new JTextArea(5, 20); // Set rows and columns for initial size
        resultsDisplay.setEditable(false);
        resultsDisplay.setFont(new Font("Arial", Font.PLAIN, 14));
        resultsDisplay.setBackground(Color.LIGHT_GRAY); // Light gray background
        resultsDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add a black border

        // Wrap results display in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(resultsDisplay);
        scrollPane.setPreferredSize(new Dimension(450, 125)); // Set fixed size for display panel
        displayPanel.add(scrollPane, BorderLayout.CENTER);

        // Add the display panel to the west side of the footer
        add(displayPanel, BorderLayout.WEST);

        // Panel for the clear button (right side)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding inside the button panel

        JButton clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Arial", Font.BOLD, 16));
        clearButton.setPreferredSize(new Dimension(160, 160)); // Square size for the button
        clearButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change cursor to hand on hover
        clearButton.addActionListener(e -> {
            // Clear all text fields in InputFieldsPanel
            Component[] components = inputFieldsPanel.getComponents();
            for (Component component : components) {
                if (component instanceof JTextField) {
                    ((JTextField) component).setText("");
                }
            }
            // Clear the results display area
            resultsDisplay.setText("");
        });

        // Add the button to the button panel
        buttonPanel.add(clearButton);

        // Add the button panel to the east side of the footer
        add(buttonPanel, BorderLayout.EAST);
    }

    // Method to update results display
    public void updateResults(String text) {
        resultsDisplay.setText(text);
    }
}