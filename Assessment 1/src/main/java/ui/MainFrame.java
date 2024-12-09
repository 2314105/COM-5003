package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel cardPanel;

    // Constructor for MainFrame
    public MainFrame() {
        // Set title of the frame
        setTitle("Grade Calculator");

        // Set size of the frame
        setSize(1200, 800);

        // Disable resizing of the frame
        setResizable(false);

        // Set default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set layout for the frame
        setLayout(new BorderLayout());

        // Create the navigation panel on the left
        JPanel navPanel = createNavPanel();

        // Create the card panel on the right for content switching
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        // Add different content panels to the card panel
        cardPanel.add(new SignInPanel(), "Sign in");
        cardPanel.add(new FoundationPanel(), "Foundation");
        cardPanel.add(new L5AndL6ModulesPanel(), "L5 and L6 Modules");
        cardPanel.add(new DirectL6EntryPanel(), "Direct L6 Entry");
        cardPanel.add(new MastersAwardPanel(), "Masters Award");
        cardPanel.add(new PostgraduateDiplomaPanel(), "Postgraduate Diploma");
        cardPanel.add(new PostgraduateCertificatePanel(), "Postgraduate Certificate");

        // Add the panels to the frame
        add(navPanel, BorderLayout.WEST);
        add(cardPanel, BorderLayout.CENTER);
    }

    // Create the navigation panel
    private JPanel createNavPanel() {
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new GridLayout(8, 1)); // 8 options, stacked vertically

        // Define the navigation options and their card names
        String[] options = {
                "Sign in",
                "Foundation",
                "L5 and L6 Modules",
                "Direct L6 Entry",
                "Masters Award",
                "Postgraduate Diploma",
                "Postgraduate Certificate",
                "Exit"
        };

        // Create buttons for each option
        for (String option : options) {
            JButton button = new JButton(option);
            if ("Exit".equals(option)) {
                button.addActionListener(e -> System.exit(0)); // Exit the application
            } else if ("L5 and L6 Modules".equals(option) || "Direct L6 Entry".equals(option)) {
                button.addActionListener(e -> cardLayout.show(cardPanel, option)); // Switch content on the right
            } else {
                button.setEnabled(false); // Disable other buttons to grey them out
            }
            navPanel.add(button);
        }

        // Set panel size and other visual adjustments if needed
        navPanel.setPreferredSize(new Dimension(200, 800));
        return navPanel;
    }

    public static void main(String[] args) {
        // Create and display the MainFrame
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}