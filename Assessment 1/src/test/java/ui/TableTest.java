package ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {

    private Table tablePanel;

    @BeforeEach
    void setUp() {
        tablePanel = new Table(3); // Example row count
    }

    @Test
    void testPanelInitialization() {
        // Check if the panel layout is GridBagLayout
        assertTrue(tablePanel.getLayout() instanceof GridBagLayout);

        // Check if the background color is set to light gray
        assertEquals(Color.LIGHT_GRAY, tablePanel.getBackground());
    }

    @Test
    void testTitlesAreAdded() {
        // Check if the titles (Modules, Credits, Marks) are added
        Component[] components = tablePanel.getComponents();
        int titleCount = 0;
        for (Component component : components) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                if (label.getText().equals("Modules") || label.getText().equals("Credits") || label.getText().equals("Marks")) {
                    titleCount++;
                }
            }
        }
        // Assert that all three titles are present
        assertEquals(3, titleCount);
    }

    @Test
    void testTextFieldsAreAdded() {
        // Check if the correct number of text fields are added (rowCount * 3 for each column)
        Component[] components = tablePanel.getComponents();
        int textFieldCount = 0;
        for (Component component : components) {
            if (component instanceof JTextField) {
                textFieldCount++;
            }
        }
        // Assert that the correct number of text fields have been added for 3 rows
        assertEquals(9, textFieldCount); // 3 rows * 3 columns
    }

    @Test
    void testTextFieldSize() {
        // Check if the preferred size of text fields is set correctly
        Component[] components = tablePanel.getComponents();
        boolean correctSize = true;
        for (Component component : components) {
            if (component instanceof JTextField) {
                Dimension preferredSize = component.getPreferredSize();
                if (!(preferredSize.equals(new Dimension(150, 30)) || preferredSize.equals(new Dimension(100, 30)))) {
                    correctSize = false;
                    break;
                }
            }
        }
        assertTrue(correctSize);
    }
}