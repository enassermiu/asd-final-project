package framework.service.create_account_factory.concrete.creditcard;

import creditcard.model.BronzeCredit;
import framework.model.Account;
import framework.model.Customer;
import framework.service.create_account_factory.creator.AccountCreator;

public class BronzeCreditAccountCreator extends AccountCreator {

    @Override
    public Account CreatAccount(Customer customer, String accountNumber) {
        return new BronzeCredit(customer, accountNumber);
    }
}

