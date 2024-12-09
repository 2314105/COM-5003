package ui;

import data.Module;
import data.ModuleManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class L5AndL6ModulesPanel extends JPanel {
    private ModuleManager moduleManager;
    private JTextArea resultTextArea; // Shared text area
    private JTextField[][] l5Fields; // Store Level 5 fields
    private JTextField[][] l6Fields; // Store Level 6 fields

    public L5AndL6ModulesPanel() {
        moduleManager = new ModuleManager();
        setLayout(new BorderLayout());

        // Panels for Level 5 and Level 6
        JPanel modulesPanel = new JPanel(new GridLayout(1, 2, 10, 0)); // Two sections side by side

        l5Fields = createEntryPanel("Level 5", modulesPanel);
        l6Fields = createEntryPanel("Level 6", modulesPanel);

        add(modulesPanel, BorderLayout.CENTER);

        // Shared result area at the bottom
        resultTextArea = new JTextArea(10, 30);
        resultTextArea.setEditable(false);
        resultTextArea.setLineWrap(true);
        resultTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);

        add(scrollPane, BorderLayout.SOUTH); // Add shared result area to the bottom
    }

    private JTextField[][] createEntryPanel(String level, JPanel parentPanel) {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add headers
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Module (" + level + ")"), gbc);

        gbc.gridx = 1;
        inputPanel.add(new JLabel("Credits"), gbc);

        gbc.gridx = 2;
        inputPanel.add(new JLabel("Mark"), gbc);

        // Fields
        Dimension fieldSize = new Dimension(100, 30);
        JTextField[] moduleFields = new JTextField[7];
        JTextField[] creditsFields = new JTextField[7];
        JTextField[] markFields = new JTextField[7];

        for (int i = 1; i <= 7; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            JTextField moduleField = new JTextField();
            moduleField.setPreferredSize(fieldSize);
            moduleFields[i - 1] = moduleField;

            // Auto-capitalize and handle credits population
            final int index = i - 1;
            moduleField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    String text = moduleField.getText().toUpperCase();
                    moduleField.setText(text);

                    if (e.getKeyCode() == KeyEvent.VK_ENTER || text.length() == 7) {
                        Module module = moduleManager.getModuleByCode(text);
                        if (module != null) {
                            creditsFields[index].setText(String.valueOf(module.getCredits()));
                        } else {
                            creditsFields[index].setText("");
                        }
                        updateResultTextArea();
                    }
                }
            });
            inputPanel.add(moduleField, gbc);

            gbc.gridx = 1;
            JTextField creditsField = new JTextField();
            creditsField.setPreferredSize(fieldSize);
            creditsField.setEditable(false);
            creditsFields[i - 1] = creditsField;
            inputPanel.add(creditsField, gbc);

            gbc.gridx = 2;
            JTextField markField = new JTextField();
            markField.setPreferredSize(fieldSize);
            markFields[i - 1] = markField;

            markField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    updateResultTextArea();
                }
            });
            inputPanel.add(markField, gbc);
        }

        // Add clear button
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 3;
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> clearFields(moduleFields, creditsFields, markFields));
        inputPanel.add(clearButton, gbc);

        panel.add(inputPanel, BorderLayout.CENTER);
        parentPanel.add(panel);

        return new JTextField[][]{moduleFields, creditsFields, markFields};
    }

    private void updateResultTextArea() {
        ArrayList<Double> allMarks = new ArrayList<>();
        ArrayList<Integer> allCredits = new ArrayList<>();
        double totalWeightedMarks = 0;
        int totalCredits = 0;

        // Aggregate Level 5 and Level 6 data
        totalWeightedMarks += aggregateFields(l5Fields[1], l5Fields[2], allCredits, allMarks);
        totalWeightedMarks += aggregateFields(l6Fields[1], l6Fields[2], allCredits, allMarks);

        for (int credit : allCredits) {
            totalCredits += credit;
        }

        if (totalCredits > 0) {
            double averageMark = totalWeightedMarks / totalCredits;
            String classification = getClassification(averageMark);
            String finalClassification = markProfiling(allCredits, allMarks, classification);
            resultTextArea.setText("Weighted Average Mark: " + averageMark + "\n" +
                    "Initial Classification: " + classification + "\n" +
                    "Final Classification: " + finalClassification);
        } else {
            resultTextArea.setText("Enter marks and credits to calculate classification.\n" +
                    "Debug Info: Total Credits = " + totalCredits +
                    ", Total Weighted Marks = " + totalWeightedMarks +
                    ", Marks List = " + allMarks.toString() +
                    ", Credits List = " + allCredits.toString());
        }
    }

    private double aggregateFields(JTextField[] creditsFields, JTextField[] markFields, ArrayList<Integer> allCredits, ArrayList<Double> allMarks) {
        double totalWeightedMarks = 0;

        for (int i = 0; i < creditsFields.length; i++) {
            try {
                String creditText = creditsFields[i].getText().trim();
                String markText = markFields[i].getText().trim();

                if (!creditText.isEmpty() && !markText.isEmpty()) {
                    int credit = Integer.parseInt(creditText);
                    double mark = Double.parseDouble(markText);

                    if (credit > 0 && mark >= 0 && mark <= 100) {
                        allCredits.add(credit);
                        allMarks.add(mark);
                        totalWeightedMarks += mark * credit;
                    }
                }
            } catch (NumberFormatException e) {
                resultTextArea.setText("Error: Invalid input. Ensure credits and marks are numeric.\n" +
                        "Field causing issue: Credits='" + creditsFields[i].getText() +
                        "', Mark='" + markFields[i].getText() + "'");
                return 0;
            }
        }
        return totalWeightedMarks;
    }

    private void clearFields(JTextField[] moduleFields, JTextField[] creditsFields, JTextField[] markFields) {
        for (JTextField field : moduleFields) {
            field.setText("");
        }
        for (JTextField field : creditsFields) {
            field.setText("");
        }
        for (JTextField field : markFields) {
            field.setText("");
        }
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