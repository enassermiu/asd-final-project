package framework.service;

import framework.model.Account;

import java.util.Collection;

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
}
