package creditcard.model;

import framework.model.Account;
import framework.model.Customer;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class CreditCardAccount extends Account {

    private String ccNumber;
    private String expirationDate;
    private double MI, MP;

    public CreditCardAccount(Customer customer, String accountNumber) {
        super(customer, accountNumber);
        ccNumber = accountNumber;

        this.expirationDate = new SimpleDateFormat("mm/dd/yyyy").format(new Date()).toString();

        setMonthlyInterestRate();
        setMinimumPayment();
    }

    public CreditCardAccount(Customer customer, String accountNumber, String ccNumber, String expirationDate) {
        super(customer, accountNumber);

        this.ccNumber = ccNumber;
        this.expirationDate = expirationDate;

        setMonthlyInterestRate();
        setMinimumPayment();
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

    public double getMI() {
        return MI;
    }

    public void setMI(double MI) {
        this.MI = MI;
    }

    public double getMP() {
        return MP;
    }

    public void setMP(double MP) {
        this.MP = MP;
    }

    @Override
    public void addInterest() {
        deposit(getBalance() * getMI(), "Interest");
    }

    @Override
    public void newDepositTransactionInserted(double amount, String description) {
        //if (amount > 400)
        //getCustomer().newCreditTransactionAlert(this, amount, description);
    }

    @Override
    public void newWithdrawTransactionInserted(double amount, String description) {
        //if (amount > 400)
        getCustomer().newBankTransactionAlert(this, amount, description);
    }

    public abstract void setMonthlyInterestRate();

    public abstract void setMinimumPayment();
}
