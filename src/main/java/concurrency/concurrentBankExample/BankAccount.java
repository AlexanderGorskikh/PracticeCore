package concurrency.concurrentBankExample;

import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private final int accountNumber;
    private double balance;
    private final ReentrantLock lock = new ReentrantLock();

    public BankAccount(int accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }
    public int getAccountNumber() {
        return accountNumber;
    }

    public void deposit(double amount) {
        lock.lock();
        try {
            if (amount > 0) {
                balance += amount;
            }
        } finally {
            lock.unlock();
        }
    }

    public boolean withdraw(double amount) {
        lock.lock();
        try {
            if (amount > 0 && balance >= amount) {
                balance -= amount;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public double getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }

    public ReentrantLock getLock() {
        return lock;
    }
}
