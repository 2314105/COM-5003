package jobme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApp extends JFrame {

    // Theme Colors
    private static final Color BACKGROUND_COLOR = new Color(10, 20, 30);
    private static final Color SIDEBAR_COLOR = new Color(50, 100, 200);
    private static final Color SIDEBAR_HOVER_COLOR = new Color(222, 222, 222);
    private static final Color BUTTON_TEXT_COLOR = Color.BLACK;
    private static final Color BUTTON_HOVER_TEXT_COLOR = new Color(255, 135, 0);

    private JPanel mainContent; // Main content panel
    private CardLayout cardLayout; // Layout for the main content

    public MainApp() {
        initComponents();
    }

    public void initComponents() {
        setTitle("Grade Calculator");
        setSize(new Dimension(1200, 720));
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add Components
        add(appBar(), BorderLayout.NORTH);
        add(sideBar(new String[]{
                "Foundation",
                "L5/L6 Modules",
                "Direct Level 6 Entry",
                "Postgraduate Diploma",
                "Postgraduate Certificate",
                "Masters"
        }), BorderLayout.WEST);
        add(mainContentPanel(), BorderLayout.CENTER);
    }

    public JPanel appBar() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND_COLOR);
        panel.setPreferredSize(new Dimension(1200, 45));

        JLabel title = new JLabel("Grade Calculator", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(Color.WHITE); // White text
        panel.add(title, BorderLayout.CENTER); // Add to the center of the app bar

        return panel;
    }

    public JPanel sideBar(String[] values) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(SIDEBAR_COLOR);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // Single column
        gbc.gridy = GridBagConstraints.RELATIVE; // Each button in a new row
        gbc.weightx = 1.0; // Buttons stretch horizontally
        gbc.weighty = 1.0; // Buttons stretch vertically to fill space
        gbc.fill = GridBagConstraints.BOTH; // Fill available space

        for (String value : values) {
            JButton button = createSidebarButton(value);
            panel.add(button, gbc);
        }

        return panel;
    }

    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(290, 45));
        button.setBackground(Color.WHITE);
        button.setForeground(BUTTON_TEXT_COLOR);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(5, 10, 5, 10));

        // Add hover effects
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(SIDEBAR_HOVER_COLOR);
                button.setForeground(BUTTON_HOVER_TEXT_COLOR);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.WHITE);
                button.setForeground(BUTTON_TEXT_COLOR);
            }
        });

        // Add action listener to switch content
        button.addActionListener(e -> switchContent(text));

        return button;
    }

    private JPanel mainContentPanel() {
        mainContent = new JPanel();
        cardLayout = new CardLayout();
        mainContent.setLayout(cardLayout);

        // Add content cards
        mainContent.add(createContentPanel("Foundation Content"), "Foundation");
        mainContent.add(createContentPanel("L5/L6 Modules Content"), "L5/L6 Modules");
        mainContent.add(createContentPanel("Direct Level 6 Entry Content"), "Direct Level 6 Entry");
        mainContent.add(createContentPanel("Postgraduate Diploma Content"), "Postgraduate Diploma");
        mainContent.add(createContentPanel("Postgraduate Certificate Content"), "Postgraduate Certificate");
        mainContent.add(createContentPanel("Masters Content"), "Masters");

        return mainContent;
    }

    private JPanel createContentPanel(String text) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.LIGHT_GRAY);

        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label, BorderLayout.CENTER);

        return panel;
    }

    private void switchContent(String cardName) {
        cardLayout.show(mainContent, cardName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainApp().setVisible(true));
    }
}