package ui;

import javax.swing.*;
import java.awt.*;
import model.DatabaseManager;

/*
This class represents a dynamic input table for module information.
It includes fields for entering module codes, displaying corresponding credits,
and entering marks for each module. The table dynamically updates credits
based on the entered module code.
*/
public class Table extends JPanel {

    // Constants for shared configuration
    private static final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 16);
    private static final int TITLE_PADDING = 10;
    private static final Insets DEFAULT_INSETS = new Insets(5, 10, 5, 10);
    private static final Dimension MODULE_FIELD_SIZE = new Dimension(150, 30);
    private static final Dimension CREDITS_MARKS_FIELD_SIZE = new Dimension(100, 30);

    private JTextField[] moduleFields; // Fields for module codes
    private JTextField[] creditFields; // Fields for module credits

    /*
    Constructs the table with the specified number of rows.
    Each row contains fields for module codes, credits, and marks.
    */
    public Table(int rowCount) {
        initializePanelProperties();
        GridBagConstraints gridConstraints = createBaseConstraints();

        initializeTitles(gridConstraints);
        initializeInputFields(rowCount, gridConstraints);
    }

    /*
    Sets up the general properties of the panel, such as layout, background color, and padding.
    */
    private void initializePanelProperties() {
        setLayout(new GridBagLayout());
        setBackground(BACKGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder(TITLE_PADDING, TITLE_PADDING, TITLE_PADDING, TITLE_PADDING));
    }

    /*
    Creates the base GridBagConstraints object for consistent layout configuration.
    */
    private GridBagConstraints createBaseConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = DEFAULT_INSETS;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        return gbc;
    }

    /*
    Adds column titles ("Modules", "Credits", "Marks") at the top of the table.
    */
    private void initializeTitles(GridBagConstraints gridConstraints) {
        String[] titles = {"Modules", "Credits", "Marks"};
        for (int i = 0; i < titles.length; i++) {
            gridConstraints.gridx = i;
            JLabel titleLabel = new JLabel(titles[i], SwingConstants.CENTER);
            titleLabel.setFont(TITLE_FONT);
            add(titleLabel, gridConstraints);
        }
    }

    /*
    Initializes the input fields for each row, including module codes, credits, and marks.
    Adds real-time updating of credits based on the entered module code.
    */
    private void initializeInputFields(int rowCount, GridBagConstraints gridConstraints) {
        moduleFields = new JTextField[rowCount];
        creditFields = new JTextField[rowCount];

        for (int i = 0; i < rowCount; i++) {
            gridConstraints.gridy = i + 1;

            // Create and add the module code field
            moduleFields[i] = createTextField(MODULE_FIELD_SIZE);
            int index = i;

            // Add a DocumentListener for real-time updates
            moduleFields[index].getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(javax.swing.event.DocumentEvent e) {
                    updateCredits(index); // Trigger when text is inserted
                }

                @Override
                public void removeUpdate(javax.swing.event.DocumentEvent e) {
                    updateCredits(index); // Trigger when text is removed
                }

                @Override
                public void changedUpdate(javax.swing.event.DocumentEvent e) {
                    updateCredits(index); // Trigger on style changes (rare)
                }
            });

            add(moduleFields[i], updateConstraints(gridConstraints, 0));

            // Create and add the credits field (read-only)
            creditFields[i] = createTextField(CREDITS_MARKS_FIELD_SIZE);
            add(creditFields[i], updateConstraints(gridConstraints, 1));

            // Create and add the marks field (user input)
            add(createTextField(CREDITS_MARKS_FIELD_SIZE), updateConstraints(gridConstraints, 2));
        }
    }

    /*
    Creates a JTextField with the specified preferred size.
    */
    private JTextField createTextField(Dimension preferredSize) {
        JTextField textField = new JTextField();
        textField.setPreferredSize(preferredSize);
        return textField;
    }

    /*
    Updates the GridBagConstraints object with the specified column index.
    */
    private GridBagConstraints updateConstraints(GridBagConstraints gridConstraints, int gridx) {
        GridBagConstraints updatedConstraints = (GridBagConstraints) gridConstraints.clone();
        updatedConstraints.gridx = gridx;
        return updatedConstraints;
    }

    /*
    Updates the credits field based on the module code entered.
    Looks up the credits in the database using the DatabaseManager.
    */
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
            creditFields[rowIndex].setText(""); // Clear credits if no module code is entered
        }
    }
}
