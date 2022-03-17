package framework.service;

import framework.model.Account;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface AccountService {
    Account saveAccount(Account account);
    Account getAccount(String accountNumber);

    Collection<Account> getAllAccounts();

    void deposit (String accountNumber, double amount) throws Exception;
    void withdraw (String accountNumber, double amount)  throws Exception;

    void addInterest(String accountNumber);
    void addInterest();

    void seedBankAccounts();
    void seedCreditAccounts();

    List<String> generateReport(String[] accountNumbers, LocalDate from, LocalDate to);
}
