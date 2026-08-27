package nori.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Owns the task collection and provides task-list operations.
 */
public class TaskList implements Iterable<Task> {
    private final ArrayList<Task> tasks;

    /** Creates an empty task list. */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list containing the supplied tasks.
     *
     * @param tasks initial tasks copied into the list
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /** Adds a task to the end of the list. */
    public void add(Task task) {
        tasks.add(task);
    }

    /** Removes and returns the task at an index. */
    public Task delete(int index) {
        return tasks.remove(index);
    }

    /** Marks and returns the task at an index. */
    public Task mark(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    /** Unmarks and returns the task at an index. */
    public Task unmark(int index) {
        Task task = tasks.get(index);
        task.markAsNotDone();
        return task;
    }

    /** Returns the task at an index. */
    public Task get(int index) {
        return tasks.get(index);
    }

    /** Returns the number of tasks in the list. */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns tasks whose descriptions contain the given keyword.
     *
     * @param keyword case-sensitive text to find in task descriptions.
     * @return matching tasks in their original order
     */
    public TaskList find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return new TaskList(matchingTasks);
    }

    /** Returns a read-only iterator over the tasks. */
    @Override
    public Iterator<Task> iterator() {
        return Collections.unmodifiableList(tasks).iterator();
    }
}
