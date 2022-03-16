package banking.model;

import framework.model.Address;
import framework.model.Customer;

public class PersonalAccount extends Customer {

    private String birthdate;

    public PersonalAccount(String name, String email, String birthdate, Address address) {
        super(name, email, address);

        this.birthdate = birthdate;
    }

    public String getCustomerTpeCode(){
        return "P";
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}