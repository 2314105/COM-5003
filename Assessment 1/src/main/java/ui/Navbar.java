package ui;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Navbar extends JPanel {

    public Navbar(MainFrame mainFrame) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        setBackground(Color.decode("#391E22"));
        setPreferredSize(new Dimension(155, 800));

        String[] buttonLabels = {
                "Sign In", "Foundation", "L5 and L6 Modules", "Direct L6 Entry",
                "Masters Award", "<html>Postgraduate<br>Diploma</html>",
                "<html>Postgraduate<br>Certificate</html>", "Exit"
        };

        Dimension buttonSize = new Dimension(134, 86);
        JButton[] buttons = createNavbarButtons(buttonLabels, buttonSize);

        // Add button actions
        configureButtonActions(mainFrame, buttons);

        // Disable inactive buttons
        disableInactiveButtons(buttons, new int[]{1, 4, 5, 6});

        // Add buttons to the panel
        for (JButton button : buttons) {
            add(button);
        }
    }

    private JButton[] createNavbarButtons(String[] labels, Dimension size) {
        JButton[] buttons = new JButton[labels.length];
        for (int i = 0; i < labels.length; i++) {
            buttons[i] = new JButton(labels[i]);
            buttons[i].setPreferredSize(size);
            buttons[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        return buttons;
    }

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
                "Exit", () -> System.exit(0)
        );

        buttons[0].addActionListener(e -> actions.get("Sign In").run());
        buttons[2].addActionListener(e -> actions.get("L5 and L6 Modules").run());
        buttons[3].addActionListener(e -> actions.get("Direct L6 Entry").run());
        buttons[7].addActionListener(e -> actions.get("Exit").run());
    }

    private void disableInactiveButtons(JButton[] buttons, int[] inactiveIndexes) {
        for (int index : inactiveIndexes) {
            buttons[index].setEnabled(false);
        }
    }
}