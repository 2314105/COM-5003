package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Navbar extends JPanel {
    public Navbar(MainFrame mainFrame) {
        // Set FlowLayout for buttons
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);  // Align buttons to the left
        flowLayout.setHgap(10);  // Set horizontal gap between buttons
        flowLayout.setVgap(10);  // Set vertical gap between buttons
        setLayout(flowLayout);

        // Set the height for each button, keeping them consistent
        Dimension buttonSize = new Dimension(134, 86);  // Fixed width and height for buttons

        // Create buttons
        JButton button1 = new JButton("Sign In");
        JButton button2 = new JButton("Foundation");
        JButton button3 = new JButton("L5 and L6 Modules");
        JButton button4 = new JButton("Direct L6 Entry");
        JButton button5 = new JButton("Masters Award");
        JButton button6 = new JButton("<html>Postgraduate<br>Diploma</html>");
        JButton button7 = new JButton("<html>Postgraduate<br>Certificate</html>");
        JButton button8 = new JButton("Exit");

        // Set each button's preferred and minimum size
        button1.setPreferredSize(buttonSize);
        button2.setPreferredSize(buttonSize);
        button3.setPreferredSize(buttonSize);
        button4.setPreferredSize(buttonSize);
        button5.setPreferredSize(buttonSize);
        button6.setPreferredSize(buttonSize);
        button7.setPreferredSize(buttonSize);
        button8.setPreferredSize(buttonSize);

        button1.setMinimumSize(buttonSize);
        button2.setMinimumSize(buttonSize);
        button3.setMinimumSize(buttonSize);
        button4.setMinimumSize(buttonSize);
        button5.setMinimumSize(buttonSize);
        button6.setMinimumSize(buttonSize);
        button7.setMinimumSize(buttonSize);
        button8.setMinimumSize(buttonSize);

        // Set cursor effect (hand cursor) when hovering over buttons
        button1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button6.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button7.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button8.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add action listener for the "Sign In" button to switch panels
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.changeMainPanel(new SignInPanel());
                mainFrame.updateBanner("Sign In");
            }
        });

        // Add action listener for the "Foundation" button to switch panels
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.changeMainPanel(new FoundationPanel());
                mainFrame.updateBanner("Foundation");
            }
        });

        // Add action listener for the "L5 and L6 Modules" button
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.changeMainPanel(new Level5Level6Panel());
                mainFrame.updateBanner("L5 and L6 Modules");
            }
        });

        // Add action listener for the "Direct L6 Entry" button to switch panels
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.changeMainPanel(new DirectL6Panel());
                mainFrame.updateBanner("Direct L6 Entry");
            }
        });

        // Add action listener for the "Masters Award" button to switch panels
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.changeMainPanel(new MastersAwardsPanel());
                mainFrame.updateBanner("Masters Award");
            }
        });

        // Add action listener for the "Postgraduate Diploma" button to switch panels
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.changeMainPanel(new PostgraduateDiplomaPanel());
                mainFrame.updateBanner("Postgraduate Diploma");
            }
        });

        // Add action listener for the "Postgraduate Certificate" button to switch panels
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.changeMainPanel(new PostgraduateCertificatePanel());
                mainFrame.updateBanner("Postgraduate Certificate");
            }
        });

        // Add action listener for the "Exit" button to exit the application
        button8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Add buttons to the layout
        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(button5);
        add(button6);
        add(button7);
        add(button8);

        // Optional: Set the background color of the navbar
        setBackground(Color.decode("#391E22"));

        // Set the fixed size for Navbar and prevent resizing
        setPreferredSize(new Dimension(155, 800)); // Fixed width (155px) and height (800px)
        setMaximumSize(new Dimension(155, 800)); // Prevent the panel from being resized
        setMinimumSize(new Dimension(155, 800));
    }
}