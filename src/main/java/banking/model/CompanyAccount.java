package banking.model;

import framework.model.Account;
import framework.model.Address;
import framework.model.Customer;

public class CompanyAccount extends Customer {

    private String noOfEmployees;

    public CompanyAccount(String name, String email, String noOfEmployees, Address address) {
        super(name, email, address);
        this.noOfEmployees = noOfEmployees;
    }

    public String getCustomerTpeCode() {
        return "C";
    }

    public String getNoOfEmployees() {
        return noOfEmployees;
    }

    public void setNoOfEmployees(String noOfEmployees) {
        this.noOfEmployees = noOfEmployees;
    }

    @Override
    public void newBankTransactionAlert(Account account, double amount, String description) {
        account.notifyObservers(description);
    }

    @Override
    public void newCreditTransactionAlert(Account account, double amount, String description) {
        account.notifyObservers(description);
    }
}
