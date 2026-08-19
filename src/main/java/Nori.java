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
     * Greets the user, echoes commands, and exits when the user enters {@code bye}.
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
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                break;
            }
            if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + "." + tasks[i]);
                }
            } else if (command.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(command.substring(5)) - 1;
                tasks[taskIndex].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks[taskIndex]);
            } else if (command.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(command.substring(7)) - 1;
                tasks[taskIndex].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks[taskIndex]);
            } else {
                Task task;
                if (command.startsWith("todo ")) {
                    task = new Todo(command.substring(5));
                } else if (command.startsWith("deadline ")) {
                    int byIndex = command.indexOf(" /by ");
                    task = new Deadline(command.substring(9, byIndex), command.substring(byIndex + 5));
                } else if (command.startsWith("event ")) {
                    int fromIndex = command.indexOf(" /from ");
                    int toIndex = command.indexOf(" /to ");
                    task = new Event(command.substring(6, fromIndex),
                            command.substring(fromIndex + 7, toIndex), command.substring(toIndex + 5));
                } else {
                    task = new Todo(command);
                }
                tasks[taskCount] = task;
                taskCount++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task);
                System.out.println("Now you have " + taskCount + (taskCount == 1
                        ? " task in the list." : " tasks in the list."));
            }
            System.out.println(DIVIDER);
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }
}
