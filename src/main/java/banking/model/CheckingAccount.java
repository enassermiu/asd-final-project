package banking.model;

import framework.model.Account;
import framework.model.Customer;

public class CheckingAccount extends Account {

    public CheckingAccount(Customer customer, String accountNumber) {
        super(customer, accountNumber);
    }

    @Override
    public void withdraw(double amount){
        if(amount > 100)
            notifyObservers("Transaction over 100$");

        super.withdraw(amount);
    }

    @Override
    public String getAccountTypeCode() {
        return "C";
    }

    @Override
    public void addInterest() {

    }
}
