package framework.service;

import banking.repository.AccountDAO;
import banking.repository.AccountDAOImpl;
import framework.model.Account;

import java.util.Collection;

public class AccountServiceImpl implements AccountService {

	private AccountDAO accountDAO;
	private static AccountServiceImpl instance;

	private AccountServiceImpl(){
		accountDAO = new AccountDAOImpl();
	}

	public static AccountService getInstance(){
		if(instance == null)
			instance = new AccountServiceImpl();

		return instance;
	}

	public Account saveAccount(Account account) {
		accountDAO.saveAccount(account);
		return account;
	}

	public void deposit(String accountNumber, double amount) {
		Account account = accountDAO.loadAccount(accountNumber);
		account.deposit(amount);
		
		accountDAO.updateAccount(account);
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

	public void transferFunds(String fromAccountNumber, String toAccountNumber, double amount, String description) {
		Account fromAccount = accountDAO.loadAccount(fromAccountNumber);
		Account toAccount = accountDAO.loadAccount(toAccountNumber);
		fromAccount.transferFunds(toAccount, amount, description);
		accountDAO.updateAccount(fromAccount);
		accountDAO.updateAccount(toAccount);
	}

	@Override
	public void addInterest(String accountNumber) {
		Account account = accountDAO.loadAccount(accountNumber);
		account.addInterest();
	}
}
