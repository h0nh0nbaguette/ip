import java.util.Scanner;

/**
 * Entry point for Nori, a command-line task assistant.
 */
public class Nori {
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
            System.out.println(command);
            System.out.println(DIVIDER);
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }
}
