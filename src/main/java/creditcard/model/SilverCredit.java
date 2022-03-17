package creditcard.model;

import framework.model.Customer;

public class SilverCredit extends CreditCardAccount {

    public SilverCredit(Customer customer, String accountNumber) {
        super(customer, accountNumber);
    }

    public SilverCredit(Customer customer, String accountNumber, String ccNumber, String  expirationDate) {
        super(customer, accountNumber, ccNumber, expirationDate);
    }

    @Override
    public String getAccountTypeCode() {
        return "S";
    }

    @Override
    public void setMonthlyInterestRate() {
        setMI(0.08);
    }

    @Override
    public void setMinimumPayment() {
        setMP(0.12);
    }
}
