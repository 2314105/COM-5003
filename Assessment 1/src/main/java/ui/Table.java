package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.DatabaseManager;

public class Table extends JPanel {

    // Constants for shared configuration
    private static final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 16);
    private static final int TITLE_PADDING = 10;
    private static final Insets DEFAULT_INSETS = new Insets(5, 10, 5, 10);
    private static final Dimension MODULE_FIELD_SIZE = new Dimension(150, 30);
    private static final Dimension CREDITS_MARKS_FIELD_SIZE = new Dimension(100, 30);

    // Fields to store JTextFields for modules and credits
    private JTextField[] moduleFields;
    private JTextField[] creditFields;

    public Table(int rowCount) {
        initializePanelProperties();
        GridBagConstraints gridConstraints = createBaseConstraints();

        initializeTitles(gridConstraints);
        initializeInputFields(rowCount, gridConstraints);
    }

    private void initializePanelProperties() {
        setLayout(new GridBagLayout());
        setBackground(BACKGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder(TITLE_PADDING, TITLE_PADDING, TITLE_PADDING, TITLE_PADDING));
    }

    private GridBagConstraints createBaseConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = DEFAULT_INSETS;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        return gbc;
    }

    private void initializeTitles(GridBagConstraints gridConstraints) {
        String[] titles = {"Modules", "Credits", "Marks"};
        for (int i = 0; i < titles.length; i++) {
            gridConstraints.gridx = i;
            JLabel titleLabel = new JLabel(titles[i], SwingConstants.CENTER);
            titleLabel.setFont(TITLE_FONT);
            add(titleLabel, gridConstraints);
        }
    }

    private void initializeInputFields(int rowCount, GridBagConstraints gridConstraints) {
        moduleFields = new JTextField[rowCount];
        creditFields = new JTextField[rowCount];

        for (int i = 0; i < rowCount; i++) {
            gridConstraints.gridy = i + 1;

            // Create and add module code field
            moduleFields[i] = createTextField(MODULE_FIELD_SIZE);
            int index = i;

            // Add a DocumentListener for real-time updates
            moduleFields[index].getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(javax.swing.event.DocumentEvent e) {
                    updateCredits(index); // Trigger on text insert
                }

                @Override
                public void removeUpdate(javax.swing.event.DocumentEvent e) {
                    updateCredits(index); // Trigger on text removal
                }

                @Override
                public void changedUpdate(javax.swing.event.DocumentEvent e) {
                    updateCredits(index); // Trigger on style changes (rare for plain text)
                }
            });

            add(moduleFields[i], updateConstraints(gridConstraints, 0));

            // Create and add credits field (read-only)
            creditFields[i] = createTextField(CREDITS_MARKS_FIELD_SIZE);
            add(creditFields[i], updateConstraints(gridConstraints, 1));

            // Create and add marks field (the user will fill it out)
            add(createTextField(CREDITS_MARKS_FIELD_SIZE), updateConstraints(gridConstraints, 2));
        }
    }

    private JTextField createTextField(Dimension preferredSize) {
        JTextField textField = new JTextField();
        textField.setPreferredSize(preferredSize);
        return textField;
    }

    private GridBagConstraints updateConstraints(GridBagConstraints gridConstraints, int gridx) {
        GridBagConstraints updatedConstraints = (GridBagConstraints) gridConstraints.clone();
        updatedConstraints.gridx = gridx;
        return updatedConstraints;
    }

    // Method to update credits based on the module code entered
    private void updateCredits(int rowIndex) {
        String moduleCode = moduleFields[rowIndex].getText().trim();
        if (!moduleCode.isEmpty()) {
            int credits = DatabaseManager.getCreditsForModule(moduleCode);
            if (credits != -1) {
                creditFields[rowIndex].setText(String.valueOf(credits));
            } else {
                creditFields[rowIndex].setText("Not found");
            }
        } else {
            creditFields[rowIndex].setText(""); // Clear credits if no module code
        }
    }
}