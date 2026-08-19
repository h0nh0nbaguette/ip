import java.util.Scanner;

/**
 * Entry point for Nori, a command-line task assistant.
 */
public class Nori {
    private static final int MAX_TASKS = 100;
    private static final String DIVIDER = "____________________________________________________________";
    private static final String BANNER = " _   _            _ \n"
            + "| \\ | | ___  _ __(_) \n"
            + "|  \\| |/ _ \\| '__| |\n"
            + "| |\\  | (_) | |  | |\n"
            + "|_| \\_|\\___/|_|  |_|\n";

    /**
     * Greets the user, manages tasks, and exits when the user enters {@code bye}.
     *
     * @param args command-line arguments, which are not used
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[MAX_TASKS];
        int taskCount = 0;
        System.out.println(DIVIDER);
        System.out.println(BANNER);
        System.out.println("Hello! I'm Nori.");
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER);

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine().trim();
            if (command.equals("bye")) {
                break;
            }
            try {
                if (command.isEmpty()) {
                    throw new NoriException("Please enter a command.");
                } else if (command.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + "." + tasks[i]);
                    }
                } else if (command.equals("mark") || command.startsWith("mark ")) {
                    int taskIndex = parseTaskIndex(command, "mark", taskCount);
                    tasks[taskIndex].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks[taskIndex]);
                } else if (command.equals("unmark") || command.startsWith("unmark ")) {
                    int taskIndex = parseTaskIndex(command, "unmark", taskCount);
                    tasks[taskIndex].markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks[taskIndex]);
                } else if (isTaskCommand(command)) {
                    if (taskCount == MAX_TASKS) {
                        throw new NoriException("Your task list is full.");
                    }
                    Task task = parseTask(command);
                    tasks[taskCount] = task;
                    taskCount++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + task);
                    System.out.println("Now you have " + taskCount + (taskCount == 1
                            ? " task in the list." : " tasks in the list."));
                } else {
                    throw new NoriException("I don't know that command.");
                }
            } catch (NoriException exception) {
                System.out.println("OOPS!!! " + exception.getMessage());
            }
            System.out.println(DIVIDER);
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    private static boolean isTaskCommand(String command) {
        return command.equals("todo") || command.startsWith("todo ")
                || command.equals("deadline") || command.startsWith("deadline ")
                || command.equals("event") || command.startsWith("event ");
    }

    private static Task parseTask(String command) throws NoriException {
        if (command.equals("todo")) {
            throw new NoriException("The description of a todo cannot be empty.");
        }
        if (command.startsWith("todo ")) {
            String description = command.substring(5).trim();
            if (description.isEmpty()) {
                throw new NoriException("The description of a todo cannot be empty.");
            }
            return new Todo(description);
        }
        if (command.equals("deadline")) {
            throw new NoriException("Use: deadline DESCRIPTION /by DATE_OR_TIME");
        }
        if (command.startsWith("deadline ")) {
            int byIndex = command.indexOf(" /by ");
            if (byIndex < 0) {
                throw new NoriException("Use: deadline DESCRIPTION /by DATE_OR_TIME");
            }
            String description = byIndex < 9 ? "" : command.substring(9, byIndex).trim();
            String by = command.substring(byIndex + 5).trim();
            if (description.isEmpty() || by.isEmpty()) {
                throw new NoriException("A deadline needs both a description and /by value.");
            }
            return new Deadline(description, by);
        }
        if (command.equals("event")) {
            throw new NoriException("Use: event DESCRIPTION /from START /to END");
        }
        int fromIndex = command.indexOf(" /from ");
        int toIndex = command.indexOf(" /to ");
        if (fromIndex < 0 || toIndex < 0 || toIndex <= fromIndex) {
            throw new NoriException("Use: event DESCRIPTION /from START /to END");
        }
        String description = command.substring(6, fromIndex).trim();
        String from = command.substring(fromIndex + 7, toIndex).trim();
        String to = command.substring(toIndex + 5).trim();
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new NoriException("An event needs a description, /from value, and /to value.");
        }
        return new Event(description, from, to);
    }

    private static int parseTaskIndex(String command, String commandWord, int taskCount)
            throws NoriException {
        String indexText = command.substring(commandWord.length()).trim();
        if (indexText.isEmpty()) {
            throw new NoriException("Please provide a task number after " + commandWord + ".");
        }
        final int taskNumber;
        try {
            taskNumber = Integer.parseInt(indexText);
        } catch (NumberFormatException exception) {
            throw new NoriException("The task number must be a whole number.");
        }
        if (taskNumber < 1 || taskNumber > taskCount) {
            throw new NoriException("That task number is not in your list.");
        }
        return taskNumber - 1;
    }
}
