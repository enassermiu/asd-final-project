package framework.report;

import framework.model.Account;

public interface Report {
    String generateReport(Account account);
}
