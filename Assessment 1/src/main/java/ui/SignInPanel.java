package ui;

import javax.swing.*;
import java.awt.*;

/*
This class represents the Sign-In panel of the application.
It includes a logo, form fields for username and password, and controls for signing in or recovering a password.
*/
public class SignInPanel extends JPanel {

    private static final String LOGO_PATH = "/LeedsTrinity.png"; // Path to the logo image
    private static final Dimension LOGO_DIMENSION = new Dimension(500, 250); // Size of the logo
    private static final Dimension FORM_DIMENSION = new Dimension(500, 200); // Size of the form
    private static final Color BACKGROUND_COLOR = Color.LIGHT_GRAY; // Background color of the panel
    private static final Color FORM_BACKGROUND_COLOR = Color.decode("#391E22"); // Background color of the form

    /*
    Constructs the Sign-In panel with a logo, form, and footer section.
    */
    public SignInPanel() {
        setLayout(new BorderLayout()); // Use BorderLayout for organizing sections
        add(createMiddlePanel(), BorderLayout.CENTER); // Add the middle section (logo and form)
        add(createFooterPanel(), BorderLayout.SOUTH); // Add the footer section
    }

    /*
    Creates the middle panel containing the logo and form.
    */
    private JPanel createMiddlePanel() {
        JPanel middlePanel = new JPanel(new GridBagLayout()); // Center-align components
        middlePanel.setBackground(BACKGROUND_COLOR);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // Single column layout
        gbc.gridy = 0;

        // Add the logo at the top
        middlePanel.add(createLogoLabel(), gbc);

        // Add the form panel below the logo
        gbc.gridy = 1;
        middlePanel.add(createFormPanel(), gbc);

        return middlePanel;
    }

    /*
    Creates the logo section as a JLabel with an image.
    */
    private JLabel createLogoLabel() {
        ImageIcon logo = new ImageIcon(getClass().getResource(LOGO_PATH)); // Load the logo
        JLabel logoLabel = new JLabel(logo);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center-align the logo
        logoLabel.setPreferredSize(LOGO_DIMENSION);
        return logoLabel;
    }

    /*
    Creates the form panel with fields for username, password, a sign-in button, and a forgotten password link.
    */
    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridLayout(4, 1, 10, 10)); // 4 rows, 1 column
        formPanel.setPreferredSize(FORM_DIMENSION);
        formPanel.setBackground(FORM_BACKGROUND_COLOR);
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        // Add username field
        formPanel.add(createTitledTextField("Username"));

        // Add password field
        formPanel.add(createTitledPasswordField("Password"));

        // Add sign-in button
        formPanel.add(createButton("Sign In"));

        // Add forgotten password label
        formPanel.add(createForgottenPasswordLabel());

        return formPanel;
    }

    /*
    Creates a text field with a title for entering the username.
    */
    private JTextField createTitledTextField(String title) {
        JTextField textField = new JTextField();
        textField.setBorder(BorderFactory.createTitledBorder(title)); // Add a titled border
        return textField;
    }

    /*
    Creates a password field with a title for entering the password.
    */
    private JPasswordField createTitledPasswordField(String title) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBorder(BorderFactory.createTitledBorder(title)); // Add a titled border
        return passwordField;
    }

    /*
    Creates a button with the given text.
    */
    private JButton createButton(String text) {
        return new JButton(text);
    }

    /*
    Creates a clickable label for "Forgotten Password".
    */
    private JLabel createForgottenPasswordLabel() {
        JLabel label = new JLabel("Forgotten Password?");
        label.setForeground(Color.BLUE); // Highlight in blue
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Use hand cursor
        label.setHorizontalAlignment(SwingConstants.CENTER); // Center-align the label
        return label;
    }

    /*
    Creates the footer panel to move the middle panel higher.
    */
    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Align components to the right
        footerPanel.setBackground(BACKGROUND_COLOR);
        footerPanel.setPreferredSize(new Dimension(0, 175)); // Height 175, width adjusts automatically
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        return footerPanel;
    }
}
