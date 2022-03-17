package framework.report;

import framework.model.Account;
import framework.model.AccountEntry;
import framework.service.AccountService;
import framework.service.AccountServiceImpl;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class BankReportServiceImpl implements ReportService {

    AccountService accountService;

    public BankReportServiceImpl(){
        this.accountService = AccountServiceImpl.getInstance();
    }

    public String generateReport() {

        StringBuilder report = new StringBuilder();

        accountService.getAllAccounts().forEach(account -> {
            report.append(generateReport(account));
            report.append(System.lineSeparator());
            report.append("----------------------------------------------");
            report.append(System.lineSeparator());
            report.append(System.lineSeparator());
        });

        return report.toString();
    }

    public String generateReport(Account account) {
        double totalCharges = 0, totalCredits = 0;

        Month thisMonth = LocalDate.now().getMonth();

        List<AccountEntry> lastMonthTransactions = account.getEntryList().stream()
                .filter(entry -> entry.getDate().getMonth().equals(thisMonth))
                .toList();

        for (AccountEntry e : lastMonthTransactions) {
            if (e.getAmount() < 0)
                totalCharges += e.getAmount();
            else
                totalCredits += e.getAmount();
        }

        double previousBalance = account.getBalance() - totalCredits + totalCharges;

        return account.getCustomer().getName() + ": " +
                account.getAccountTypeCode()  + "-" + account.getAccountNumber() + System.lineSeparator()  +
                "Last Month's balance : '" + previousBalance + "'" + System.lineSeparator() +
                "Current Month's balance: '" + account.getBalance() + "'" ;
    }
}
