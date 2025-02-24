import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BankSystem extends JFrame {
    private JPanel accountPanel, depositPanel, withdrawPanel;
    private JTextField numberTextField, depositTextField, withdrawTextField;
    private JTextArea accountDetailsArea;
    private JButton viewButton, nextButton, previousButton, depositButton, withdrawButton, quitButton;
    private ArrayList<BankAccount> accounts;
    private int index;

    public BankSystem() {
        setTitle("Bank System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(1000, 600);

        // Initialize panels
        accountPanel = new JPanel();
        depositPanel = new JPanel();
        withdrawPanel = new JPanel();
        accountPanel.setBorder(BorderFactory.createTitledBorder("Account Panel"));
        depositPanel.setBorder(BorderFactory.createTitledBorder("Deposit Panel"));
        withdrawPanel.setBorder(BorderFactory.createTitledBorder("Withdraw Panel"));

        // Initialize components
        numberTextField = new JTextField(10);
        depositTextField = new JTextField(10);
        withdrawTextField = new JTextField(10);
        accountDetailsArea = new JTextArea(10, 30);
        accountDetailsArea.setEditable(false);

        viewButton = new JButton("View");
        nextButton = new JButton("Next");
        previousButton = new JButton("Previous");
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        quitButton = new JButton("Quit");

        // Add components to panels
        accountPanel.add(new JLabel("Account Number:"));
        accountPanel.add(numberTextField);
        accountPanel.add(viewButton);
        accountPanel.add(nextButton);
        accountPanel.add(previousButton);

        depositPanel.add(new JLabel("Deposit Amount:"));
        depositPanel.add(depositTextField);
        depositPanel.add(depositButton);

        withdrawPanel.add(new JLabel("Withdraw Amount:"));
        withdrawPanel.add(withdrawTextField);
        withdrawPanel.add(withdrawButton);

        // Add panels to frame
        add(accountPanel, BorderLayout.NORTH);
        add(depositPanel, BorderLayout.CENTER);
        add(withdrawPanel, BorderLayout.SOUTH);
        add(new JScrollPane(accountDetailsArea), BorderLayout.EAST);
        add(quitButton, BorderLayout.WEST);

        // Load data
        accounts = new ArrayList<>();
        loadData();
        index = 0;
        displayAccount();

        // Event handlers
        viewButton.addActionListener(e -> displayAccount());
        nextButton.addActionListener(e -> nextAccount());
        previousButton.addActionListener(e -> previousAccount());
        depositButton.addActionListener(e -> deposit());
        withdrawButton.addActionListener(e -> withdraw());
        quitButton.addActionListener(e -> quit());

        setVisible(true);
    }

    private void loadData() {
        accounts.add(new BankAccount(1, "John Doe", 1000.0));
        accounts.add(new BankAccount(2, "Jane Smith", 1500.0));
        accounts.add(new BankAccount(3, "Alice Johnson", 2000.0));
    }

    private void displayAccount() {
        if (index < 0 || index >= accounts.size()) return;
        BankAccount account = accounts.get(index);
        numberTextField.setText(String.valueOf(account.getAccountNumber()));
        accountDetailsArea.setText(account.toString());
    }

    private void nextAccount() {
        if (index < accounts.size() - 1) {
            index++;
            displayAccount();
        }
    }

    private void previousAccount() {
        if (index > 0) {
            index--;
            displayAccount();
        }
    }

    private void deposit() {
        try {
            double amount = Double.parseDouble(depositTextField.getText());
            BankAccount account = accounts.get(index);
            account.deposit(amount);
            displayAccount();
            depositTextField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid deposit amount");
        }
    }

    private void withdraw() {
        try {
            double amount = Double.parseDouble(withdrawTextField.getText());
            BankAccount account = accounts.get(index);
            if (account.withdraw(amount)) {
                displayAccount();
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient balance");
            }
            withdrawTextField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid withdrawal amount");
        }
    }

    private void quit() {
        JOptionPane.showMessageDialog(this, "Exiting the application...");
        System.exit(0);
    }

    public static void main(String[] args) {
        new BankSystem();
    }
}