package ui;

import model.*;
import model.Module;
import model.ValidationManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/*
This class represents the panel for managing Direct Level 6 entry modules.
It provides functionality for data entry, validation, and calculating results for direct Level 6 students.
*/
public class DirectL6Panel extends JPanel {

    private final Table inputFieldsPanel;            // Table for entering module data
    private final Footer footer;                    // Footer for displaying results and controls
    private final DirectLevel6Calculator calculator; // Calculator for processing Level 6 results
    private final ValidationManager validationManager; // Manager for validating module data
    private final ModuleDataExtractor moduleDataExtractor; // Extractor for retrieving module data from the table

    /*
    Constructs the panel for Direct Level 6 students.
    Includes a table for input, a footer for controls, and logic for validation and calculation.
    */
    public DirectL6Panel() {
        this.calculator = new DirectLevel6Calculator(new AverageCalculator(), new MarkClassifier());
        this.validationManager = new ValidationManager(new Validator());
        this.moduleDataExtractor = new ModuleDataExtractor();

        setLayout(new BorderLayout());

        // Create the middle panel to hold the input table
        JPanel middlePanel = new JPanel(new GridBagLayout());
        middlePanel.setBackground(Color.LIGHT_GRAY);
        middlePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Initialize the table for module data
        inputFieldsPanel = new Table(7);
        middlePanel.add(inputFieldsPanel);

        // Initialize the footer for displaying results and controls
        footer = new Footer(inputFieldsPanel);
        add(middlePanel, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);

        // Add functionality to the "Calculate" button in the footer
        footer.getCalculateButton().addActionListener(e -> processAndDisplayResults());
    }

    /*
    Processes the module data entered in the table, validates it, and calculates results.
    Displays an error message or the final classification in the footer.
    */
    private void processAndDisplayResults() {
        // Extract module data from the input table
        List<Module> modules = moduleDataExtractor.extractModulesFromTable(inputFieldsPanel);

        // Check if module data is empty
        if (modules.isEmpty()) {
            footer.updateResults("Error: No valid modules provided.");
            return;
        }

        // Validate the extracted module data
        if (!validationManager.validateModules(modules, 6, footer::updateResults)) {
            return; // Stop processing if validation fails
        }

        // Calculate the final classification for the student
        String finalResults = calculator.calculateFinalClassification(modules);

        // Update the footer with the calculated results
        footer.updateResults(finalResults);
    }
}
