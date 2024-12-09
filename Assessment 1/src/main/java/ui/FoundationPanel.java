package ui;

import javax.swing.*;
import java.awt.*;

public class FoundationPanel extends JPanel {

    public FoundationPanel() {
        // Panel setup
        setLayout(new BorderLayout());

        // Example content
        add(new JLabel("Foundation content goes here"), BorderLayout.CENTER);
    }
}