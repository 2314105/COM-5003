package ui;

import javax.swing.*;
import java.awt.*;

public class Footer extends JPanel {
    private JTextArea resultsDisplay; // Text area for showing results
    private JButton calculateButton;  // Expose calculate button for panel-specific logic
    private JButton clearButton;      // Expose clear button if needed

    public Footer(Table inputFieldsPanel) {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#391E22"));
        setBorder(BorderFactory.createEmptyBorder(5, 15, 30, 15));

        // Results display (non-scrollable version)
        resultsDisplay = new JTextArea(5, 20);
        resultsDisplay.setEditable(false);
        resultsDisplay.setFont(new Font("Arial", Font.PLAIN, 20));
        resultsDisplay.setBackground(Color.white);
        resultsDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Add padding inside the JTextArea
        resultsDisplay.setMargin(new Insets(10, 10, 10, 10)); // Top, left, bottom, right padding

        // Directly add the JTextArea to the displayPanel without a JScrollPane
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBackground(Color.decode("#391E22"));
        displayPanel.add(resultsDisplay, BorderLayout.CENTER); // No scroll pane
        add(displayPanel, BorderLayout.WEST);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.decode("#391E22"));

        // Calculate Button
        calculateButton = new JButton("Calculate");
        calculateButton.setFont(new Font("Arial", Font.BOLD, 20));
        calculateButton.setPreferredSize(new Dimension(160, 160));
        calculateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Set cursor for calculate button
        buttonPanel.add(calculateButton);

        // Clear Button
        clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Arial", Font.BOLD, 20));
        clearButton.setPreferredSize(new Dimension(160, 160));
        clearButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Set cursor for clear button
        clearButton.addActionListener(e -> clearFields(inputFieldsPanel));
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.EAST);
    }

    public JButton getCalculateButton() {
        return calculateButton;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public void updateResults(String text) {
        resultsDisplay.setText(text);
    }

    private void clearFields(Table inputFieldsPanel) {
        Component[] components = inputFieldsPanel.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            }
        }
        resultsDisplay.setText("");
    }
}