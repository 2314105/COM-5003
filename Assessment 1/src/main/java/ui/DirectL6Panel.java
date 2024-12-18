package ui;

import model.DirectLevel6Calculator;
import model.Module;
import model.AverageCalculator;
import model.MarkClassifier;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

    // Constants for design
    public class DirectL6Panel extends JPanel {

        private static final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;

        private Table inputFieldsPanel;
        private Footer footer;
        private final DirectLevel6Calculator calculator;

        public DirectL6Panel() {
            this.calculator = new DirectLevel6Calculator(new AverageCalculator(), new MarkClassifier());
            setLayout(new BorderLayout());
            initializeComponents();
            setupListeners();
            revalidate();
            repaint();
        }

        private void initializeComponents() {
            JPanel middlePanel = initializeMiddlePanel();
            inputFieldsPanel = new Table(7);
            middlePanel.add(inputFieldsPanel, getGridBagConstraints(1));

            footer = new Footer(inputFieldsPanel);
            add(middlePanel, BorderLayout.CENTER);
            add(footer, BorderLayout.SOUTH);
        }

        private JPanel initializeMiddlePanel() {
            JPanel middlePanel = new JPanel(new GridBagLayout());
            middlePanel.setBackground(BACKGROUND_COLOR);
            middlePanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
            return middlePanel;
        }

        private GridBagConstraints getGridBagConstraints(int gridy) {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = gridy;
            gbc.insets = new Insets(5, 10, 5, 10);
            return gbc;
        }

        private void setupListeners() {
            footer.getCalculateButton().addActionListener(e -> processAndDisplayResults());
        }

        private void processAndDisplayResults() {
            List<Module> modules = extractModulesFromFields();

            if (modules.isEmpty()) {
                footer.updateResults("Error: No valid modules provided.");
                return;
            }

            String finalResults = calculator.calculateFinalClassification(modules);
            updateFooterResults(finalResults);
        }

        private List<Module> extractModulesFromFields() {
            List<Module> modules = new ArrayList<>();
            Component[] components = inputFieldsPanel.getComponents();

            for (int i = 3; i < components.length; i += 3) {
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

        private void updateFooterResults(String finalResults) {
            footer.updateResults(finalResults);
        }
    }
