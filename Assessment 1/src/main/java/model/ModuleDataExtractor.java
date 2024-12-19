package model;

import ui.Table;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/*
This class provides functionality to extract module data from the table.
It converts the user input in the table into a list of Module objects.
*/
public class ModuleDataExtractor {

    /*
    Extracts module data from the Table object and converts it into a list of Module objects.
    Skips rows with empty or invalid fields.
    */
    public List<Module> extractModulesFromTable(Table table) {
        List<Module> modules = new ArrayList<>();
        Component[] components = table.getComponents();

        // Iterate over table components, skipping the title row (first 3 components are labels)
        for (int i = 3; i < components.length; i += 3) {
            try {
                // Ensure the credits and marks fields are JTextFields
                if (components[i + 1] instanceof JTextField creditsField &&
                        components[i + 2] instanceof JTextField marksField) {

                    String creditsText = creditsField.getText().trim();
                    String marksText = marksField.getText().trim();

                    // Skip rows with empty fields
                    if (creditsText.isEmpty() || marksText.isEmpty()) {
                        continue;
                    }

                    // Parse the credits and marks, then create a new Module object
                    int credits = Integer.parseInt(creditsText);
                    double marks = Double.parseDouble(marksText);

                    modules.add(new Module("Module" + (modules.size() + 1), credits, marks));
                }
            } catch (NumberFormatException ex) {
                // Log an error message for invalid input in a row
                System.err.println("Invalid data in table row: " + ex.getMessage());
            }
        }

        // Log a warning if no valid modules were extracted
        if (modules.isEmpty()) {
            System.err.println("No valid modules extracted from table.");
        }

        return modules;
    }
}
