package framework.service.factory.concrete.banking;

import banking.model.CheckingAccount;
import banking.model.SavingAccount;
import framework.model.Account;
import framework.model.Customer;
import framework.service.factory.creator.AccountCreator;

public class CheckingAccountCreator extends AccountCreator {

    @Override
    public Account CreatAccount(Customer customer, String accountNumber) {
        return new CheckingAccount(customer, accountNumber);
    }
}
