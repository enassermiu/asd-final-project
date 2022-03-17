package framework.service.create_account_factory.creator;

import framework.model.Account;
import framework.model.Customer;

public abstract class AccountCreator {
    public abstract Account CreatAccount(Customer customer, String accountNumber);
}
