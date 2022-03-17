package creditcard.model;

import framework.model.Customer;

public class GoldCredit extends CreditCardAccount {

    public GoldCredit(Customer customer, String accountNumber) {
        super(customer, accountNumber);
    }

    public GoldCredit(Customer customer, String accountNumber, String ccNumber, String  expirationDate) {
        super(customer, accountNumber, ccNumber, expirationDate);
    }

    @Override
    public String getAccountTypeCode() {
        return "G";
    }

    @Override
    public void setMonthlyInterestRate() {
        setMI(0.06);
    }

    @Override
    public void setMinimumPayment() {
        setMP(0.10);
    }
}
