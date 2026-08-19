/**
 * Represents a task that must be completed by a specified time.
 */
public class Deadline extends Task {
    private final String by;

    /**
     * Creates a deadline.
     *
     * @param description description shown to the user
     * @param by deadline text supplied by the user
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
