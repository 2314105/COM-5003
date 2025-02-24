package ui;

import javax.swing.*;
import java.awt.*;

/*
This class defines the footer section of the application, which includes:
- A text area to display results.
- Buttons for "Calculate" and "Clear" functionality.
The footer is designed to interact with the input fields and display calculation results.
*/
public class Footer extends JPanel {
    private JTextArea resultsDisplay; // Text area for showing results
    private JButton calculateButton;  // Exposed button for triggering calculations
    private JButton clearButton;      // Exposed button for clearing fields

    /*
    Constructor to initialize the footer panel with results display and buttons.
    */
    public Footer(Table inputFieldsPanel) {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#391E22")); // Dark background color for the footer
        setBorder(BorderFactory.createEmptyBorder(5, 15, 30, 15));

        // Initialize the results display area
        resultsDisplay = new JTextArea(5, 20);
        resultsDisplay.setEditable(false); // Read-only text area
        resultsDisplay.setFont(new Font("Arial", Font.PLAIN, 20));
        resultsDisplay.setBackground(Color.white);
        resultsDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        resultsDisplay.setMargin(new Insets(10, 10, 10, 10)); // Add padding inside the text area

        // Add the results display to a panel on the left side
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBackground(Color.decode("#391E22"));
        displayPanel.add(resultsDisplay, BorderLayout.CENTER); // Add results display directly
        add(displayPanel, BorderLayout.WEST);

        // Initialize the button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.decode("#391E22"));

        // Create and add the "Calculate" button
        calculateButton = new JButton("Calculate");
        calculateButton.setFont(new Font("Arial", Font.BOLD, 20));
        calculateButton.setPreferredSize(new Dimension(160, 160));
        calculateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Hand cursor
        buttonPanel.add(calculateButton);

        // Create and add the "Clear" button
        clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Arial", Font.BOLD, 20));
        clearButton.setPreferredSize(new Dimension(160, 160));
        clearButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Hand cursor
        clearButton.addActionListener(e -> clearFields(inputFieldsPanel)); // Attach clear functionality
        buttonPanel.add(clearButton);

        // Add the button panel to the right side of the footer
        add(buttonPanel, BorderLayout.EAST);
    }

    /*
    Returns the "Calculate" button for external use.
    */
    public JButton getCalculateButton() {
        return calculateButton;
    }

    /*
    Returns the "Clear" button for external use.
    */
    public JButton getClearButton() {
        return clearButton;
    }

    /*
    Updates the text in the results display area with the specified string.
    */
    public void updateResults(String text) {
        resultsDisplay.setText(text);
    }

    /*
    Clears all input fields in the specified table and resets the results display area.
    */
    private void clearFields(Table inputFieldsPanel) {
        Component[] components = inputFieldsPanel.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText(""); // Clear text fields
            }
        }
        resultsDisplay.setText(""); // Clear results display
    }
}
