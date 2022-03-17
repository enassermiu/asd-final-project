package framework.service.create_account_factory.concrete.creditcard;

import creditcard.model.SilverCredit;
import framework.model.Account;
import framework.model.Customer;
import framework.service.create_account_factory.creator.AccountCreator;

public class SilverCreditAccountCreator extends AccountCreator {

    @Override
    public Account CreatAccount(Customer customer, String accountNumber) {
        return new SilverCredit(customer, accountNumber);
    }
}
