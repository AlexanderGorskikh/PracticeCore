package concurrency.concurrentBankExample;

public class Main {
    public static void main(String[] args) {
        ConcurrentBank bank = new ConcurrentBank();

        BankAccount account1 = bank.createAccount(1, 1000);
        BankAccount account2 = bank.createAccount(2, 2000);

        Thread t1 = new Thread(() -> {
            bank.transfer(1, 2, 300);
            System.out.println("Transfer 300 from account 1 to account 2 completed.");
        });

        Thread t2 = new Thread(() -> {
            bank.transfer(2, 1, 500);
            System.out.println("Transfer 500 from account 2 to account 1 completed.");
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Total Balance: " + bank.getTotalBalance());
    }
}
