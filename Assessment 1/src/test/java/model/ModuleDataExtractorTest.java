package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Table;

import javax.swing.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ModuleDataExtractorTest {

    private ModuleDataExtractor moduleDataExtractor;

    @BeforeEach
    void setUp() {
        moduleDataExtractor = new ModuleDataExtractor();
    }

    @Test
    void testExtractModulesFromTableWithValidData() {
        // Arrange
        Table table = new Table(2); // Create a table with 2 rows
        JTextField creditsField1 = (JTextField) table.getComponent(4); // Credits field for row 1
        JTextField marksField1 = (JTextField) table.getComponent(5);   // Marks field for row 1
        JTextField creditsField2 = (JTextField) table.getComponent(7); // Credits field for row 2
        JTextField marksField2 = (JTextField) table.getComponent(8);   // Marks field for row 2

        creditsField1.setText("30");
        marksField1.setText("85");
        creditsField2.setText("40");
        marksField2.setText("75");

        // Act
        List<Module> modules = moduleDataExtractor.extractModulesFromTable(table);

        // Assert
        assertEquals(2, modules.size());
        assertEquals("Module1", modules.get(0).getCode());
        assertEquals(30, modules.get(0).getCredits());
        assertEquals(85.0, modules.get(0).getMarks());

        assertEquals("Module2", modules.get(1).getCode());
        assertEquals(40, modules.get(1).getCredits());
        assertEquals(75.0, modules.get(1).getMarks());
    }

    @Test
    void testExtractModulesFromTableWithEmptyFields() {
        // Arrange
        Table table = new Table(1); // Create a table with 1 row
        JTextField creditsField = (JTextField) table.getComponent(4); // Credits field
        JTextField marksField = (JTextField) table.getComponent(5);   // Marks field

        creditsField.setText(""); // Empty credits field
        marksField.setText("");   // Empty marks field

        // Act
        List<Module> modules = moduleDataExtractor.extractModulesFromTable(table);

        // Assert
        assertTrue(modules.isEmpty(), "No modules should be extracted when fields are empty.");
    }

    @Test
    void testExtractModulesFromTableWithInvalidData() {
        // Arrange
        Table table = new Table(1); // Create a table with 1 row
        JTextField creditsField = (JTextField) table.getComponent(4); // Credits field
        JTextField marksField = (JTextField) table.getComponent(5);   // Marks field

        creditsField.setText("30");
        marksField.setText("InvalidMark"); // Invalid marks

        // Act
        List<Module> modules = moduleDataExtractor.extractModulesFromTable(table);

        // Assert
        assertTrue(modules.isEmpty(), "No modules should be extracted when data is invalid.");
    }

    @Test
    void testExtractModulesFromTableWithMixedValidAndInvalidData() {
        // Arrange
        Table table = new Table(2); // Create a table with 2 rows
        JTextField validCreditsField = (JTextField) table.getComponent(4); // Credits field for row 1
        JTextField validMarksField = (JTextField) table.getComponent(5);   // Marks field for row 1
        JTextField invalidCreditsField = (JTextField) table.getComponent(7); // Credits field for row 2
        JTextField invalidMarksField = (JTextField) table.getComponent(8);   // Marks field for row 2

        validCreditsField.setText("30");
        validMarksField.setText("85");
        invalidCreditsField.setText("InvalidCredits");
        invalidMarksField.setText("75");

        // Act
        List<Module> modules = moduleDataExtractor.extractModulesFromTable(table);

        // Assert
        assertEquals(1, modules.size(), "Only valid rows should be extracted.");
        assertEquals("Module1", modules.get(0).getCode());
        assertEquals(30, modules.get(0).getCredits());
        assertEquals(85.0, modules.get(0).getMarks());
    }

    @Test
    void testExtractModulesFromTableWithNoData() {
        // Arrange
        Table table = new Table(0); // Create an empty table

        // Act
        List<Module> modules = moduleDataExtractor.extractModulesFromTable(table);

        // Assert
        assertTrue(modules.isEmpty(), "No modules should be extracted when table is empty.");
    }
}
