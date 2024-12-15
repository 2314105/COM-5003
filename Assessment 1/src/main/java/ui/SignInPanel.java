package ui;

import javax.swing.*;
import java.awt.*;

public class SignInPanel extends JPanel {

    private static final String LOGO_PATH = "/LeedsTrinity.png";
    private static final Dimension LOGO_DIMENSION = new Dimension(500, 250);
    private static final Dimension FORM_DIMENSION = new Dimension(500, 200);
    private static final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;
    private static final Color FORM_BACKGROUND_COLOR = Color.decode("#391E22");

    public SignInPanel() {
        setLayout(new BorderLayout());
        add(createMiddlePanel(), BorderLayout.CENTER);
        add(createFooterPanel(), BorderLayout.SOUTH);
    }

    private JPanel createMiddlePanel() {
        JPanel middlePanel = new JPanel(new GridBagLayout());
        middlePanel.setBackground(BACKGROUND_COLOR);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Add logo
        middlePanel.add(createLogoLabel(), gbc);

        // Add form panel
        gbc.gridy = 1;
        middlePanel.add(createFormPanel(), gbc);

        return middlePanel;
    }

    private JLabel createLogoLabel() {
        ImageIcon logo = new ImageIcon(getClass().getResource(LOGO_PATH));
        JLabel logoLabel = new JLabel(logo);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLabel.setPreferredSize(LOGO_DIMENSION);
        return logoLabel;
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        formPanel.setPreferredSize(FORM_DIMENSION);
        formPanel.setBackground(FORM_BACKGROUND_COLOR);
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Username field
        formPanel.add(createTitledTextField("Username"));

        // Password field
        formPanel.add(createTitledPasswordField("Password"));

        // Sign In button
        formPanel.add(createButton("Sign In"));

        // Forgotten Password link
        formPanel.add(createForgottenPasswordLabel());

        return formPanel;
    }

    private JTextField createTitledTextField(String title) {
        JTextField textField = new JTextField();
        textField.setBorder(BorderFactory.createTitledBorder(title));
        return textField;
    }

    private JPasswordField createTitledPasswordField(String title) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBorder(BorderFactory.createTitledBorder(title));
        return passwordField;
    }

    private JButton createButton(String text) {
        return new JButton(text);
    }

    private JLabel createForgottenPasswordLabel() {
        JLabel label = new JLabel("Forgotten Password?");
        label.setForeground(Color.BLUE);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.setBackground(BACKGROUND_COLOR);
        footerPanel.setPreferredSize(new Dimension(0, 175));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return footerPanel;
    }
}