package ui;

import data.Module;
import data.ModuleManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class DirectL6EntryPanel extends JPanel {
    private ModuleManager moduleManager;
    private JTextArea resultTextArea;

    public DirectL6EntryPanel() {
        // Initialize ModuleManager to access the modules
        moduleManager = new ModuleManager();

        // Panel setup with BorderLayout for the main layout
        setLayout(new BorderLayout());

        // Left panel for Modules, Credits, Marks using GridBagLayout to center content
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for flexible control
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding between elements

        // Column headers for Module, Credits, and Mark at the top
        gbc.gridx = 0;
        gbc.gridy = 0;
        leftPanel.add(new JLabel("Module"), gbc);

        gbc.gridx = 1;
        leftPanel.add(new JLabel("Credits"), gbc);

        gbc.gridx = 2;
        leftPanel.add(new JLabel("Mark"), gbc);

        // Add 7 rows of input fields for Modules, Credits, and Marks
        Dimension fieldSize = new Dimension(100, 30); // Set width to 100px and height to 30px
        JTextField[] moduleFields = new JTextField[7];
        JTextField[] creditsFields = new JTextField[7]; // Array to store the credits fields for each row
        JTextField[] markFields = new JTextField[7]; // Array to store the mark fields for each row

        for (int i = 1; i <= 7; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            JTextField moduleField = new JTextField();
            moduleField.setPreferredSize(fieldSize);
            moduleFields[i - 1] = moduleField; // Store reference to module fields

            // Auto-capitalize module code as the user types
            final int index = i - 1; // To refer to the correct credits field in the array
            moduleField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    String text = moduleField.getText().toUpperCase(); // Convert to uppercase
                    moduleField.setText(text);

                    // Once typing is finished (or user hits enter), auto-populate credits
                    if (e.getKeyCode() == KeyEvent.VK_ENTER || text.length() == 7) { // Assuming module codes are 7 characters (e.g., "COM5003")
                        Module module = moduleManager.getModuleByCode(text);
                        if (module != null) {
                            creditsFields[index].setText(String.valueOf(module.getCredits())); // Auto-populate credits
                        } else {
                            creditsFields[index].setText(""); // Clear if no matching module is found
                        }
                        updateResultTextArea(creditsFields, markFields);
                    }
                }
            });

            leftPanel.add(moduleField, gbc);

            gbc.gridx = 1;
            JTextField creditsField = new JTextField();
            creditsField.setPreferredSize(fieldSize);
            creditsField.setEditable(false); // Make credits field non-editable
            creditsFields[i - 1] = creditsField; // Store the reference to the credits field in the array
            leftPanel.add(creditsField, gbc);

            gbc.gridx = 2;
            JTextField markField = new JTextField();
            markField.setPreferredSize(fieldSize);
            markFields[i - 1] = markField; // Store the reference to the mark field in the array
            markField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    updateResultTextArea(creditsFields, markFields);
                }
            });
            leftPanel.add(markField, gbc);
        }

        // Add "Clear" button under the columns
        gbc.gridx = 0;
        gbc.gridy = 8; // Position under the input rows
        gbc.gridwidth = 3; // Span across all three columns
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> clearFields(moduleFields, creditsFields, markFields));
        leftPanel.add(clearButton, gbc);

        // Set the preferred size for the left panel
        leftPanel.setPreferredSize(new Dimension(400, 400)); // Adjust the height as needed

        // Text area to display results at the bottom
        resultTextArea = new JTextArea(5, 30);
        resultTextArea.setEditable(false);
        resultTextArea.setLineWrap(true);
        resultTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);

        // Add components to the main panel
        add(leftPanel, BorderLayout.CENTER); // Left panel in the center
        add(scrollPane, BorderLayout.SOUTH); // Results at the bottom
    }

    private void updateResultTextArea(JTextField[] creditsFields, JTextField[] markFields) {
        ArrayList<Double> marks = new ArrayList<>();
        ArrayList<Integer> credits = new ArrayList<>();
        double totalMarks = 0;
        int totalCredits = 0;

        for (int i = 0; i < creditsFields.length; i++) {
            try {
                String creditText = creditsFields[i].getText();
                String markText = markFields[i].getText();
                if (!creditText.isEmpty() && !markText.isEmpty()) {
                    int credit = Integer.parseInt(creditText);
                    double mark = Double.parseDouble(markText);

                    credits.add(credit);
                    marks.add(mark);

                    totalMarks += mark * credit;
                    totalCredits += credit;
                }
            } catch (NumberFormatException e) {
                resultTextArea.setText("Please enter valid credits and marks.");
                return;
            }
        }

        if (totalCredits > 0) {
            double averageMark = totalMarks / totalCredits;
            String classification = getClassification(averageMark);
            String finalClassification = markProfiling(credits, marks, classification);
            resultTextArea.setText("Weighted Average Mark: " + averageMark + "\n" +
                    "Initial Classification: " + classification + "\n" +
                    "Final Classification: " + finalClassification);
        } else {
            resultTextArea.setText("Enter marks and credits to calculate classification.");
        }
    }

    private void clearFields(JTextField[] moduleFields, JTextField[] creditsFields, JTextField[] markFields) {
        // Clear all input fields
        for (JTextField field : moduleFields) {
            field.setText("");
        }
        for (JTextField field : creditsFields) {
            field.setText("");
        }
        for (JTextField field : markFields) {
            field.setText("");
        }
        // Clear the result text area
        resultTextArea.setText("");
    }

    private String getClassification(double averageMark) {
        if (averageMark >= 70) {
            return "First Class";
        } else if (averageMark >= 60) {
            return "Upper Second Class (2:1)";
        } else if (averageMark >= 50) {
            return "Lower Second Class (2:2)";
        } else {
            return "Fail";
        }
    }

    private String markProfiling(ArrayList<Integer> credits, ArrayList<Double> marks, String initialClassification) {
        int higherClassificationCredits = 0;
        int totalCredits = 0;

        for (int i = 0; i < marks.size(); i++) {
            totalCredits += credits.get(i);
            if (marks.get(i) >= 60) {
                higherClassificationCredits += credits.get(i);
            }
        }

        if ((double) higherClassificationCredits / totalCredits > 0.5) {
            if (initialClassification.equals("Upper Second Class (2:1)")) {
                return "First Class";
            } else if (initialClassification.equals("Lower Second Class (2:2)")) {
                return "Upper Second Class (2:1)";
            }
        }

        return initialClassification;
    }
}