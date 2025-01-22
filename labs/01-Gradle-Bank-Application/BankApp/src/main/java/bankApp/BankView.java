package bankApp;

import java.util.List;
import java.util.Scanner;

public class BankView {
    private final Scanner scanner;

    public BankView() {
        this.scanner = new Scanner(System.in);
    }

    public String getInput(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

    public String getSocialId(String prompt) {
        String pattern = "\\d{4}-[a-zA-Z]{3}";
        String socialId;
        do {
            System.out.print(prompt + ": ");
            socialId = scanner.nextLine();
            if (socialId.equalsIgnoreCase("0")) break;
            if (!socialId.matches(pattern)) {
                System.out.println("Invalid social ID format! Please try again.");
            }
        } while (!socialId.matches(pattern));
        return socialId;
    }

    public String getPassword(String prompt) {
        int minLength = 8; // Minimum length requirement
        String password;
        boolean hasUpperCase; // At least one uppercase letter
        boolean hasLowerCase; // At least one lowercase letter
        boolean hasDigit; // At least one digit
        do {
            System.out.println("Your Password must be at least 8 characters and contain 1 uppercase, 1 lowercase, and 1 number");
            System.out.print(prompt + ": ");
            password = scanner.nextLine();
            hasUpperCase = false; // At least one uppercase letter
            hasLowerCase = false; // At least one lowercase letter
            hasDigit = false;
            for (char ch : password.toCharArray()) {
                if (Character.isUpperCase(ch)) {
                    hasUpperCase = true;
                } else if (Character.isLowerCase(ch)) {
                    hasLowerCase = true;
                } else if (Character.isDigit(ch)) {
                    hasDigit = true;
                }
            }
        } while (!hasUpperCase || !hasLowerCase || !hasDigit || !(password.length() >= minLength));
        return password;
    }

    public void displayMenu() {
        System.out.println("1. Create User");
        System.out.println("2. Login");
        System.out.println("3. Exit");
    }

    public void displayExitMessage() {
        System.out.println("Exiting the program. Goodbye!\n");
    }

    public void displayBackMainMessage() {
        System.out.println("Back to the Main Menu.\n");
    }

    public void displayLoggedInMenu() {
        System.out.println("1. Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer");
        System.out.println("5. ChangePassword");
        System.out.println("6. ShowTransactionHistory");
        System.out.println("7. Logout");
        System.out.println("8. Exit");
    }

    public void displayAccountDetails(String details) {
        System.out.println("\nAccount Details:");
        System.out.println(details);
        System.out.println();
    }

    public double getAmountInput(String prompt) {
        System.out.print(prompt + ": ");
        return Double.parseDouble(scanner.nextLine());
    }

    public void displayMessage(String message) {
        System.out.println(message);
        System.out.println();
    }

    public void displayErrorMessage(String input) {
        System.out.println("⚠️" + input);
        System.out.println();
    }

    public void displayTransactionHistory(List<String> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("Transaction History is empty.");
        } else {
            System.out.println("Transaction History:");
            for (String transaction : transactions) {
                System.out.println(transaction);
            }
        }
        System.out.println();
    }
}