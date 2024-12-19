package ui;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/*
This class defines the navigation bar (Navbar) for the Grade Calculator application.
The Navbar provides buttons to navigate between different sections of the application.
*/
public class Navbar extends JPanel {

    /*
    Constructor to initialize the Navbar with buttons and configure their actions.
    The Navbar is styled and aligned to the left side of the application window.
    */
    public Navbar(MainFrame mainFrame) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        setBackground(Color.decode("#391E22"));
        setPreferredSize(new Dimension(155, 800));

        // Define the labels for each button
        String[] buttonLabels = {
                "Sign In", "Foundation", "L5 and L6 Modules", "Direct L6 Entry",
                "Masters Award", "<html>Postgraduate<br>Diploma</html>",
                "<html>Postgraduate<br>Certificate</html>", "Exit"
        };

        Dimension buttonSize = new Dimension(134, 86); // Set button dimensions
        JButton[] buttons = createNavbarButtons(buttonLabels, buttonSize); // Create buttons

        // Configure button actions to change panels or exit
        configureButtonActions(mainFrame, buttons);

        // Disable buttons that are inactive
        disableInactiveButtons(buttons, new int[]{1, 4, 5, 6});

        // Add all buttons to the panel
        for (JButton button : buttons) {
            add(button);
        }
    }

    /*
    Creates an array of buttons with the given labels and dimensions.
    Each button is styled with a hand cursor for interactivity.
    */
    private JButton[] createNavbarButtons(String[] labels, Dimension size) {
        JButton[] buttons = new JButton[labels.length];
        for (int i = 0; i < labels.length; i++) {
            buttons[i] = new JButton(labels[i]);
            buttons[i].setPreferredSize(size);
            buttons[i].setCursor(new Cursor(Cursor.HAND_CURSOR)); // Set hand cursor
        }
        return buttons;
    }

    /*
    Configures actions for specific buttons in the Navbar.
    Actions are mapped to change the main panel or exit the application.
    */
    private void configureButtonActions(MainFrame mainFrame, JButton[] buttons) {
        Map<String, Runnable> actions = Map.of(
                "Sign In", () -> {
                    mainFrame.changeMainPanel(new SignInPanel());
                    mainFrame.updateBanner("Sign In");
                },
                "L5 and L6 Modules", () -> {
                    mainFrame.changeMainPanel(new Level5Level6Panel());
                    mainFrame.updateBanner("L5 and L6 Modules");
                },
                "Direct L6 Entry", () -> {
                    mainFrame.changeMainPanel(new DirectL6Panel());
                    mainFrame.updateBanner("Direct L6 Entry");
                },
                "Exit", () -> System.exit(0) // Exit the application
        );

        // Attach actions to specific buttons
        buttons[0].addActionListener(e -> actions.get("Sign In").run());
        buttons[2].addActionListener(e -> actions.get("L5 and L6 Modules").run());
        buttons[3].addActionListener(e -> actions.get("Direct L6 Entry").run());
        buttons[7].addActionListener(e -> actions.get("Exit").run());
    }

    /*
    Disables buttons .
    */
    private void disableInactiveButtons(JButton[] buttons, int[] inactiveIndexes) {
        for (int index : inactiveIndexes) {
            buttons[index].setEnabled(false); // Disable the button
        }
    }
}
