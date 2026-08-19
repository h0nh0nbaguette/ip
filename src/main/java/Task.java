/**
 * Represents a task and whether it has been completed.
 */
public class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Creates an incomplete task with the given description.
     *
     * @param description description shown to the user
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** Marks this task as completed. */
    public void markAsDone() {
        isDone = true;
    }

    /** Marks this task as not completed. */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the completion marker used in task listings.
     *
     * @return {@code X} when completed, or a blank otherwise
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
