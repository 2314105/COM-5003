package ui;

import javax.swing.*;
import java.awt.*;

public class Table extends JPanel {

    // Constants for shared configuration
    private static final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 16);
    private static final int TITLE_PADDING = 10;
    private static final Insets DEFAULT_INSETS = new Insets(5, 10, 5, 10);
    private static final Dimension MODULE_FIELD_SIZE = new Dimension(150, 30);
    private static final Dimension CREDITS_MARKS_FIELD_SIZE = new Dimension(100, 30);

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
        for (int i = 0; i < rowCount; i++) {
            gridConstraints.gridy = i + 1;

            add(createTextField(MODULE_FIELD_SIZE), updateConstraints(gridConstraints, 0));
            add(createTextField(CREDITS_MARKS_FIELD_SIZE), updateConstraints(gridConstraints, 1));
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
}