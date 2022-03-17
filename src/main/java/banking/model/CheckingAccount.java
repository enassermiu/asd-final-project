package banking.model;

import framework.model.Account;
import framework.model.Customer;

public class CheckingAccount extends Account {

    public CheckingAccount(Customer customer, String accountNumber) {
        super(customer, accountNumber);
    }

    @Override
    public String getAccountTypeCode() {
        return "C";
    }

    @Override
    public void addInterest() {
        deposit(getBalance() * 0.05, "Interest");
    }

    @Override
    public void newDepositTransactionInserted(double amount, String description) {
        getCustomer().newBankTransactionAlert(this, amount, description);
    }

    @Override
    public void newWithdrawTransactionInserted(double amount, String description) {
        getCustomer().newBankTransactionAlert(this, amount, description);
    }
}
