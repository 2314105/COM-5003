package ui;

import data.Module;
import data.ModuleManager;
import models.DirectL6EntryCalculation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class DirectL6EntryPanel extends JPanel {
    private ModuleManager moduleManager;
    private JTextArea resultTextArea;
    private DirectL6EntryCalculation calculator;

    public DirectL6EntryPanel() {
        moduleManager = new ModuleManager();
        calculator = new DirectL6EntryCalculation();
        setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        leftPanel.add(new JLabel("Module"), gbc);

        gbc.gridx = 1;
        leftPanel.add(new JLabel("Credits"), gbc);

        gbc.gridx = 2;
        leftPanel.add(new JLabel("Mark"), gbc);

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
                        updateResultTextArea(creditsFields, markFields);
                    }
                }
            });

            leftPanel.add(moduleField, gbc);

            gbc.gridx = 1;
            JTextField creditsField = new JTextField();
            creditsField.setPreferredSize(fieldSize);
            creditsField.setEditable(false);
            creditsFields[i - 1] = creditsField;
            leftPanel.add(creditsField, gbc);

            gbc.gridx = 2;
            JTextField markField = new JTextField();
            markField.setPreferredSize(fieldSize);
            markFields[i - 1] = markField;
            markField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    updateResultTextArea(creditsFields, markFields);
                }
            });
            leftPanel.add(markField, gbc);
        }

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 3;
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> clearFields(moduleFields, creditsFields, markFields));
        leftPanel.add(clearButton, gbc);

        leftPanel.setPreferredSize(new Dimension(400, 400));

        resultTextArea = new JTextArea(5, 30);
        resultTextArea.setEditable(false);
        resultTextArea.setLineWrap(true);
        resultTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);

        add(leftPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    private void updateResultTextArea(JTextField[] creditsFields, JTextField[] markFields) {
        ArrayList<Double> marks = new ArrayList<>();
        ArrayList<Integer> credits = new ArrayList<>();

        try {
            for (int i = 0; i < creditsFields.length; i++) {
                String creditText = creditsFields[i].getText();
                String markText = markFields[i].getText();
                if (!creditText.isEmpty() && !markText.isEmpty()) {
                    credits.add(Integer.parseInt(creditText));
                    marks.add(Double.parseDouble(markText));
                }
            }

            double averageMark = calculator.calculateWeightedAverage(credits, marks);
            String classification = calculator.getClassification(averageMark);
            String finalClassification = calculator.markProfiling(credits, marks, classification);

            resultTextArea.setText("Weighted Average Mark: " + averageMark + "\n" +
                    "Initial Classification: " + classification + "\n" +
                    "Final Classification: " + finalClassification);
        } catch (NumberFormatException e) {
            resultTextArea.setText("Please enter valid credits and marks.");
        }
    }

    private void clearFields(JTextField[] moduleFields, JTextField[] creditsFields, JTextField[] markFields) {
        for (JTextField field : moduleFields) field.setText("");
        for (JTextField field : creditsFields) field.setText("");
        for (JTextField field : markFields) field.setText("");
        resultTextArea.setText("");
    }
}