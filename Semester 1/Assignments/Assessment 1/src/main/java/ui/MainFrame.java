package ui;

import javax.swing.*;
import java.awt.*;

/*
This class defines the main application frame for the Grade Calculator.
It includes the layout, navigation bar, banner, and a main panel for dynamic content.
*/
public class MainFrame extends JFrame {

    private static final int FRAME_WIDTH = 1200; // Width of the main application window
    private static final int FRAME_HEIGHT = 800; // Height of the main application window
    private static final int NAVBAR_WIDTH = 155; // Width of the navigation bar
    private static final int BANNER_HEIGHT = 75; // Height of the banner

    private JPanel mainPanel; // The panel for dynamic content
    private JLabel banner;    // The banner displaying the current section title

    /*
    Constructor to initialize the main frame with its components.
    Sets the title, size, layout, and basic configurations.
    */
    public MainFrame() {
        setTitle("Grade Calculator");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Set the application icon and background color
        setIconImage(new ImageIcon(getClass().getResource("/logo.jpg")).getImage());
        getContentPane().setBackground(Color.decode("#E6D1D8"));
        setLayout(null);

        // Initialize components
        initializeNavbar();
        initializeBanner();
        initializeMainPanel();

        // Display the initial content
        changeMainPanel(new SignInPanel());
        setVisible(true);
    }

    /*
    Initializes the navigation bar and adds it to the frame.
    */
    private void initializeNavbar() {
        Navbar navbar = new Navbar(this);
        navbar.setBounds(0, 0, NAVBAR_WIDTH, FRAME_HEIGHT);
        add(navbar);
    }

    /*
    Initializes the banner, sets its properties, and adds it to the frame.
    The banner displays the current section title.
    */
    private void initializeBanner() {
        banner = new JLabel("Sign In", SwingConstants.CENTER);
        banner.setFont(new Font("Arial", Font.BOLD, 34));
        banner.setOpaque(true);
        banner.setBackground(Color.decode("#391E22"));
        banner.setForeground(Color.WHITE);
        banner.setBounds(NAVBAR_WIDTH, 0, FRAME_WIDTH - NAVBAR_WIDTH, BANNER_HEIGHT);
        add(banner);
    }

    /*
    Initializes the main panel, which is used for displaying dynamic content.
    Sets its layout and adds it to the frame.
    */
    private void initializeMainPanel() {
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(NAVBAR_WIDTH, BANNER_HEIGHT, FRAME_WIDTH - NAVBAR_WIDTH, FRAME_HEIGHT - BANNER_HEIGHT);
        add(mainPanel);
    }

    /*
    Replaces the current content in the main panel with the specified JPanel.
    Clears any existing content and refreshes the panel.
    */
    public void changeMainPanel(JPanel newPanel) {
        mainPanel.removeAll();
        mainPanel.add(newPanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    /*
    Updates the text displayed in the banner to reflect the current section.
    */
    public void updateBanner(String text) {
        banner.setText(text);
    }
}
