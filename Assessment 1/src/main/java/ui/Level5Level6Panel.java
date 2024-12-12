package ui;

import javax.swing.*;
import java.awt.*;

public class Level5Level6Panel extends JPanel {

    public Level5Level6Panel() {
        setLayout(new BorderLayout());

        // Panel for Level 5 and Level 6 tables
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout(1, 2, 10, 10)); // 1 row, 2 columns with spacing

        middlePanel.setBackground(Color.LIGHT_GRAY);
        middlePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Level 5 Table
        Table level5Table = new Table(8); // 8 rows for Level 5
        middlePanel.add(level5Table);

        // Level 6 Table
        Table level6Table = new Table(8); // 8 rows for Level 6
        middlePanel.add(level6Table);

        // Create Footer panel using the new Footer class
        Footer footer = new Footer(level5Table); // Pass the Level 5 table (or could pass any other)
        add(footer, BorderLayout.SOUTH);  // Add the footer to the bottom of the panel
        add(middlePanel, BorderLayout.CENTER);

        // Revalidate the layout to make sure it updates properly
        revalidate();
        repaint();
    }
}