package bankApp;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BankModel {
    private final List<bankApp.User> users;
    public static final int VALIDAGE = 18;

    public BankModel() {
        this.users = new ArrayList<>();
    }

    public boolean addUser(String username, String socialId, String password) {
        if (getUser(socialId) == null && ((Year.now().getValue() - Integer.parseInt(socialId.substring(0, 4))) >= VALIDAGE)) {
            String salt = generateSalt();
            String combinedPassword = password + salt;
            String hashedPassword = hashPassword(combinedPassword);
            bankApp.User user = new bankApp.User(username, socialId, hashedPassword, salt);
            users.add(user);
            saveUser((bankApp.User) user);
            saveUsers();
            return true;
        }
        return false;
    }



    public boolean authenticateUser(String socialId, String password) {
        bankApp.User user = getUser(socialId);
        if (user != null) {
            String combinedPassword = password + user.getSalt();
            String hashedPassword = hashPassword(combinedPassword);
            if (hashedPassword != null) {
                return hashedPassword.equals(user.getPassword());
            }
        }
        return false;
    }

    private String generateSalt() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder salt = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int randomIndex = (int) (Math.random() * chars.length());
            salt.append(chars.charAt(randomIndex));
        }
        return salt.toString();
    }

    private String hashPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
            // Convert the byte array to hexadecimal string representation
            StringBuilder hexString = new StringBuilder();
            for (byte hashedByte : hashedBytes) {
                String hex = Integer.toHexString(0xff & hashedByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public bankApp.User getUser(String socialId) {
        for (bankApp.User user : users) {
            if (user.getSocialId().equals(socialId)) {
                return user;
            }
        }
        return null;
    }

    public void deposit(User user, double amount) {
        user.deposit(amount);
        updateUser(user);
        saveUsers();
        saveTransaction(user.getSocialId(), "Deposit", amount);
    }

    public boolean withdraw(User user, double amount) {
        boolean success = user.withdraw(amount);
        if (success) {
            updateUser(user);
            saveUsers();
            saveTransaction(user.getSocialId(), "Withdrawal", amount);
        }
        return success;
    }

    public boolean transfer(User sender, User recipient, double amount) {
        boolean success = sender.transfer(recipient, amount);
        if (success) {
            updateUser(sender);
            updateUser(recipient);
            saveUsers();
            saveTransferTransaction(sender.getSocialId(), recipient.getSocialId(), amount);
        }
        return success;
    }

    private void saveUser(User user) {
        try (FileWriter writer = new FileWriter("users.txt", true)) {
            String line = user.getUsername() + "," + user.getSocialId() + "," + user.getPassword() +
                    "," + user.getSalt() + "," + user.getAccountDetails() + "\n";
            writer.write(line);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void saveUsers() {
        try (FileOutputStream fileOut = new FileOutputStream("users.ser");
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(users);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public List<bankApp.User> retrieveUser() {
        List<bankApp.User> users = new ArrayList<>();
        try (FileInputStream fileIn = new FileInputStream("users.ser");
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            users = (List<User>) objectIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return users;
        }
        return users;
    }

    private void updateUser(User user) {
        Path filePath = Paths.get("users.txt");
        List<String> updatedLines = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(filePath);

            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[1].equals(user.getSocialId())) {
                    String updatedLine = user.getUsername() + "," + user.getSocialId() + "," +
                            user.getPassword() + "," + user.getSalt() + "," + user.getAccountDetails();
                    updatedLines.add(updatedLine);
                } else {
                    updatedLines.add(line);
                }
            }

            Files.write(filePath, updatedLines, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to update user.", e);
        }
    }

    private void saveTransaction(String senderSocialId, String action, double amount) {
        try (FileWriter writer = new FileWriter("transactions.txt", true)) {
            String line = senderSocialId + " , " + action + " , " + amount + "\n";
            writer.write(line);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private void saveTransaction(String senderSocialId, String action) {
        try (FileWriter writer = new FileWriter("transactions.txt", true)) {
            String line = senderSocialId + " , " + action + "\n";
            writer.write(line);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private void saveTransferTransaction(String senderSocialId, String recipientUserName, double amount) {
        try (FileWriter writer = new FileWriter("transactions.txt", true)) {
            String line = senderSocialId + " , " + " Transfer " + " , " + amount + " , " + recipientUserName + "\n";
            writer.write(line);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void changePassword(User user, String newPassword) {
        String salt = generateSalt();
        String combinedPassword = newPassword + salt;
        String hashedPassword = hashPassword(combinedPassword);
        user.setPassword(hashedPassword);
        user.setSalt(salt);
        updateUser(user);
        saveUsers();
        saveTransaction(user.getSocialId(), "Changed Password");
    }

    public List<String> getTransactionHistory(User user) {
        Path filePath = Paths.get("transactions.txt");
        List<String> transactions = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length >= 1 && parts[0].equals(user.getSocialId())) {
                    transactions.add(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to show user transactions .", e);
        }
        return transactions;
    }
}