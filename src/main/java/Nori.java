import java.nio.file.Path;

/**
 * Coordinates Nori's user interface, command parsing, task list, and storage.
 */
public class Nori {
    private static final Path DATA_FILE_PATH = Path.of("data", "nori.txt");

    private final Ui ui;
    private final Storage storage;
    private final Parser parser;
    private TaskList tasks;

    /** Creates Nori with its command-line interface and default data file. */
    public Nori() {
        this.ui = new Ui();
        this.storage = new Storage(DATA_FILE_PATH);
        this.parser = new Parser();
    }

    /** Starts Nori and processes commands until the user enters {@code bye}. */
    public void run() {
        ui.showWelcome();
        tasks = loadTasks();

        while (ui.hasNextCommand()) {
            String command = ui.readCommand();
            CommandType commandType = parser.parseCommandType(command);
            if (commandType == CommandType.BYE) {
                break;
            }
            try {
                executeCommand(command, commandType);
            } catch (NoriException exception) {
                ui.showError(exception.getMessage());
            }
            ui.showDivider();
        }

        ui.showGoodbye();
    }

    /** Executes one parsed user command. */
    private void executeCommand(String command, CommandType commandType) throws NoriException {
        switch (commandType) {
            case EMPTY -> throw new NoriException("Please enter a command.");
            case LIST -> ui.showTaskList(tasks);
            case MARK -> updateTaskStatus(command, true);
            case UNMARK -> updateTaskStatus(command, false);
            case DELETE -> deleteTask(command);
            case TODO, DEADLINE, EVENT -> addTask(command);
            case UNKNOWN -> throw new NoriException("I don't know that command.");
            case BYE -> throw new AssertionError("bye should be handled before the command switch");
            default -> throw new AssertionError("Every command type should be handled");
        }
    }

    /** Adds a task described by a user command and persists the updated list. */
    private void addTask(String command) throws NoriException {
        Task task = parser.parseTask(command);
        tasks.add(task);
        storage.save(tasks);
        ui.showTaskAdded(task, tasks.size());
    }

    /** Deletes the selected task and persists the updated list. */
    private void deleteTask(String command) throws NoriException {
        int taskIndex = parser.parseTaskIndex(command, "delete", tasks.size());
        Task removedTask = tasks.delete(taskIndex);
        storage.save(tasks);
        ui.showTaskDeleted(removedTask, tasks.size());
    }

    /** Changes a task's completion state and persists the updated list. */
    private void updateTaskStatus(String command, boolean isDone) throws NoriException {
        String commandWord = isDone ? "mark" : "unmark";
        int taskIndex = parser.parseTaskIndex(command, commandWord, tasks.size());
        Task task = isDone ? tasks.mark(taskIndex) : tasks.unmark(taskIndex);
        storage.save(tasks);
        ui.showTaskStatusChanged(task, isDone);
    }

    /** Loads persisted tasks, recovering with an empty list after a loading error. */
    private TaskList loadTasks() {
        try {
            return storage.load();
        } catch (NoriException exception) {
            ui.showError(exception.getMessage());
            ui.showDivider();
            return new TaskList();
        }
    }

    /**
     * Starts a command-line Nori session.
     *
     * @param args command-line arguments, which are not used
     */
    public static void main(String[] args) {
        new Nori().run();
    }
}
