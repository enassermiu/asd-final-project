package banking.model;

import framework.model.Account;
import framework.model.Customer;

public class SavingAccount extends Account {

    public SavingAccount(Customer customer, String accountNumber) {
        super(customer, accountNumber);
    }

    @Override
    public void withdraw(double amount){
        if(amount>500)
            notifyObservers("Transaction over $500");
        super.withdraw(amount);
    }

    @Override
    public String getAccountTypeCode() {
        return "S";
    }

    @Override
    public void addInterest() {

    }
}
