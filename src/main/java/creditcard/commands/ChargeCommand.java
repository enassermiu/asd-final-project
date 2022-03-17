package creditcard.commands;

import framework.model.Account;
import framework.service.AccountService;
import framework.service.command.Command;

public class ChargeCommand implements Command {
    AccountService accountService;

    public ChargeCommand(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void execute(Object... args) throws Exception {
        String accountNumber = (String) args[0];
        double amount = Double.parseDouble((String) args[1]);
        accountService.withdraw(accountNumber, amount);
    }
}
