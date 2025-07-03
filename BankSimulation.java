import java.util.*;

// Base class
abstract class BankAccount {
    protected String accountNumber;
    protected String accountHolderName;
    protected double balance;

    public BankAccount(String accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited ₹" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public abstract void withdraw(double amount);  // Abstract for specific behavior

    public void displayDetails() {
        System.out.println("\nAccount Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: ₹" + balance);
    }
}

// Derived class for Savings Account
class SavingsAccount extends BankAccount {
    private double interestRate = 0.04;

    public SavingsAccount(String accountNumber, String accountHolderName, double balance) {
        super(accountNumber, accountHolderName, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn ₹" + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    public void addInterest() {
        double interest = balance * interestRate;
        balance += interest;
        System.out.println("Interest added: ₹" + interest);
    }
}

// Derived class for Current Account
class CurrentAccount extends BankAccount {
    private double overdraftLimit = 5000;

    public CurrentAccount(String accountNumber, String accountHolderName, double balance) {
        super(accountNumber, accountHolderName, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && (balance + overdraftLimit) >= amount) {
            balance -= amount;
            System.out.println("Withdrawn ₹" + amount);
        } else {
            System.out.println("Exceeds overdraft limit or invalid amount.");
        }
    }
}

// Main class
public class BankSimulation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Creating sample accounts
        BankAccount acc1 = new SavingsAccount("S1001", "Arul Kumar", 10000);
        BankAccount acc2 = new CurrentAccount("C2001", "Priya Sharma", 5000);

        acc1.displayDetails();
        acc1.deposit(2000);
        acc1.withdraw(1500);
        ((SavingsAccount) acc1).addInterest(); // Typecast to access savings-specific method
        acc1.displayDetails();

        System.out.println("\n-----------------------------");

        acc2.displayDetails();
        acc2.deposit(1000);
        acc2.withdraw(7000); // Should allow overdraft
        acc2.displayDetails();

        sc.close();
    }
}
