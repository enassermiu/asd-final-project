package framework.model;

import framework.notification.EmailObserver;
import framework.notification.Observable;
import framework.notification.Observer;
import framework.notification.Report;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Account implements Observable {

    private String accountNumber;

    private Customer customer;

    private List<AccountEntry> entryList = new ArrayList<AccountEntry>();

    private List<Observer> observers = new ArrayList<>();

    public abstract Report report(LocalDate from, LocalDate to);

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

    private void addEntry(AccountEntry entry) {
        entryList.add(entry);
    }

    public void transferFunds(Account toAccount, double amount, String description) {
        AccountEntry fromEntry = new AccountEntry(-amount, description, toAccount.getAccountNumber(),
                toAccount.getCustomer().getName());
        AccountEntry toEntry = new AccountEntry(amount, description, toAccount.getAccountNumber(),
                toAccount.getCustomer().getName());

        entryList.add(fromEntry);

        toAccount.addEntry(toEntry);
    }

    public List<AccountEntry> getEntryList() {
        return entryList;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

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
