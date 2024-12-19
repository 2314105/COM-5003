package ui;

import javax.swing.*;
import java.awt.*;

/*
This utility class provides helper methods for managing UI components in the application.
It includes methods for operations like clearing fields in a table.
*/
public class PanelUtils {

    /*
    Clears all text fields in the specified table.
    Iterates through all components in the table and resets the text of any JTextField to an empty string.
    */
    public void clearTableFields(Table table) {
        Component[] components = table.getComponents(); // Get all components in the table
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText(""); // Clear the text field
            }
        }
    }
}
