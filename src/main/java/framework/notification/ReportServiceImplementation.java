package framework.notification;

import creditcard.model.CreditCardAccount;
import framework.model.Account;
import framework.model.AccountEntry;
import org.w3c.dom.ls.LSOutput;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ReportServiceImplementation implements Report{

    Account account;

    public ReportServiceImplementation(Account account){
        this.account = account;
    }


    public List<String> generateReport(Account[] accounts, LocalDate from, LocalDate to) {
        List<String> reports = new ArrayList<>();

        for(Account account : accounts) {
            if(account != null) {
                reports.add(generateReport(account));
            }
        }
        return reports;
    }

    @Override
    public String generateReport(Account account) {
        List<AccountEntry> entryList = account.getEntryList();
        double previousMonthBalance;
        double currentBalance = account.getBalance();

        LocalDate thisMonth = LocalDate.now().minusDays(30);

        //get last date of transaction before 30 days
        Optional<LocalDate> lastMonthLatest= entryList.stream().map(x -> x.getDate())
                .filter(date -> date.isBefore(thisMonth)).max( Comparator.comparing(LocalDate::toEpochDay ));

        //check if present
        if(lastMonthLatest.isPresent())
        {
            // get the entry that matches
            List<AccountEntry> etnries = entryList.stream().filter(entry -> entry.getDate().equals(lastMonthLatest.get())).toList();

            //last month's balance
            previousMonthBalance = etnries.get(0).getRemainingBalance();
        }
        else{
            //no previous balance (account newer than 1 month)
            previousMonthBalance = 0;
        }

        System.out.println("last Month's balance : " + previousMonthBalance + "current Month's balance" + currentBalance);



        if (account instanceof CreditCardAccount)
        {
//            - new balance = previous balance – total credits + total charges + MI * (previous balance
//– total credits)
//            - total due = MP * new balance


        }




        return null;
    }
}
