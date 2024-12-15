package ui;

import model.DirectLevel6Calculator;
import model.Module;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DirectL6Panel extends JPanel {

    // Constants for design
    private static final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;

    private Table inputFieldsPanel; // Input fields panel
    private Footer footer;         // Footer panel to display results

    public DirectL6Panel() {
        setLayout(new BorderLayout());
        initializeComponents(); // Initialize UI components
        setupListeners();       // Set up action listeners
        revalidate();
        repaint();
    }

    // Initializes the middle panel and major components
    private void initializeComponents() {
        JPanel middlePanel = initializeMiddlePanel(); // Create and configure a middle panel
        inputFieldsPanel = new Table(7);              // Create inputFieldsPanel dynamically
        middlePanel.add(inputFieldsPanel, getGridBagConstraints(1));

        footer = new Footer(inputFieldsPanel);        // Initialize footer
        add(middlePanel, BorderLayout.CENTER);        // Add components to respective layout positions
        add(footer, BorderLayout.SOUTH);
    }

    // Sets up the middle panel
    private JPanel initializeMiddlePanel() {
        JPanel middlePanel = new JPanel(new GridBagLayout());
        middlePanel.setBackground(BACKGROUND_COLOR);
        middlePanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        return middlePanel;
    }

    // Configures grid constraints for layout
    private GridBagConstraints getGridBagConstraints(int gridy) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = gridy;
        gbc.insets = new Insets(5, 10, 5, 10);
        return gbc;
    }

    // Configures listeners for panel-specific actions
    private void setupListeners() {
        footer.getCalculateButton().addActionListener(e -> processAndDisplayResults());
    }

    // Handle calculations and result updates
    private void processAndDisplayResults() {
        List<Module> modules = extractModulesFromFields();

        if (modules.isEmpty()) {
            footer.updateResults("Error: No valid modules provided.");
            return;
        }

        // Direct calculations via DirectLevel6Calculator
        String finalResults = DirectLevel6Calculator.calculateFinalClassification(modules);

        updateFooterResults(finalResults);
    }

    // Extract valid modules from the input table
    private List<Module> extractModulesFromFields() {
        List<Module> modules = new ArrayList<>();
        Component[] components = inputFieldsPanel.getComponents();

        for (int i = 3; i < components.length; i += 3) { // Process each set of fields (module, credits, marks)
            try {
                JTextField creditsField = (JTextField) components[i + 1];
                JTextField marksField = (JTextField) components[i + 2];

                int credits = Integer.parseInt(creditsField.getText().trim());
                double marks = Double.parseDouble(marksField.getText().trim());
                modules.add(new Module("Module" + (modules.size() + 1), credits, marks));
            } catch (NumberFormatException ex) {
                footer.updateResults("Error: Ensure credits and marks fields are filled correctly.");
                return new ArrayList<>(); // Return empty, invalid modules
            }
        }
        return modules;
    }

    // Display results in the footer
    private void updateFooterResults(String finalResults) {
        footer.updateResults(finalResults);
    }
}