package creditcard.commands;

import framework.model.Account;
import framework.service.AccountService;
import framework.service.command.Command;

public class AddInterestCommand implements Command {
    AccountService accountService;

    public AddInterestCommand(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void execute(Object... args) throws Exception {
        accountService.addInterest();
    }
}
