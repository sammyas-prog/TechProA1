import java.util.Scanner;

public class aim {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nBanking System Menu:");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Transfer");
            System.out.println("6. List Accounts");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter owner name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter initial balance: ");
                    double balance = scanner.nextDouble();
                    bank.createAccount(name, balance);
                    break;
                case 2:
                    System.out.print("Enter account number: ");
                    String accNum = scanner.nextLine();
                    Account acc = bank.getAccount(accNum);
                    if (acc != null) {
                        System.out.print("Enter deposit amount: ");
                        double depAmount = scanner.nextDouble();
                        acc.deposit(depAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter account number: ");
                    accNum = scanner.nextLine();
                    acc = bank.getAccount(accNum);
                    if (acc != null) {
                        System.out.print("Enter withdrawal amount: ");
                        double withAmount = scanner.nextDouble();
                        acc.withdraw(withAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter account number: ");
                    accNum = scanner.nextLine();
                    acc = bank.getAccount(accNum);
                    if (acc != null) {
                        System.out.println("Balance: $" + acc.getBalance());
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter from account number: ");
                    String fromAcc = scanner.nextLine();
                    System.out.print("Enter to account number: ");
                    String toAcc = scanner.nextLine();
                    Account from = bank.getAccount(fromAcc);
                    Account to = bank.getAccount(toAcc);
                    if (from != null && to != null) {
                        System.out.print("Enter transfer amount: ");
                        double transAmount = scanner.nextDouble();
                        from.transfer(to, transAmount);
                    } else {
                        System.out.println("One or both accounts not found.");
                    }
                    break;
                case 6:
                    bank.listAccounts();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}