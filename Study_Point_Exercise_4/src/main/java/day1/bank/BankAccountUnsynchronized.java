package day1.bank;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// NOT THREADSAFE
public class BankAccountUnsynchronized {

    private double balance;
    private final Lock lock = new ReentrantLock();

    public BankAccountUnsynchronized() {
        balance = 0;
    }

    //public synchronized void deposit(double amount) {
    public void deposit(double amount) {
//    double newBalance = balance + amount;
//    balance = newBalance;
        lock.lock();
        balance += amount;
        lock.unlock();
    }

    //public synchronized void withdraw(double amount) {
    public void withdraw(double amount) {
//    double newBalance = balance - amount;
//    balance = newBalance;
        lock.lock();
        balance -= amount;
        lock.unlock();
    }

    public double getBalance() {
        return balance;
    }
}
