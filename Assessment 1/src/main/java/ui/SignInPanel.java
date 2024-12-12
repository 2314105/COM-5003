package ui;

import javax.swing.*;
import java.awt.*;

public class SignInPanel extends JPanel {

    public SignInPanel() {
        // Set the layout of the panel to BorderLayout
        setLayout(new BorderLayout());

        // Create the middle section (3/6)
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridBagLayout());
        middlePanel.setBackground(Color.LIGHT_GRAY);

        // Load the logo image
        ImageIcon image = new ImageIcon(getClass().getResource("/LeedsTrinity.png"));
        JLabel logoLabel = new JLabel(image);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLabel.setPreferredSize(new Dimension(500, 250));

        // Create the sign-in form inside the middle section
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 1, 10, 10));
        formPanel.setPreferredSize(new Dimension(500, 200));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPanel.setBackground(Color.white);

        // Username field
        JTextField usernameField = new JTextField();
        usernameField.setBorder(BorderFactory.createTitledBorder("Username"));
        formPanel.add(usernameField);

        // Password field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));
        formPanel.add(passwordField);

        // Sign In button
        JButton signInButton = new JButton("Sign In");
        formPanel.add(signInButton);

        // Forgotten Password link
        JLabel forgottenPasswordLabel = new JLabel("Forgotten Password?");
        forgottenPasswordLabel.setForeground(Color.BLUE);
        forgottenPasswordLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        forgottenPasswordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(forgottenPasswordLabel);

        // Add the logo label above the form panel in the middle section
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        middlePanel.add(logoLabel, gbc); // Add logo above the form

        // Add the form panel below the logo
        gbc.gridy = 1;
        middlePanel.add(formPanel, gbc);

        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Right-align the button in the footer
        footerPanel.setBackground(Color.lightGray);
        footerPanel.setPreferredSize(new Dimension(0, 175));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add the middle section to the center of the SignInPanel
        add(footerPanel, BorderLayout.SOUTH);
        add(middlePanel, BorderLayout.CENTER);
    }
}