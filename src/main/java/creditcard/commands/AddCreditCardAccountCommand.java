package creditcard.commands;

import framework.model.Account;
import framework.service.AccountService;
import framework.service.command.Command;

public class AddCreditCardAccountCommand implements Command {
    AccountService accountService;

    public AddCreditCardAccountCommand(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void execute(Object... args) throws Exception {
        accountService.saveAccount((Account) args[0]);
    }
}
