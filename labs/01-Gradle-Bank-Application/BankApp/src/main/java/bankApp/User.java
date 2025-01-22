package bankApp;

import bankApp.BankAccount;

import java.io.Serializable;

public class User implements Serializable {
    private final String username;
    private final String socialId;
    private String password;
    private String salt;
    private final BankAccount bankAccount;

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String username, String socialId, String password, String salt) {
        this.username = username;
        this.socialId = socialId;
        this.password = password;
        this.salt = salt;
        this.bankAccount = new BankAccount();
    }

    public String getSalt() {
        return salt;
    }

    public String getSocialId() {
        return socialId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void deposit(double amount) {
        bankAccount.deposit(amount);
    }

    public boolean withdraw(double amount) {
        return bankAccount.withdraw(amount);
    }

    public boolean transfer(User recipient, double amount) {
        return bankAccount.transfer(recipient.getBankAccount(), amount);
    }

    public String getAccountDetails() {
        return bankAccount.toString();
    }
}