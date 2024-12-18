package ui;

import model.Level5Level6Calculator;
import model.Module;
import model.Validator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Level5Level6Panel extends JPanel {

    private Footer footer;
    private Level5Level6Calculator calculator;
    private final Validator validator;

    public Level5Level6Panel() {
        this.calculator = new Level5Level6Calculator();
        this.validator = new Validator();
        setLayout(new BorderLayout());

        // Panel for Level 5 and Level 6 tables
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout(1, 2, 10, 10));
        middlePanel.setBackground(Color.LIGHT_GRAY);
        middlePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Level 5 Table
        Table level5Table = new Table(8);
        middlePanel.add(level5Table);

        // Level 6 Table
        Table level6Table = new Table(8);
        middlePanel.add(level6Table);

        footer = new Footer(level5Table);
        add(footer, BorderLayout.SOUTH);
        add(middlePanel, BorderLayout.CENTER);

        footer.getClearButton().addActionListener(e -> clearTables(level5Table, level6Table));
        footer.getCalculateButton().addActionListener(e -> extractAndDisplayData(level5Table, level6Table));

        revalidate();
        repaint();
    }

    private void clearTables(Table level5Table, Table level6Table) {
        clearTableFields(level5Table);
        clearTableFields(level6Table);
    }

    private void clearTableFields(Table table) {
        Component[] components = table.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            }
        }
    }

    private void extractAndDisplayData(Table level5Table, Table level6Table) {
        List<Module> level5Modules = extractModulesFromTable(level5Table, 5);
        List<Module> level6Modules = extractModulesFromTable(level6Table, 6);

        if (level5Modules.isEmpty() || level6Modules.isEmpty()) {
            footer.updateResults("Error: No valid modules provided.");
            return;
        }

        if (!validateModules(level5Modules, 5) || !validateModules(level6Modules, 6)) {
            return; // Stop if validation fails
        }

        String result = calculator.calculateMethodD(
                extractMarks(level5Modules), extractCredits(level5Modules),
                extractMarks(level6Modules), extractCredits(level6Modules)
        );

        footer.updateResults(result);
    }

    private List<Module> extractModulesFromTable(Table table, int level) {
        List<Module> modules = new ArrayList<>();
        Component[] components = table.getComponents();

        for (int i = 0; i < components.length; i += 3) {
            try {
                JTextField creditsField = (JTextField) components[i + 1];
                JTextField marksField = (JTextField) components[i + 2];

                String creditsText = creditsField.getText().trim();
                String marksText = marksField.getText().trim();

                if (creditsText.isEmpty() || marksText.isEmpty()) {
                    continue;
                }

                int credits = Integer.parseInt(creditsText);
                double marks = Double.parseDouble(marksText);

                modules.add(new Module("Module" + (modules.size() + 1), credits, marks));
            } catch (NumberFormatException ex) {
                footer.updateResults("Error: Ensure credits and marks fields are filled correctly.");
                return new ArrayList<>();
            }
        }

        return modules;
    }

    private boolean validateModules(List<Module> modules, int level) {
        if (!validator.validatePassMarks(modules)) {
            footer.updateResults("Error: All marks must be 40% or above to pass.");
            return false;
        }

        if (!validator.validateCategoricalMarks(modules)) {
            footer.updateResults("Error: Marks must be integers.");
            return false;
        }

        if (!validator.validateCreditsPerLevel(modules)) {
            footer.updateResults("Error: Total credits must add up to 120.");
            return false;
        }

        if (!validator.validateModuleCodes(modules, level)) {
            footer.updateResults("Error: Module codes must start with '" + level + "' for Level " + level + ".");
            return false;
        }

        return true;
    }

    private List<Double> extractMarks(List<Module> modules) {
        List<Double> marks = new ArrayList<>();
        for (Module module : modules) {
            marks.add(module.getMarks());
        }
        return marks;
    }

    private List<Integer> extractCredits(List<Module> modules) {
        List<Integer> credits = new ArrayList<>();
        for (Module module : modules) {
            credits.add(module.getCredits());
        }
        return credits;
    }
}
