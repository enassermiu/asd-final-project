package creditcard.commands;

import framework.model.Account;
import framework.service.AccountService;
import framework.service.command.Command;

public class GenerateMonthlyBillCommand implements Command {
    AccountService accountService;

    public GenerateMonthlyBillCommand(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void execute(Object... args) throws Exception {
//        TODO
    }
}
