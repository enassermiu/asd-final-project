package banking.model;

import framework.model.Address;
import framework.model.Customer;

public class CompanyAccount extends Customer {

    private String noOfEmployees;

    public CompanyAccount(String name, String email, String noOfEmployees, Address address) {
        super(name, email, address);
        this.noOfEmployees = noOfEmployees;
    }

    public String getCustomerTpeCode(){
        return "C";
    }

    public String getNoOfEmployees() {
        return noOfEmployees;
    }

    public void setNoOfEmployees(String noOfEmployees) {
        this.noOfEmployees = noOfEmployees;
    }
}
