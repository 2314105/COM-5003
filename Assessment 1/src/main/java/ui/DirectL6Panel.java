package ui;

import model.DirectLevel6Calculator;
import model.Module;
import model.ModuleDataExtractor;
import model.ValidationService;
import model.Validator;
import model.AverageCalculator;
import model.MarkClassifier;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DirectL6Panel extends JPanel {

    private final Table inputFieldsPanel;
    private final Footer footer;
    private final DirectLevel6Calculator calculator;
    private final ValidationService validationService;
    private final ModuleDataExtractor moduleDataExtractor;

    public DirectL6Panel() {
        this.calculator = new DirectLevel6Calculator(new AverageCalculator(), new MarkClassifier());
        this.validationService = new ValidationService(new Validator());
        this.moduleDataExtractor = new ModuleDataExtractor();

        setLayout(new BorderLayout());

        JPanel middlePanel = new JPanel(new GridBagLayout());
        middlePanel.setBackground(Color.LIGHT_GRAY);
        middlePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        inputFieldsPanel = new Table(7);
        middlePanel.add(inputFieldsPanel);

        footer = new Footer(inputFieldsPanel);
        add(middlePanel, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);

        footer.getCalculateButton().addActionListener(e -> processAndDisplayResults());
    }

    private void processAndDisplayResults() {
        List<Module> modules = moduleDataExtractor.extractModulesFromTable(inputFieldsPanel);

        if (modules.isEmpty()) {
            footer.updateResults("Error: No valid modules provided.");
            return;
        }

        if (!validationService.validateModules(modules, 6, footer::updateResults)) {
            return;
        }

        String finalResults = calculator.calculateFinalClassification(modules);
        footer.updateResults(finalResults);
    }
}
