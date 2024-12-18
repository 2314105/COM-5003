package ui;

import javax.swing.*;
import java.awt.*;

public class PanelUtils {

    public void clearTableFields(Table table) {
        Component[] components = table.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            }
        }
    }
}
