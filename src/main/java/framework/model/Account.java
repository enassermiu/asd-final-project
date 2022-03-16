package framework.model;

import framework.notification.EmailObserver;
import framework.notification.Observable;
import framework.notification.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Account implements Observable {

    private String accountNumber;
    private Customer customer;

    private List<AccountEntry> entryList = new ArrayList<AccountEntry>();
    private List<Observer> observers = new ArrayList<>();

    public Account(Customer customer, String accountNumber) {
        this.setCustomer(customer);
        this.accountNumber = accountNumber;
        add(new EmailObserver());
    }

    public double getBalance() {
        double balance = 0;
        for (AccountEntry entry : entryList) {
            balance += entry.getAmount();
        }
        return balance;
    }

    public void deposit(double amount) {
        AccountEntry entry = new AccountEntry(amount, "deposit", "", "");
        entryList.add(entry);
//        notifyObservers("The amount " + amount + " has been depoited");
    }

    public void withdraw(double amount) {

        AccountEntry entry = new AccountEntry(-amount, "withdraw", "", "");
        entryList.add(entry);

    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public abstract String getAccountTypeCode();

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public abstract void addInterest();

    public void add(Observer o) {
        observers.add(o);
    }

    public void remove(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers(String message) {
        for (Observer o : observers) {
            o.send(this.getCustomer().getEmail(), message);
        }
    }
}
