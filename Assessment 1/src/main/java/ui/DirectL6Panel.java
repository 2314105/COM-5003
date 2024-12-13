package ui;

import model.DirectLevel6Calculator;
import model.Module;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DirectL6Panel extends JPanel {
    private Table inputFieldsPanel; // Table for input fields
    private Footer footer;         // Footer for displaying results

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
        inputFieldsPanel = new Table(7); // Dynamic row count
        gbc.gridy = 1;
        middlePanel.add(inputFieldsPanel, gbc);

        // Create Footer panel and connect buttons to actions
        footer = new Footer(inputFieldsPanel);

        // Add button listeners for the specific panel
        addPanelSpecificListeners();

        add(footer, BorderLayout.SOUTH);  // Add the footer to the bottom of the panel
        add(middlePanel, BorderLayout.CENTER);

        // Revalidate the layout to make sure it updates properly
        revalidate();
        repaint();
    }

    // Add listeners for panel-specific actions
    private void addPanelSpecificListeners() {
        JButton calculateButton = footer.getCalculateButton();
        calculateButton.addActionListener(e -> calculateAndDisplayResults());
    }

    // Perform calculation and update the footer results display
    private void calculateAndDisplayResults() {
        List<Module> modules = new ArrayList<>();
        Component[] components = inputFieldsPanel.getComponents();

        // Start from the second row (index 3), and process every group of 3 fields
        for (int i = 3; i < components.length; i += 3) {
            try {
                // The first field in the group is the module field (ignored here)
                JTextField creditsField = (JTextField) components[i + 1];
                JTextField marksField = (JTextField) components[i + 2];

                int credits = Integer.parseInt(creditsField.getText().trim());
                double marks = Double.parseDouble(marksField.getText().trim());

                modules.add(new Module("Module" + (modules.size() + 1), credits, marks));
            } catch (NumberFormatException | ClassCastException ex) {
                footer.updateResults("Error: Please ensure all credits and marks fields are filled correctly.");
                return;
            }
        }

        if (modules.isEmpty()) {
            footer.updateResults("Error: No valid modules provided.");
            return;
        }

        // Calculate results using the calculator
        double average = DirectLevel6Calculator.calculateL6Average(modules);
        String classification = DirectLevel6Calculator.calculateL6Profiling(modules);

        // Update the footer with the results
        String resultText = String.format("Level 6 Weighted Average: %.1f\nClassification: %s", average, classification);
        footer.updateResults(resultText);
    }
}