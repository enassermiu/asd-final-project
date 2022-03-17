package framework.service.command;

public interface Command {
    public void execute(Object... args) throws Exception;
}
