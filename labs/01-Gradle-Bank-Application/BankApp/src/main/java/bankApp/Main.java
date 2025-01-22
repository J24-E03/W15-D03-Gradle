package bankApp;

public class Main {
    public static void main(String[] args) {
        BankModel bankModel = new BankModel();
        BankView bankView = new BankView();
        BankController controller = new BankController(bankModel, bankView);

        controller.run();
    }
}