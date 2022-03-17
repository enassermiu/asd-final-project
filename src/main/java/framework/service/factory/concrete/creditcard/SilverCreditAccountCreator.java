package framework.service.factory.concrete.creditcard;

import creditcard.model.BronzeCredit;
import creditcard.model.SilverCredit;
import framework.model.Account;
import framework.model.Customer;
import framework.service.factory.creator.AccountCreator;

public class SilverCreditAccountCreator extends AccountCreator {

    @Override
    public Account CreatAccount(Customer customer, String accountNumber) {
        return new SilverCredit(customer, accountNumber);
    }
}
