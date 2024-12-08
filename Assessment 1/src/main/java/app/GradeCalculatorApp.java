package app;

import ui.MainFrame;

import javax.swing.*;

public class GradeCalculatorApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}