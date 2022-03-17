package framework.service.command;

import java.util.HashMap;

public class CommandInvoker {
    private HashMap<String, Command> commands;

    public CommandInvoker() {
        this.commands = new HashMap<>();
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }

    public void addCommand(String commandName, Command command) {
        this.commands.put(commandName, command);
    }
}
