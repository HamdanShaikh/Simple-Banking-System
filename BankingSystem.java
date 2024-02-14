import java.util.Scanner;

// Parent class for all types of accounts
class Account {
    private String accountNumber; // Unique identifier for the account
    private double balance; // Current balance of the account

    // Constructor to initialize the account
    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0; // Initially balance is zero
    }

    // Method to deposit funds into the account
    public void deposit(double amount) {
        balance += amount; // Add the deposited amount to the balance
        System.out.println("Deposit successful. Current balance: " + balance);
    }

    // Method to withdraw funds from the account
    public void withdraw(double amount) {
        if (balance >= amount) { // Check if sufficient funds are available
            balance -= amount; // Deduct the withdrawal amount from the balance
            System.out.println("Withdrawal successful. Current balance: " + balance);
        } else {
            System.out.println("Insufficient funds."); // Display message if insufficient funds
        }
    }

    // Method to get the current balance of the account
    public double getBalance() {
        return balance;
    }
}

// Subclass representing a savings account
class SavingsAccount extends Account {
    private double interestRate; // Interest rate for the savings account

    // Constructor to initialize the savings account
    public SavingsAccount(String accountNumber, double interestRate) {
        super(accountNumber); // Call superclass constructor
        this.interestRate = interestRate; // Set the interest rate
    }

    // Method to add interest to the savings account
    public void addInterest() {
        double interest = getBalance() * interestRate / 100; // Calculate the interest amount
        deposit(interest); // Deposit the interest amount into the account
        System.out.println("Interest added. Current balance: " + getBalance());
    }
}

// Subclass representing a current account
class CurrentAccount extends Account {
    private double overdraftLimit; // Overdraft limit for the current account

    // Constructor to initialize the current account
    public CurrentAccount(String accountNumber, double overdraftLimit) {
        super(accountNumber); // Call superclass constructor
        this.overdraftLimit = overdraftLimit; // Set the overdraft limit
    }

    // Override the withdraw method to incorporate the overdraft limit
    @Override
    public void withdraw(double amount) {
        if (getBalance() + overdraftLimit >= amount) { // Check if withdrawal amount is within the overdraft limit
            super.withdraw(amount); // Call superclass withdraw method
        } else {
            System.out.println("Withdrawal amount exceeds overdraft limit."); // Display message if withdrawal amount exceeds overdraft limit
        }
    }
}

public class BankingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Welcome message
        System.out.println("Welcome to the Banking System");

        // Prompt user for account number
        System.out.println("Enter your account number:");
        String accountNumber = scanner.nextLine();

        // Prompt user to choose account type
        System.out.println("Choose account type (1. Savings, 2. Current):");
        int accountType = scanner.nextInt();

        // Create account based on user choice
        Account account = null;
        switch (accountType) {
            case 1:
                account = new SavingsAccount(accountNumber, 3.5); // Example interest rate: 3.5%
                break;
            case 2:
                account = new CurrentAccount(accountNumber, 1000); // Example overdraft limit: 1000
                break;
            default:
                System.out.println("Invalid account type.");
                System.exit(1);
        }

        int choice;
        // Display menu and process user choices
        do {
            System.out.println("\nChoose an option:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter deposit amount:");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 2:
                    System.out.println("Enter withdrawal amount:");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.println("Current balance: " + account.getBalance());
                    break;
                case 4:
                    System.out.println("Thank you for using the Banking System.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4);

        scanner.close(); // Close scanner
    }
}
