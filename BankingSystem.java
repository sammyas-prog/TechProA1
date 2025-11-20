import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

public class BankingSystem extends JFrame {

    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);

    private JTextField loginUserField;
    private JPasswordField loginPassField;

    // User credentials in-memory store: username -> password
    private HashMap<String, String> users = new HashMap<>();

    // Accounts store: account number -> Account object
    private HashMap<String, Account> accounts = new HashMap<>();

    public BankingSystem() {
        setTitle("Banking System");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Add default admin user
        users.put("admin", "admin");

        mainPanel.add(createLoginPanel(), "Login");
        mainPanel.add(createMainMenuPanel(), "MainMenu");

        add(mainPanel);
        cardLayout.show(mainPanel, "Login");
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        JLabel titleLabel = new JLabel("Banking System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel userLabel = new JLabel("Username:");
        JLabel passLabel = new JLabel("Password:");

        loginUserField = new JTextField(15);
        loginPassField = new JPasswordField(15);

        JButton loginButton = new JButton("Login");
        JButton createAccountButton = new JButton("Create Account");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1; gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(userLabel, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panel.add(loginUserField, gbc);

        gbc.gridx = 0; gbc.gridy++; gbc.anchor = GridBagConstraints.EAST;
        panel.add(passLabel, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panel.add(loginPassField, gbc);

        gbc.gridy++; gbc.gridx = 0; gbc.anchor = GridBagConstraints.EAST;
        panel.add(loginButton, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panel.add(createAccountButton, gbc);

        loginButton.addActionListener(e -> {
            String user = loginUserField.getText().trim();
            String pass = new String(loginPassField.getPassword());
            if (users.containsKey(user) && users.get(user).equals(pass)) {
                // Successful login
                loginUserField.setText("");
                loginPassField.setText("");
                cardLayout.show(mainPanel, "MainMenu");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        createAccountButton.addActionListener(e -> createUserAccount());

        return panel;
    }

    private void createUserAccount() {
        JTextField userField = new JTextField(15);
        JPasswordField passField = new JPasswordField(15);
        JPasswordField passConfirmField = new JPasswordField(15);

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("New Username:"));
        panel.add(userField);
        panel.add(new JLabel("Password:"));
        panel.add(passField);
        panel.add(new JLabel("Confirm Password:"));
        panel.add(passConfirmField);

        int res = JOptionPane.showConfirmDialog(this, panel, "Create New User Account", JOptionPane.OK_CANCEL_OPTION);
        if (res == JOptionPane.OK_OPTION) {
            String username = userField.getText().trim();
            String password = new String(passField.getPassword());
            String passwordConfirm = new String(passConfirmField.getPassword());

            if (username.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (users.containsKey(username)) {
                JOptionPane.showMessageDialog(this, "Username already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!password.equals(passwordConfirm)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            users.put(username, password);
            JOptionPane.showMessageDialog(this, "User account created successfully!");
        }
    }

    private JPanel createMainMenuPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Banking System");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JButton addAccountButton = new JButton("Add Account");
        JButton depositButton = new JButton("Deposit To Account");
        JButton withdrawButton = new JButton("Withdraw From Account");
        JButton displayAccountsButton = new JButton("Display Account List");
        JButton logoutButton = new JButton("Logout");
        JButton exitButton = new JButton("Exit");

        addAccountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        depositButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        withdrawButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        displayAccountsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        addAccountButton.setMaximumSize(new Dimension(200, 30));
        depositButton.setMaximumSize(new Dimension(200, 30));
        withdrawButton.setMaximumSize(new Dimension(200, 30));
        displayAccountsButton.setMaximumSize(new Dimension(200, 30));
        logoutButton.setMaximumSize(new Dimension(200, 30));
        exitButton.setMaximumSize(new Dimension(200, 30));

        panel.add(Box.createVerticalStrut(20));
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(addAccountButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(depositButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(withdrawButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(displayAccountsButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(logoutButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(exitButton);

        addAccountButton.addActionListener(e -> addAccount());
        depositButton.addActionListener(e -> depositToAccount());
        withdrawButton.addActionListener(e -> withdrawFromAccount());
        displayAccountsButton.addActionListener(e -> displayAccounts());
        logoutButton.addActionListener(e -> doLogout());
        exitButton.addActionListener(e -> System.exit(0));

        return panel;
    }

    private void doLogout() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            cardLayout.show(mainPanel, "Login");
        }
    }

    private void addAccount() {
        JTextField accountNumField = new JTextField(15);
        JTextField nameField = new JTextField(15);
        JTextField initialDepositField = new JTextField(15);

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Account Number:"));
        panel.add(accountNumField);
        panel.add(new JLabel("Account Holder Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Initial Deposit:"));
        panel.add(initialDepositField);

        int res = JOptionPane.showConfirmDialog(this, panel, "Add Account", JOptionPane.OK_CANCEL_OPTION);
        if (res == JOptionPane.OK_OPTION) {
            String accNum = accountNumField.getText().trim();
            String name = nameField.getText().trim();
            String depositStr = initialDepositField.getText().trim();

            if (accNum.isEmpty() || name.isEmpty() || depositStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (accounts.containsKey(accNum)) {
                JOptionPane.showMessageDialog(this, "Account number already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                double deposit = Double.parseDouble(depositStr);
                if (deposit < 0) {
                    JOptionPane.showMessageDialog(this, "Deposit must be positive", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Account account = new Account(accNum, name, deposit);
                accounts.put(accNum, account);
                JOptionPane.showMessageDialog(this, "Account created successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid deposit amount!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void depositToAccount() {
        String accNum = JOptionPane.showInputDialog(this, "Enter Account Number:");
        if (accNum == null || accNum.trim().isEmpty()) return;
        accNum = accNum.trim();
        Account account = accounts.get(accNum);
        if (account == null) {
            JOptionPane.showMessageDialog(this, "Account not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String amountStr = JOptionPane.showInputDialog(this, "Enter amount to deposit:");
        if (amountStr == null || amountStr.trim().isEmpty()) return;
        try {
            double amount = Double.parseDouble(amountStr.trim());
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Amount must be positive", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            account.deposit(amount);
            JOptionPane.showMessageDialog(this, "Deposit successful! New Balance: $" + account.getBalance());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void withdrawFromAccount() {
        String accNum = JOptionPane.showInputDialog(this, "Enter Account Number:");
        if (accNum == null || accNum.trim().isEmpty()) return;
        accNum = accNum.trim();
        Account account = accounts.get(accNum);
        if (account == null) {
            JOptionPane.showMessageDialog(this, "Account not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String amountStr = JOptionPane.showInputDialog(this, "Enter amount to withdraw:");
        if (amountStr == null || amountStr.trim().isEmpty()) return;
        try {
            double amount = Double.parseDouble(amountStr.trim());
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Amount must be positive", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean result = account.withdraw(amount);
            if (result) {
                JOptionPane.showMessageDialog(this, "Withdrawal successful! New Balance: $" + account.getBalance());
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient balance!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayAccounts() {
        if (accounts.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No accounts available!");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Account acc : accounts.values()) {
            sb.append("Account Number: ").append(acc.getAccountNumber()).append("\n");
            sb.append("Holder Name: ").append(acc.getHolderName()).append("\n");
            sb.append("Balance: $").append(acc.getBalance()).append("\n");
            sb.append("Transaction History:\n");
            for (String t : acc.getTransactionHistory()) {
                sb.append("  ").append(t).append("\n");
            }
            sb.append("\n");
        }
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(this, scrollPane, "Account List", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BankingSystem app = new BankingSystem();
            app.setVisible(true);
        });
    }

    // Inner Class to represent Account
    private static class Account {
        private String accountNumber;
        private String holderName;
        private double balance;
        private ArrayList<String> transactionHistory = new ArrayList<>();

        public Account(String accountNumber, String holderName, double initialDeposit) {
            this.accountNumber = accountNumber;
            this.holderName = holderName;
            this.balance = initialDeposit;
            transactionHistory.add("Initial Deposit: $" + initialDeposit);
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public String getHolderName() {
            return holderName;
        }

        public double getBalance() {
            return balance;
        }

        public ArrayList<String> getTransactionHistory() {
            return transactionHistory;
        }

        public void deposit(double amount) {
            balance += amount;
            transactionHistory.add("Deposited: $" + amount);
        }

        public boolean withdraw(double amount) {
            if (amount > balance) {
                return false;
            }
            balance -= amount;
            transactionHistory.add("Withdrew: $" + amount);
            return true;
        }
    }
}