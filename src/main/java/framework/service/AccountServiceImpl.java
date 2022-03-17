package framework.service;

import banking.model.PersonalAccount;
import banking.model.SavingAccount;
import banking.repository.AccountDAO;
import banking.repository.AccountDAOImpl;
import creditcard.model.CreditCardAccount;
import creditcard.model.GoldCredit;
import framework.model.Account;
import framework.model.Address;
import framework.model.Customer;

import java.util.Arrays;
import java.util.Collection;

public class AccountServiceImpl implements AccountService {

    private AccountDAO accountDAO;
    private volatile static AccountServiceImpl instance;

    private AccountServiceImpl() {
        accountDAO = new AccountDAOImpl();
    }

    public static AccountService getInstance() {
        if (instance == null) {
            synchronized (AccountServiceImpl.class) {
                if (instance == null)
                    instance = new AccountServiceImpl();
            }
        }
        return instance;
    }

    public Account saveAccount(Account account) {
        accountDAO.saveAccount(account);
        return account;
    }

    public void deposit(String accountNumber, double amount) throws Exception {
        Account account = accountDAO.loadAccount(accountNumber);
        if (account == null) {
            throw new Exception("Account Not Found!");
        } else if (amount < 0) {
            throw new Exception("Can't Deposit a negative amount!");
        } else {
            account.deposit(amount);
            accountDAO.updateAccount(account);
        }
    }

    public Account getAccount(String accountNumber) {
        Account account = accountDAO.loadAccount(accountNumber);
        return account;
    }

    public Collection<Account> getAllAccounts() {
        return accountDAO.getAccounts();
    }

    public void withdraw(String accountNumber, double amount) throws Exception {
        Account account = accountDAO.loadAccount(accountNumber);

        if (account == null) {
            throw new Exception("Account Not Found!");
        } else if (amount < 0) {
            throw new Exception("Can't withdraw a negative amount!");
        } else if (account.getBalance() < amount) {
            throw new Exception("Can't withdraw more than your current balance: " + account.getBalance());
        } else {
            account.withdraw(amount);
            accountDAO.updateAccount(account);
        }
    }

    public void addInterest(String accountNumber) {
        Account account = accountDAO.loadAccount(accountNumber);
        account.addInterest();
    }

    public void addInterest() {
        getAllAccounts().forEach(a -> {
            a.addInterest();
        });
    }

    public void seedBankAccounts() {
        Address address = new Address("1000 N 4th St", "Fairfield", "IA", "52577");
        Customer customer = new PersonalAccount("Customer1", "customer1@gmail.com", "00/00/0000", address);
        Account[] accounts = {
                new SavingAccount(customer, "100")
        };

        Arrays.stream(accounts).forEach(a -> {
            a.deposit(100);
            saveAccount(a);
        });
    }

    public void seedCreditAccounts() {
        Address address = new Address("1000 N 4th St", "Fairfield", "IA", "52577");
        Customer customer = new PersonalAccount("Customer1", "customer1@gmail.com", "00/00/0000", address);
        CreditCardAccount[] accounts = {
                new GoldCredit(customer, "100")
        };

        Arrays.stream(accounts).forEach(a -> {
            a.deposit(100);
            a.setExpirationDate("01/02/2025");
            saveAccount(a);
        });
    }
}
