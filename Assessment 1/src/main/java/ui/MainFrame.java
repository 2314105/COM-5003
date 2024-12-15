package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static final int FRAME_WIDTH = 1200;
    private static final int FRAME_HEIGHT = 800;
    private static final int NAVBAR_WIDTH = 155;
    private static final int BANNER_HEIGHT = 75;

    private JPanel mainPanel;
    private JLabel banner;

    public MainFrame() {
        setTitle("Grade Calculator");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setIconImage(new ImageIcon(getClass().getResource("/logo.jpg")).getImage());
        getContentPane().setBackground(Color.decode("#E6D1D8"));
        setLayout(null);

        initializeNavbar();
        initializeBanner();
        initializeMainPanel();

        // Show initial content
        changeMainPanel(new SignInPanel());
        setVisible(true);
    }

    private void initializeNavbar() {
        Navbar navbar = new Navbar(this);
        navbar.setBounds(0, 0, NAVBAR_WIDTH, FRAME_HEIGHT);
        add(navbar);
    }

    private void initializeBanner() {
        banner = new JLabel("Sign In", SwingConstants.CENTER);
        banner.setFont(new Font("Arial", Font.BOLD, 34));
        banner.setOpaque(true);
        banner.setBackground(Color.decode("#391E22"));
        banner.setForeground(Color.WHITE);
        banner.setBounds(NAVBAR_WIDTH, 0, FRAME_WIDTH - NAVBAR_WIDTH, BANNER_HEIGHT);
        add(banner);
    }

    private void initializeMainPanel() {
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(NAVBAR_WIDTH, BANNER_HEIGHT, FRAME_WIDTH - NAVBAR_WIDTH, FRAME_HEIGHT - BANNER_HEIGHT);
        add(mainPanel);
    }

    public void changeMainPanel(JPanel newPanel) {
        mainPanel.removeAll();
        mainPanel.add(newPanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void updateBanner(String text) {
        banner.setText(text);
    }
}