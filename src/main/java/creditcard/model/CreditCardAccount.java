package creditcard.model;

import framework.model.Account;
import framework.model.Customer;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class CreditCardAccount extends Account {

    private String ccNumber;
    private String  expirationDate;

    public CreditCardAccount(Customer customer, String accountNumber) {
        super(customer, accountNumber);
        ccNumber = accountNumber;

        this.expirationDate = new SimpleDateFormat("mm/dd/yyyy").format(new Date()).toString();
    }

    public CreditCardAccount(Customer customer, String accountNumber, String ccNumber, String  expirationDate) {
        super(customer, accountNumber);

        this.ccNumber = ccNumber;
        this.expirationDate = expirationDate;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public  void  withdraw(double amount){
        if(amount>400)
            notifyObservers("you are charged for more than $400"+ amount);

        super.withdraw(amount);
    }
}
