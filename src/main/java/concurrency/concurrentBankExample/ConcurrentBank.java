package concurrency.concurrentBankExample;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentBank {
    private final ConcurrentHashMap<Integer, BankAccount> accounts = new ConcurrentHashMap<>();

    public BankAccount createAccount(int accountNumber, double initialBalance) {
        BankAccount account = new BankAccount(accountNumber, initialBalance);
        accounts.put(accountNumber, account);
        return account;
    }

    public boolean transfer(int fromAccountNumber, int toAccountNumber, double amount) {
        BankAccount fromAccount = accounts.get(fromAccountNumber);
        BankAccount toAccount = accounts.get(toAccountNumber);

        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("One or both accounts do not exist.");
        }

        // Lock accounts in consistent order to avoid deadlocks
        BankAccount firstLock = fromAccountNumber < toAccountNumber ? fromAccount : toAccount;
        BankAccount secondLock = fromAccountNumber < toAccountNumber ? toAccount : fromAccount;

        firstLock.getLock().lock();
        secondLock.getLock().lock();

        try {
            if (fromAccount.withdraw(amount)) {
                toAccount.deposit(amount);
                return true;
            }
            return false;
        } finally {
            secondLock.getLock().unlock();
            firstLock.getLock().unlock();
        }
    }

    public double getTotalBalance() {
        double totalBalance = 0;
        for (BankAccount account : accounts.values()) {
            account.getLock().lock();
            try {
                totalBalance += account.getBalance();
            } finally {
                account.getLock().unlock();
            }
        }
        return totalBalance;
    }
}
