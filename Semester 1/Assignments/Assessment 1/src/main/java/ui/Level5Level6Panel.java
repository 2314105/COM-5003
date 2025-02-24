package ui;

import model.*;
import model.Module;
import model.ValidationManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/*
This class represents the panel for managing Level 5 and Level 6 modules.
It includes functionality for data entry, validation, and displaying calculated results.
*/
public class Level5Level6Panel extends JPanel {

    private final Footer footer;                      // Footer for results display and controls
    private final Level5Level6Calculator calculator;  // Calculator for computing results
    private final ValidationManager validationManager; // for validating module data
    private final ModuleDataExtractor moduleDataExtractor; // Extractor for retrieving module data from the tables
    private final PanelUtils panelUtils;             // Utility class for managing table operations

    /*
    Constructs the panel with tables for Level 5 and Level 6 modules, a footer for controls,
    and validation/calculation logic.
    */
    public Level5Level6Panel() {
        this.calculator = new Level5Level6Calculator();
        this.validationManager = new ValidationManager(new Validator());
        this.moduleDataExtractor = new ModuleDataExtractor();
        this.panelUtils = new PanelUtils();

        setLayout(new BorderLayout());

        // Panel for Level 5 and Level 6 tables
        JPanel middlePanel = new JPanel(new GridLayout(1, 2, 10, 10));
        middlePanel.setBackground(Color.LIGHT_GRAY);
        middlePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create and add the Level 5 and Level 6 tables
        Table level5Table = new Table(8);
        middlePanel.add(level5Table);

        Table level6Table = new Table(8);
        middlePanel.add(level6Table);

        // Initialize the footer with a reference to the Level 5 table
        footer = new Footer(level5Table);
        add(middlePanel, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);

        // Add clear functionality to reset the tables
        footer.getClearButton().addActionListener(e -> {
            panelUtils.clearTableFields(level5Table);
            panelUtils.clearTableFields(level6Table);
        });

        // Add calculate functionality to process and display results
        footer.getCalculateButton().addActionListener(e -> extractAndDisplayData(level5Table, level6Table));

        revalidate();
        repaint();
    }

    /*
    Extracts data from the Level 5 and Level 6 tables, validates it, and calculates results.
    Displays an error message or the calculated result in the footer.
    */
    private void extractAndDisplayData(Table level5Table, Table level6Table) {
        // Extract module data from the tables
        List<Module> level5Modules = moduleDataExtractor.extractModulesFromTable(level5Table);
        List<Module> level6Modules = moduleDataExtractor.extractModulesFromTable(level6Table);

        // Check if data is missing from either table
        if (level5Modules.isEmpty() || level6Modules.isEmpty()) {
            footer.updateResults("Error: No valid modules provided.");
            return;
        }

        // Validate the extracted module data
        if (!validationManager.validateModules(level5Modules, 5, footer::updateResults) ||
                !validationManager.validateModules(level6Modules, 6, footer::updateResults)) {
            return; // Stop if validation fails
        }

        // Calculate the results using the extracted data
        String result = calculator.calculateMethodD(
                level5Modules.stream().map(Module::getMarks).toList(),
                level5Modules.stream().map(Module::getCredits).toList(),
                level6Modules.stream().map(Module::getMarks).toList(),
                level6Modules.stream().map(Module::getCredits).toList()
        );

        // Update the footer with the calculated results
        footer.updateResults(result);
    }
}
