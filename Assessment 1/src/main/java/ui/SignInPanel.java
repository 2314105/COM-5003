package ui;

import javax.swing.*;
import java.awt.*;

public class SignInPanel extends JPanel {

    public SignInPanel() {
        // Panel setup
        setLayout(new BorderLayout());

        // Example content
        add(new JLabel("Welcome to the Sign In page"), BorderLayout.CENTER);
    }
}