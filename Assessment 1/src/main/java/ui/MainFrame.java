package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel mainPanel;  // This will be the main content panel
    private Banner banner;     // Replace JLabel with Banner

    public MainFrame() {
        // Set the title, size, and default close operation for the frame
        setTitle("Grade Calculator");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Load the logo image from resources
        ImageIcon image = new ImageIcon(getClass().getResource("/logo.jpg"));
        setIconImage(image.getImage());
        getContentPane().setBackground(Color.decode("#E6D1D8"));
        setLayout(null); // Set layout to null for absolute positioning

        // Create the Navbar and add it to the frame with absolute position
        Navbar navbar = new Navbar(this);  // Pass the MainFrame instance
        navbar.setPreferredSize(new Dimension(150, 800)); // Set navbar height to match the frame height
        navbar.setBounds(0, 0, 150, getHeight()); // Position navbar at (0,0) with width 150 and full height
        add(navbar);

        // Create the banner (top section of the frame) using the Banner class
        banner = new Banner("Sign In");
        banner.setBounds(150, 0, 1050, 50); // Place banner to the right of navbar, and at the top of the frame
        add(banner);

        // Create a main panel for the content area (center part of the frame)
        mainPanel = new JPanel(new BorderLayout()); // Use BorderLayout for proper resizing
        mainPanel.setBackground(Color.WHITE);  // Set a white background for the main content
        mainPanel.setBounds(150, 50, 1050, 750); // Position main panel below the banner
        add(mainPanel);

        // Initially show the SignInPanel
        SignInPanel signInPanel = new SignInPanel();
        changeMainPanel(signInPanel);

        // Make the frame visible
        setVisible(true);
    }

    // Method to change the content in the main panel
    public void changeMainPanel(JPanel newPanel) {
        mainPanel.removeAll();  // Remove current content
        mainPanel.add(newPanel, BorderLayout.CENTER); // Add new panel and ensure it fills the space
        mainPanel.revalidate();  // Revalidate the layout
        mainPanel.repaint();     // Repaint the panel
    }

    // Method to update the banner text
    public void updateBanner(String text) {
        banner.updateText(text); // Update the banner text through the Banner class
    }
}