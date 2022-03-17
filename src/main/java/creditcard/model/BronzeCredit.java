package creditcard.model;

import framework.model.Customer;

public class BronzeCredit extends CreditCardAccount {

    public BronzeCredit(Customer customer, String accountNumber) {
        super(customer, accountNumber);
    }

    public BronzeCredit(Customer customer, String accountNumber, String ccNumber, String expirationDate) {
        super(customer, accountNumber, ccNumber, expirationDate);
    }

    @Override
    public String getAccountTypeCode() {
        return "B";
    }

    @Override
    public void setMonthlyInterestRate() {
        setMI(0.10);
    }

    @Override
    public void setMinimumPayment() {
        setMP(0.14);
    }
}
