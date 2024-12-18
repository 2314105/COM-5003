package ui;

import model.Level5Level6Calculator;
import model.Module;
import model.ModuleDataExtractor;
import model.ValidationService;
import model.Validator;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Level5Level6Panel extends JPanel {

    private final Footer footer;
    private final Level5Level6Calculator calculator;
    private final ValidationService validationService;
    private final ModuleDataExtractor moduleDataExtractor;
    private final PanelUtils panelUtils;

    public Level5Level6Panel() {
        this.calculator = new Level5Level6Calculator();
        this.validationService = new ValidationService(new Validator());
        this.moduleDataExtractor = new ModuleDataExtractor();
        this.panelUtils = new PanelUtils();

        setLayout(new BorderLayout());

        // Panel for Level 5 and Level 6 tables
        JPanel middlePanel = new JPanel(new GridLayout(1, 2, 10, 10));
        middlePanel.setBackground(Color.LIGHT_GRAY);
        middlePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        Table level5Table = new Table(8);
        middlePanel.add(level5Table);

        Table level6Table = new Table(8);
        middlePanel.add(level6Table);

        footer = new Footer(level5Table);
        add(middlePanel, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);

        footer.getClearButton().addActionListener(e -> {
            panelUtils.clearTableFields(level5Table);
            panelUtils.clearTableFields(level6Table);
        });

        footer.getCalculateButton().addActionListener(e -> extractAndDisplayData(level5Table, level6Table));

        revalidate();
        repaint();
    }

    private void extractAndDisplayData(Table level5Table, Table level6Table) {
        List<Module> level5Modules = moduleDataExtractor.extractModulesFromTable(level5Table);
        List<Module> level6Modules = moduleDataExtractor.extractModulesFromTable(level6Table);

        if (level5Modules.isEmpty() || level6Modules.isEmpty()) {
            footer.updateResults("Error: No valid modules provided.");
            return;
        }

        if (!validationService.validateModules(level5Modules, 5, footer::updateResults) ||
                !validationService.validateModules(level6Modules, 6, footer::updateResults)) {
            return;
        }

        String result = calculator.calculateMethodD(
                level5Modules.stream().map(Module::getMarks).toList(),
                level5Modules.stream().map(Module::getCredits).toList(),
                level6Modules.stream().map(Module::getMarks).toList(),
                level6Modules.stream().map(Module::getCredits).toList()
        );

        footer.updateResults(result);
    }
}
