// 1. Defining the custom exception
class lessbalanceexception extends Exception {
    public lessbalanceexception(double amount) {
        // Passing the specific error message to the superclass (Exception) constructor
        super("withdraw amount (rs " + amount + ") is not valid. insufficient balance.");
    }
}

// 2. Creating the BankAccount class
class BankAccount {
    private double balance;

    // Constructor to initialize the account with a minimum balance
    public BankAccount(double initialBalance) {
        if (initialBalance < 1000) {
            System.out.println("Account must be created with a minimum balance of Rs 1000. Setting balance to 1000.");
            this.balance = 1000;
        } else {
            this.balance = initialBalance;
        }
    }

    // Method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: Rs " + amount + ". Current Balance: Rs " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Method to withdraw money, declaring that it might throw our custom exception
    public void withdraw(double amount) throws lessbalanceexception {
        // Check if withdrawing the amount will drop the balance below 1000
        if ((balance - amount) < 1000) {
            // Explicitly throwing the custom exception
            throw new lessbalanceexception(amount);
        }
        
        balance -= amount;
        System.out.println("Successfully withdrawn: Rs " + amount + ". Current Balance: Rs " + balance);
    }
}

// 3. Main class to test the functionality
public class BankingSystem {
    public static void main(String[] args) {
        
        System.out.println("--- Testing Custom Exception (Banking Operations) ---");
        // Create an account with an initial balance of Rs 1500
        BankAccount account = new BankAccount(1500);

        try {
            account.deposit(500); // Balance becomes 2000
            
            // Valid withdrawal: 2000 - 800 = 1200 (which is >= 1000)
            account.withdraw(800); 
            
            // Invalid withdrawal: 1200 - 500 = 700 (which is < 1000)
            // This will trigger the lessbalanceexception
            account.withdraw(500); 
            
        } catch (lessbalanceexception e) {
            // Catching and displaying the custom exception message
            System.out.println("Exception Caught: " + e.getMessage());
        }

        System.out.println("\n--- Testing Built-in Exception (ArithmeticException) ---");
        try {
            int numerator = 50;
            int denominator = 0;
            System.out.println("Attempting to divide " + numerator + " by " + denominator + "...");
            
            // This line will throw an ArithmeticException
            int result = numerator / denominator; 
            
            System.out.println("Result: " + result); // This won't execute
            
        } catch (ArithmeticException e) {
            // Catching and handling the built-in exception
            System.out.println("Exception Caught: " + e.toString());
            System.out.println("Error: Cannot divide a number by zero.");
        }
    }
}