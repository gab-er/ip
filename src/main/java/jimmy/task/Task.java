package jimmy.task;

/**
 * Represents a task in the TaskList.
 */
public class Task {
    private static int globalId = 1; // Start from 1
    private boolean completed;
    private final String description;
    private final int id;

    /**
     * Constructor for a Task object.
     *
     * @param description The description of the task.
     */
    public Task(String description, boolean completed) {
        this.completed = completed;
        this.description = description;
        this.id = globalId++;
    }

    /**
     * Marks a task as done.
     */
    public void markDone() {
        this.completed = true;
    }

    /**
     * Marks a task as not done.
     */
    public void markNotDone() {
        this.completed = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return Completion status of the task.
     */
    public boolean getCompleted() {
        return this.completed;
    }

    /**
     * Returns the appropriate status icon depending on the completion status of the task.
     *
     * @return Appropriate status icon depending on the completion status of the task.
     */
    public String getStatusIcon() {
        return this.completed ? "X" : " ";
    }

    /**
     * Returns the formatted string to be used for storing in the hard disk.
     *
     * @return Formatted string.
     */
    public String toStorageString() {
        return String.format("TODO|%s|%s", this.getDescription(), this.getCompleted());
    }

    /**
     * Returns the string representation of this task.
     *
     * @return String representation of this task.
     */
    @Override
    public String toString() {
        String formattedString = String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
        return formattedString;
    }
}
