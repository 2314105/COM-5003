package model;

import ui.Table;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ModuleDataExtractor {

    public List<Module> extractModulesFromTable(Table table) {
        List<Module> modules = new ArrayList<>();
        Component[] components = table.getComponents();

        // Skip the title row (first 3 components are labels)
        for (int i = 3; i < components.length; i += 3) {
            try {
                // Ensure we only process valid JTextFields
                if (components[i + 1] instanceof JTextField creditsField &&
                        components[i + 2] instanceof JTextField marksField) {

                    String creditsText = creditsField.getText().trim();
                    String marksText = marksField.getText().trim();

                    if (creditsText.isEmpty() || marksText.isEmpty()) {
                        continue; // Skip rows with empty fields
                    }

                    int credits = Integer.parseInt(creditsText);
                    double marks = Double.parseDouble(marksText);

                    modules.add(new Module("Module" + (modules.size() + 1), credits, marks));
                }
            } catch (NumberFormatException ex) {
                System.err.println("Invalid data in table row: " + ex.getMessage());
            }
        }

        if (modules.isEmpty()) {
            System.err.println("No valid modules extracted from table.");
        }

        return modules;
    }
}
