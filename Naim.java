import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Naim extends JFrame {
    private Bank bank = new Bank();
    private JTextArea outputArea;

    public Main() {
        setTitle("Banking System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                bank.closeConnection();
            }
        });
        setLayout(new BorderLayout());

        // Output area for displaying results
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2));

        JButton createAccountBtn = new JButton("Create Account");
        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton checkBalanceBtn = new JButton("Check Balance");
        JButton transferBtn = new JButton("Transfer");
        JButton listAccountsBtn = new JButton("List Accounts");

        buttonPanel.add(createAccountBtn);
        buttonPanel.add(depositBtn);
        buttonPanel.add(withdrawBtn);
        buttonPanel.add(checkBalanceBtn);
        buttonPanel.add(transferBtn);
        buttonPanel.add(listAccountsBtn);

        add(buttonPanel, BorderLayout.SOUTH);

        // Action listeners
        createAccountBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAccount();
            }
        });

        depositBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });

        withdrawBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });

        checkBalanceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });

        transferBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transfer();
            }
        });

        listAccountsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listAccounts();
            }
        });
    }

    private void createAccount() {
        String name = JOptionPane.showInputDialog("Enter owner name:");
        if (name != null) {
            String balanceStr = JOptionPane.showInputDialog("Enter initial balance:");
            try {
                double balance = Double.parseDouble(balanceStr);
                String accountNumber = bank.createAccount(name, balance);
                outputArea.append("Account created: " + accountNumber + " for " + name + "\n");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid balance amount.");
            }
        }
    }

    private void deposit() {
        String accNum = JOptionPane.showInputDialog("Enter account number:");
        Account acc = bank.getAccount(accNum);
        if (acc != null) {
            String amountStr = JOptionPane.showInputDialog("Enter deposit amount:");
            try {
                double amount = Double.parseDouble(amountStr);
                acc.deposit(amount);
                bank.updateAccount(acc);
                outputArea.append("Deposited $" + amount + " to " + accNum + ". New balance: $" + acc.getBalance() + "\n");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid amount.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Account not found.");
        }
    }

    private void withdraw() {
        String accNum = JOptionPane.showInputDialog("Enter account number:");
        Account acc = bank.getAccount(accNum);
        if (acc != null) {
            String amountStr = JOptionPane.showInputDialog("Enter withdrawal amount:");
            try {
                double amount = Double.parseDouble(amountStr);
                acc.withdraw(amount);
                bank.updateAccount(acc);
                outputArea.append("Withdrew $" + amount + " from " + accNum + ". New balance: $" + acc.getBalance() + "\n");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid amount.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Account not found.");
        }
    }

    private void checkBalance() {
        String accNum = JOptionPane.showInputDialog("Enter account number:");
        Account acc = bank.getAccount(accNum);
        if (acc != null) {
            outputArea.append("Balance for " + accNum + ": $" + acc.getBalance() + "\n");
        } else {
            JOptionPane.showMessageDialog(this, "Account not found.");
        }
    }

    private void transfer() {
        String fromAcc = JOptionPane.showInputDialog("Enter from account number:");
        String toAcc = JOptionPane.showInputDialog("Enter to account number:");
        Account from = bank.getAccount(fromAcc);
        Account to = bank.getAccount(toAcc);
        if (from != null && to != null) {
            String amountStr = JOptionPane.showInputDialog("Enter transfer amount:");
            try {
                double amount = Double.parseDouble(amountStr);
                from.transfer(to, amount);
                bank.updateAccount(from);
                bank.updateAccount(to);
                outputArea.append("Transferred $" + amount + " from " + fromAcc + " to " + toAcc + "\n");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid amount.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "One or both accounts not found.");
        }
    }

    private void listAccounts() {
        outputArea.append("All Accounts:\n");
        bank.listAccounts();
        // Note: listAccounts() prints to console; for GUI, we could modify it to return a string
        // For simplicity, it still prints to console, but you can enhance it.
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}