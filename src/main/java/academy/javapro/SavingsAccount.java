package academy.javapro;

/**
 * SavingsAccount class extending the abstract Account class.
 * Features interest rate and minimum balance requirement.
 */
public class SavingsAccount extends Account {
    private static final double MIN_BALANCE = 100.0; // Minimum balance requirement
    private final double interestRate;

    /**
     * Constructor for creating a new savings account.
     *
     * @param accountNumber The account number
     * @param customerName The name of the account holder
     * @param initialBalance The initial balance
     * @param interestRate The annual interest rate (%)
     */
    public SavingsAccount(String accountNumber, String customerName, double initialBalance, double interestRate) {
        super(accountNumber, customerName, initialBalance); // Call to the parent constructor
        this.interestRate = interestRate;
    }

    /**
     * Calculates the interest amount based on the current balance.
     *
     * @return The calculated interest amount
     */
    public double calculateInterest() {
        return getBalance() * (interestRate / 100);
    }

    /**
     * Applies the calculated interest to the account balance.
     */
    public void applyInterest() {
        double interest = calculateInterest();
        setBalance(getBalance() + interest);  //instead of calling deposit()
        logTransaction("INTEREST", interest); //making sure its logged as interest
        System.out.println("Interest of $" + String.format("%.2f", interest) + " applied.");
    }
    

    /**
     * Overrides the withdraw method with savings account-specific rules.
     * Ensures minimum balance is maintained.
     */
    @Override
    public void withdraw(double amount) {
        double newBalance = getBalance() - amount;
        if (amount > 0 && newBalance >= MIN_BALANCE) { 
            setBalance(newBalance);
            logTransaction("WITHDRAWAL", amount);
            System.out.println("Withdrew $" + String.format("%.2f", amount) + " from Savings Account.");
        } else {
            System.out.println("Insufficient funds. Minimum balance of $" + MIN_BALANCE + " required.");
        }
    }

    /**
     * Overrides the displayInfo method to include savings account-specific information.
     */
    @Override
    public void displayInfo() {
        super.displayInfo(); // Call to the parent method
        System.out.println("Account Type: Savings Account");
        System.out.println("Interest Rate: " + interestRate + "%");
        System.out.println("Minimum Balance Requirement: $" + MIN_BALANCE);
    }
}
