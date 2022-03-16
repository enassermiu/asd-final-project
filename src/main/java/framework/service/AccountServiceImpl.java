package framework.service;

import banking.model.PersonalAccount;
import banking.model.SavingAccount;
import banking.repository.AccountDAO;
import banking.repository.AccountDAOImpl;
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

    public void withdraw(String accountNumber, double amount) {
        Account account = accountDAO.loadAccount(accountNumber);
        account.withdraw(amount);
        accountDAO.updateAccount(account);
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

    public void seedsAccounts(){
        Address address = new Address("1000 N 4th St", "Fairfield", "IA", "52577");
        Customer customer = new PersonalAccount("Customer1", "customer1@gmail.com", "00/00/0000", address);
        Account[] accounts = {
                new SavingAccount(customer, "100")
        };

        Arrays.stream(accounts).forEach(a -> saveAccount(a));
    }
}
