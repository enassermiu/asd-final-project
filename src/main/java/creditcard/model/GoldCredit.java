package creditcard.model;

import framework.model.Customer;

public class GoldCredit extends CreditCardAccount {

    private double getMonthlyInterest(double credit) {
        return credit * 0.06;
    }

    private double getMonthlyMinimumPayment(double credit) {
        return credit * 0.1;
    }

    public GoldCredit(Customer customer, String accountNumber) {
        super(customer, accountNumber);
    }

    public GoldCredit(Customer customer, String accountNumber, String ccNumber, String  expirationDate) {
        super(customer, accountNumber, ccNumber, expirationDate);
    }
    @Override
    public  void  withdraw(double amount){
        if(amount>400)
            notifyObservers("you are charged for more than $400");
        super.withdraw(amount);
    }
    @Override
    public void addInterest() {

    }
}
