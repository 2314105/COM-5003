package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class ModuleTablePanel extends JPanel {
    public ModuleTablePanel() {
        // Set the layout of the panel
        setLayout(new BorderLayout());

        // Define the column names
        String[] columnNames = {"Module Code", "Credits", "Marks"};

        // Define the initial data (module codes, credits, and marks)
        Object[][] data = {
                {"CS101", "20", "75"},
                {"CS102", "20", "80"},
                {"CS103", "20", "70"},
                {"CS104", "20", "85"},
                {"CS105", "20", "90"},
                {"CS106", "20", "65"},
                {"CS107", "20", "88"},
                {"CS108", "20", "92"}
        };

        // Create a table with the specified data and column names
        JTable table = new JTable(data, columnNames);

        // Set the font for the table to increase the text size
        table.setFont(new Font("Arial", Font.PLAIN, 16)); // Increase font size for better readability

        // Set the font for the column names (headers)
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18)); // Make header text larger and bold
        table.getTableHeader().setForeground(Color.BLACK); // Optional: Set header text color

        // Set the alignment of text in each cell to center both horizontally and vertically
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        centerRenderer.setVerticalAlignment(SwingConstants.CENTER);  // Vertically center the text

        // Apply the center alignment renderer to all columns
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Center the column names (header text)
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setHeaderRenderer(centerRenderer); // Apply renderer to header
        }

        // Show grid lines (borders between cells) and set grid color
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK); // Set the color of the grid lines

        // Set the column widths to make them appropriate for the content
        table.getColumnModel().getColumn(0).setPreferredWidth(200); // Module Code column
        table.getColumnModel().getColumn(1).setPreferredWidth(100); // Credits column
        table.getColumnModel().getColumn(2).setPreferredWidth(100); // Marks column

        // Set the row height to make the cells larger
        table.setRowHeight(50); // Increase the row height to make the cells taller

        // Prevent column reordering
        table.getTableHeader().setReorderingAllowed(false);

        // Make the table scrollable
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Optional: Set the background color for the panel
        setBackground(Color.WHITE);
    }
}