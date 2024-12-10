package ui;

import data.Module;
import data.ModuleManager;
import models.L5AndL6ModulesCalculation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class L5AndL6ModulesPanel extends JPanel {
    private ModuleManager moduleManager;
    private JTextArea resultTextArea;
    private JTextField[][] l5Fields;
    private JTextField[][] l6Fields;

    public L5AndL6ModulesPanel() {
        moduleManager = new ModuleManager();
        setLayout(new BorderLayout());

        JPanel modulesPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        l5Fields = createEntryPanel("Level 5", modulesPanel);
        l6Fields = createEntryPanel("Level 6", modulesPanel);

        add(modulesPanel, BorderLayout.CENTER);

        resultTextArea = new JTextArea(10, 30);
        resultTextArea.setEditable(false);
        resultTextArea.setLineWrap(true);
        resultTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);

        add(scrollPane, BorderLayout.SOUTH);
    }

    private JTextField[][] createEntryPanel(String level, JPanel parentPanel) {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Module (" + level + ")"), gbc);
        gbc.gridx = 1;
        inputPanel.add(new JLabel("Credits"), gbc);
        gbc.gridx = 2;
        inputPanel.add(new JLabel("Mark"), gbc);

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
        ArrayList<Integer> allCredits = new ArrayList<>();
        ArrayList<Double> allMarks = new ArrayList<>();

        aggregateFields(l5Fields, allCredits, allMarks);
        aggregateFields(l6Fields, allCredits, allMarks);

        double totalWeightedMarks = L5AndL6ModulesCalculation.aggregateFields(allCredits, allMarks);
        int totalCredits = allCredits.stream().mapToInt(Integer::intValue).sum();

        if (totalCredits > 0) {
            double averageMark = totalWeightedMarks / totalCredits;
            String classification = L5AndL6ModulesCalculation.getClassification(averageMark);
            String finalClassification = L5AndL6ModulesCalculation.markProfiling(allCredits, allMarks, classification);
            resultTextArea.setText("Weighted Average Mark: " + averageMark + "\n" +
                    "Initial Classification: " + classification + "\n" +
                    "Final Classification: " + finalClassification);
        } else {
            resultTextArea.setText("Enter marks and credits to calculate classification.");
        }
    }

    private void aggregateFields(JTextField[][] fields, ArrayList<Integer> credits, ArrayList<Double> marks) {
        JTextField[] creditsFields = fields[1];
        JTextField[] markFields = fields[2];

        for (int i = 0; i < creditsFields.length; i++) {
            try {
                int credit = Integer.parseInt(creditsFields[i].getText().trim());
                double mark = Double.parseDouble(markFields[i].getText().trim());
                credits.add(credit);
                marks.add(mark);
            } catch (NumberFormatException ignored) {
            }
        }
    }

    private void clearFields(JTextField[] moduleFields, JTextField[] creditsFields, JTextField[] markFields) {
        for (JTextField field : moduleFields) field.setText("");
        for (JTextField field : creditsFields) field.setText("");
        for (JTextField field : markFields) field.setText("");
        resultTextArea.setText("");
    }
}