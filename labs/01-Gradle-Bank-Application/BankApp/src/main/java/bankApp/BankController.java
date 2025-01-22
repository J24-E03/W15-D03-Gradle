package bankApp;

import java.time.Year;
import java.util.List;

public class BankController {
    private final bankApp.BankModel model;
    private final bankApp.BankView view;
    private bankApp.User currentUser;

    public BankController(bankApp.BankModel bankModel, bankApp.BankView bankView) {
        this.model = bankModel;
        this.view = bankView;
    }

    public void run() {
        int menuChoice;
        try {
            view.displayMenu();
            menuChoice = Integer.parseInt(view.getInput("Enter your choice"));
            System.out.println();
            switch (menuChoice) {
                case 1 -> {
                    createUser();
                    run();
                }
                case 2 -> {
                    login();
                    run();
                }
                case 3 -> {
                    view.displayExitMessage();
                    System.exit(0);
                }
                default -> {
                    view.displayErrorMessage("Invalid choice. Please try again.");
                    run();
                }
            }
        } catch (Exception exception) {
            view.displayErrorMessage("Invalid choice. Please try again.");
            this.run();
        }
    }

    private void loggedInMenu() {
        int menuChoice;
        try {
            view.displayLoggedInMenu();
            menuChoice = Integer.parseInt(view.getInput("Enter your choice"));
            System.out.println();

            switch (menuChoice) {
                case 1 -> {
                    balance();
                    loggedInMenu();
                }
                case 2 -> {
                    deposit();
                    loggedInMenu();
                }
                case 3 -> {
                    withdraw();
                    loggedInMenu();
                }
                case 4 -> {
                    transfer();
                    loggedInMenu();
                }
                case 5 -> {
                    changePassword();
                    loggedInMenu();
                }
                case 6 -> {
                    viewTransactionHistory();
                    loggedInMenu();
                }
                case 7 -> {
                    currentUser = null;
                    view.displayBackMainMessage();
                    run();
                }
                case 8 -> {
                    view.displayExitMessage();
                    System.exit(0);
                }
                default -> {
                    view.displayErrorMessage("Invalid choice. Please try again.");
                    loggedInMenu();
                }
            }
        } catch (Exception exception) {
            view.displayErrorMessage("Invalid choice. Please try again.");
            this.run();
        }
    }

    public void createUser() {
        String username = view.getInput("Enter your name");
        String socialId = view.getSocialId("Enter your socialId number (like this yyyy-abc) or 0 to exit");
        if (socialId.equalsIgnoreCase("0")) {
            view.displayErrorMessage("You have already chose to exit from the create User menu!");
            return;
        }
        if ((Year.now().getValue() - Integer.parseInt(socialId.substring(0, 4))) < bankApp.BankModel.VALIDAGE) {
            view.displayErrorMessage("You must be over 18 years old to open an account!");
            return;
        }
        String password = view.getPassword("Enter your password");
        if (model.addUser(username, socialId, password)) {
            view.displayMessage("User created successfully!");
        } else {
            view.displayErrorMessage("The user was duplicate and was not added!");
        }
    }

    public void login() {
        String socialId = view.getSocialId("Enter your socialId number (like this yyyy-abc) or 0 to exit");
        String password = "";
        if (!socialId.equalsIgnoreCase("0")) password = view.getInput("Enter password");

        if (socialId.equalsIgnoreCase("0")) {
            view.displayErrorMessage("You have already chose to exit from the login menu!");
            return;
        }
        if (model.authenticateUser(socialId, password)) {
            currentUser = model.getUser(socialId);
            loggedInMenu();
        } else {
            view.displayErrorMessage("Invalid username or password.");
        }
    }

    public void balance() {
        view.displayAccountDetails(currentUser.getAccountDetails());
    }

    public void deposit() {
        double amount = view.getAmountInput("Enter deposit amount");
        model.deposit(currentUser, amount);
        view.displayAccountDetails(currentUser.getAccountDetails());
    }

    public void withdraw() {
        double amount = view.getAmountInput("Enter withdrawal amount");
        boolean success = model.withdraw(currentUser, amount);
        if (success) {
            view.displayAccountDetails(currentUser.getAccountDetails());
        } else {
            view.displayErrorMessage("Insufficient balance.");
        }
    }

    public void transfer() {
        String recipientSocialId = view.getSocialId("Enter recipient socialId number (like this yyyy-abc) or 0 to exit");
        bankApp.User recipient = model.getUser(recipientSocialId);
        if (recipientSocialId.equalsIgnoreCase("0")) {
            view.displayErrorMessage("You have already chose to exit from the transfer menu!");
            return;
        }
        if (recipient == null) {
            view.displayErrorMessage("Recipient not found.");
            return;
        }

        double amount = view.getAmountInput("Enter transfer amount");
        boolean success = model.transfer(currentUser, recipient, amount);
        if (success) {
            view.displayAccountDetails(currentUser.getAccountDetails());
        } else {
            view.displayErrorMessage("Insufficient balance.");
        }
    }

    public void setCurrentUser(bankApp.User currentUser) {
        this.currentUser = currentUser;
    }

    public void changePassword() {
        String currentPassword = view.getInput("Enter your current password");
        if (model.authenticateUser(currentUser.getSocialId(), currentPassword)) {
            String newPassword = view.getPassword("Enter your new password");
            model.changePassword(currentUser, newPassword);
            view.displayMessage("Password changed successfully!");
            loggedInMenu();
        } else {
            view.displayErrorMessage("Invalid password.");
        }
    }

    public void viewTransactionHistory() {
        List<String> transactions = model.getTransactionHistory(currentUser);
        view.displayTransactionHistory(transactions);
    }
}