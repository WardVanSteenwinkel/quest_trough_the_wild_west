public enum CommandWord {
    GO("go"), LOOK("look"), TAKE("take"), DROP("drop"), EAT("eat"), QUIT("quit"),
    HELP("help"),STATS("stats"), BACK("back"), UNKNOWN("?");

    private String commandString;

    CommandWord(String commandString) {
        this.commandString = commandString;
    }

    public String toString() {
        return commandString;
    }
}
