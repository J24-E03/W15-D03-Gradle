package bankApp;

import java.io.Serializable;
import java.util.Random;

public class BankAccount implements Serializable {
    private final String accountNumber;
    private double balance;

    public BankAccount() {
        this.accountNumber = generateAccountNumber();
        this.balance = 0.0;
    }


    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean transfer(BankAccount recipient, double amount) {
        if (balance >= amount) {
            balance -= amount;
            recipient.deposit(amount);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber + " Balance:" + balance + " Kr";
    }

    private String generateAccountNumber() {
        Random random = new Random();
        int[] randomDigits = new int[4];
        for (int i = 0; i < 4; i++) {
            randomDigits[i] = random.nextInt(9) + 1;
        }
        int checksum = 0;
        for (int digit : randomDigits) {
            checksum += digit;
        }
        StringBuilder accountNumberGenerator = new StringBuilder();
        for (int digit : randomDigits) {
            accountNumberGenerator.append(digit);
        }
        accountNumberGenerator.append(checksum);

        return accountNumberGenerator.toString();
    }
}