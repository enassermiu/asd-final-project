package framework.service;

import framework.model.Account;
import framework.model.Address;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface AccountService {
    Account saveAccount(Account account);
    Account getAccount(String accountNumber);
    Collection<Account> getAllAccounts();
    void deposit (String accountNumber, double amount);
    void withdraw (String accountNumber, double amount);
    void transferFunds(String fromAccountNumber, String toAccountNumber, double amount, String description);
    void addInterest(String accountNumber);
    List<String> generateReport(String[] accountNumbers, LocalDate from, LocalDate to);
}
