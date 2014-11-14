package aurora;

public class CommandWrapper {
    private String command;

    public CommandWrapper() {
    }

    public CommandWrapper(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}