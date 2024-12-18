package ui;

import model.Level5Level6Calculator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Level5Level6Panel extends JPanel {

    private Footer footer;  // Store reference to Footer
    private Level5Level6Calculator calculator;

    public Level5Level6Panel() {
        setLayout(new BorderLayout());
        calculator = new Level5Level6Calculator(); // Initialize the calculator

        // Panel for Level 5 and Level 6 tables
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout(1, 2, 10, 10)); // 1 row, 2 columns with spacing
        middlePanel.setBackground(Color.LIGHT_GRAY);
        middlePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Level 5 Table
        Table level5Table = new Table(8); // 8 rows for Level 5
        middlePanel.add(level5Table);

        // Level 6 Table
        Table level6Table = new Table(8); // 8 rows for Level 6
        middlePanel.add(level6Table);

        // Footer panel
        footer = new Footer(level5Table); // Store reference to Footer
        add(footer, BorderLayout.SOUTH); // Add the footer to the bottom of the panel
        add(middlePanel, BorderLayout.CENTER);

        // Add Clear functionality to the panel
        footer.getClearButton().addActionListener(e -> clearTables(level5Table, level6Table));

        // Add Calculate functionality to the panel
        footer.getCalculateButton().addActionListener(e -> {
            System.out.println("Calculate button clicked!"); // Debugging line
            extractAndDisplayData(level5Table, level6Table);
        });

        // Revalidate the layout to make sure it updates properly
        revalidate();
        repaint();
    }

    // Method to clear both Level 5 and Level 6 tables
    private void clearTables(Table level5Table, Table level6Table) {
        clearTableFields(level5Table);
        clearTableFields(level6Table);
    }

    // Helper method to clear fields in a specific table
    private void clearTableFields(Table table) {
        Component[] components = table.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText(""); // Clear text fields
            }
        }
    }

    // Method to extract data from tables and calculate/display results
    private void extractAndDisplayData(Table level5Table, Table level6Table) {
        List<Double> l5Marks = new ArrayList<>();
        List<Integer> l5Credits = new ArrayList<>();
        List<Double> l6Marks = new ArrayList<>();
        List<Integer> l6Credits = new ArrayList<>();

        // Extract Level 5 data
        extractTableData(level5Table, l5Marks, l5Credits);

        // Extract Level 6 data
        extractTableData(level6Table, l6Marks, l6Credits);

        // Perform calculations and get the formatted result
        String result = calculator.calculateMethodD(l5Marks, l5Credits, l6Marks, l6Credits);

        // Display the result in the footer
        footer.updateResults(result);
    }

    // Helper method to extract data from a table and populate the marks and credits lists
    private void extractTableData(Table table, List<Double> marks, List<Integer> credits) {
        Component[] components = table.getComponents();

        for (int i = 0; i < components.length; i += 3) { // Process each row of 3 fields (Module Name, Credits, Marks)
            if (components[i + 1] instanceof JTextField creditsField && components[i + 2] instanceof JTextField marksField) {
                try {
                    String creditsText = creditsField.getText().trim();
                    String marksText = marksField.getText().trim();

                    if (!creditsText.isEmpty() && !marksText.isEmpty()) {
                        credits.add(Integer.parseInt(creditsText));
                        marks.add(Double.parseDouble(marksText));
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Invalid input: " + e.getMessage());
                    // Skip invalid rows but continue processing others
                }
            }
        }
    }
}
