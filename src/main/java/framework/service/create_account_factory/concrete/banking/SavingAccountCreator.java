package framework.service.create_account_factory.concrete.banking;

import banking.model.SavingAccount;
import framework.model.Account;
import framework.model.Customer;
import framework.service.create_account_factory.creator.AccountCreator;

public class SavingAccountCreator extends AccountCreator {

    @Override
    public Account CreatAccount(Customer customer, String accountNumber) {
        return new SavingAccount(customer, accountNumber);
    }
}

