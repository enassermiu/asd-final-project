package framework.service.create_account_factory.concrete.creditcard;

import creditcard.model.GoldCredit;
import framework.model.Account;
import framework.model.Customer;
import framework.service.create_account_factory.creator.AccountCreator;

public class GoldCreditAccountCreator extends AccountCreator {

    @Override
    public Account CreatAccount(Customer customer, String accountNumber) {
        return new GoldCredit(customer, accountNumber);
    }
}
