import java.sql.*;
import java.util.HashMap;
import java.util.Map;
String url = "jdbc:mysql://localhost:3306/banking_system";
String username = "root";
String password = "";

public class Bank {
    private Connection connection;
    private int accountCounter = 1000; // Starting account number

    public Bank() {
        try {
            // Update these credentials as per your MySQL setup
            String url = "jdbc:mysql://localhost:3306/banking_system";
            String username = "root";
            String password = "";
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to MySQL database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String createAccount(String ownerName, double initialBalance) {
        String accountNumber = "ACC" + accountCounter++;
        try {
            String query = "INSERT INTO accounts (account_number, owner_name, balance) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, accountNumber);
            stmt.setString(2, ownerName);
            stmt.setDouble(3, initialBalance);
            stmt.executeUpdate();
            System.out.println("Account created: " + accountNumber + " for " + ownerName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountNumber;
    }

    public Account getAccount(String accountNumber) {
        try {
            String query = "SELECT * FROM accounts WHERE account_number = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, accountNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Account(rs.getString("account_number"), rs.getString("owner_name"), rs.getDouble("balance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateAccount(Account account) {
        try {
            String query = "UPDATE accounts SET balance = ? WHERE account_number = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setDouble(1, account.getBalance());
            stmt.setString(2, account.getAccountNumber());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listAccounts() {
        System.out.println("All Accounts:");
        try {
            String query = "SELECT * FROM accounts";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getString("account_number") + " - " + rs.getString("owner_name") + " - Balance: $" + rs.getDouble("balance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}