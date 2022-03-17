package framework.service.create_account_factory.concrete.banking;

import banking.model.CheckingAccount;
import framework.model.Account;
import framework.model.Customer;
import framework.service.create_account_factory.creator.AccountCreator;

public class CheckingAccountCreator extends AccountCreator {

    @Override
    public Account CreatAccount(Customer customer, String accountNumber) {
        return new CheckingAccount(customer, accountNumber);
    }
}
