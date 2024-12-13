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

        // Results display
        resultsDisplay = new JTextArea(5, 20);
        resultsDisplay.setEditable(false);
        resultsDisplay.setFont(new Font("Arial", Font.PLAIN, 14));
        resultsDisplay.setBackground(Color.white);
        resultsDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JScrollPane scrollPane = new JScrollPane(resultsDisplay);
        scrollPane.setPreferredSize(new Dimension(450, 125));
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBackground(Color.decode("#391E22"));
        displayPanel.add(scrollPane, BorderLayout.CENTER);
        add(displayPanel, BorderLayout.WEST);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.decode("#391E22"));

        // Calculate Button
        calculateButton = new JButton("Calculate");
        calculateButton.setFont(new Font("Arial", Font.BOLD, 16));
        calculateButton.setPreferredSize(new Dimension(160, 160));
        calculateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Set cursor
        buttonPanel.add(calculateButton);

        // Clear Button
        clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Arial", Font.BOLD, 16));
        clearButton.setPreferredSize(new Dimension(160, 160));
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