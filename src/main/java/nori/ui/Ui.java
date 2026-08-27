package nori.ui;

import java.util.Scanner;

import nori.task.Task;
import nori.task.TaskList;

/**
 * Reads commands from the user and displays Nori's responses.
 */
public class Ui {
    private static final String DIVIDER = "____________________________________________________________";
    private static final String BANNER = " _   _            _ \n"
            + "| \\ | | ___  _ __(_) \n"
            + "|  \\| |/ _ \\| '__| |\n"
            + "| |\\  | (_) | |  | |\n"
            + "|_| \\_|\\___/|_|  |_|\n";

    private final Scanner scanner;

    /** Creates a command-line UI connected to standard input. */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /** Displays Nori's greeting. */
    public void showWelcome() {
        showDivider();
        System.out.println(BANNER);
        System.out.println("Hello! I'm Nori.");
        System.out.println("What can I do for you?");
        showDivider();
    }

    /** Returns whether another command is available from the input stream. */
    public boolean hasNextCommand() {
        return scanner.hasNextLine();
    }

    /** Reads and trims the next command. */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /** Displays all tasks with one-based list numbers. */
    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        showNumberedTasks(tasks);
    }

    /**
     * Displays tasks that match a find command.
     *
     * @param tasks matching tasks to display.
     */
    public void showMatchingTasks(TaskList tasks) {
        System.out.println("Here are the matching tasks in your list:");
        showNumberedTasks(tasks);
    }

    /** Displays tasks with one-based list numbers. */
    private void showNumberedTasks(TaskList tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    /** Displays confirmation that a task was added. */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        showTaskCount(taskCount);
    }

    /** Displays confirmation that a task was deleted. */
    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        showTaskCount(taskCount);
    }

    /** Displays confirmation that a task's completion state changed. */
    public void showTaskStatusChanged(Task task, boolean isDone) {
        String message = isDone
                ? "Nice! I've marked this task as done:"
                : "OK, I've marked this task as not done yet:";
        System.out.println(message);
        System.out.println("  " + task);
    }

    /** Displays a recoverable error. */
    public void showError(String message) {
        System.out.println("OOPS!!! " + message);
    }

    /** Displays the response separator. */
    public void showDivider() {
        System.out.println(DIVIDER);
    }

    /** Displays Nori's farewell. */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        showDivider();
    }

    /** Displays the task count with correct singular or plural wording. */
    private void showTaskCount(int taskCount) {
        System.out.println("Now you have " + taskCount + (taskCount == 1
                ? " task in the list." : " tasks in the list."));
    }
}
