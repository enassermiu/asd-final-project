package framework.report;

import creditcard.model.CreditCardAccount;
import framework.model.AccountEntry;
import framework.service.AccountService;
import framework.service.AccountServiceImpl;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class CreditReportServiceImpl implements ReportService {

    AccountService accountService;

    public CreditReportServiceImpl() {
        this.accountService = AccountServiceImpl.getInstance();
    }

    public String generateReport() {

        StringBuilder report = new StringBuilder();

        accountService.getAllAccounts().forEach(account -> {
            report.append(generateReport((CreditCardAccount) account));
            report.append(System.lineSeparator());
            report.append("----------------------------------------------");
            report.append(System.lineSeparator());
            report.append(System.lineSeparator());
        });

        return report.toString();
    }

    public String generateReport(CreditCardAccount account) {
        double totalCharges = 0, totalCredits = 0;
        Month thisMonth = LocalDate.now().getMonth();

        List<AccountEntry> lastMonthTransactions = account.getEntryList().stream()
                .filter(entry -> entry.getDate().getMonth().equals(thisMonth))
                .toList();

        for (AccountEntry e : lastMonthTransactions) {
            if (e.getAmount() < 0)
                totalCharges += -e.getAmount();
            else
                totalCredits += e.getAmount();
        }

        double previousBalance = account.getBalance() - totalCredits + totalCharges;
        double newBalance = previousBalance - totalCredits + totalCharges +
                account.getMI() * (previousBalance - totalCredits);
        double totalDue = account.getMP() * newBalance;

        return account.getCustomer().getName() + ": " +
                account.getAccountTypeCode() + "-" + account.getAccountNumber() + System.lineSeparator() +
                "Last Month's balance : '" + previousBalance + "'" + System.lineSeparator() +
                "Total Charges for this Month : '" + totalCharges + "'" + System.lineSeparator() +
                "Total Credits for this Month : '" + totalCredits + "'" + System.lineSeparator() +
                "New balance : '" + newBalance + "'" + System.lineSeparator() +
                "Total due : '" + totalDue + "'";
    }
}
