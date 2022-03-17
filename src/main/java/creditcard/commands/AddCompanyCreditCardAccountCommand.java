package creditcard.commands;

import framework.model.Account;
import framework.service.AccountService;
import framework.service.command.Command;

public class AddCompanyCreditCardAccountCommand implements Command {
    AccountService accountService;

    public AddCompanyCreditCardAccountCommand(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void execute(Object... args) throws Exception {
        accountService.saveAccount((Account) args[0]);
    }
}
