import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, Account> accounts = new HashMap<>();
    private int accountCounter = 1000; // Starting account number

    public String createAccount(String ownerName, double initialBalance) {
        String accountNumber = "ACC" + accountCounter++;
        Account newAccount = new Account(accountNumber, ownerName, initialBalance);
        accounts.put(accountNumber, newAccount);
        System.out.println("Account created: " + accountNumber + " for " + ownerName);
        return accountNumber;
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void listAccounts() {
        System.out.println("All Accounts:");
        for (Account acc : accounts.values()) {
            System.out.println(acc.getAccountNumber() + " - " + acc.getOwnerName() + " - Balance: $" + acc.getBalance());
        }
    }
}