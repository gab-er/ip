package duke.task;

public class Task {
    private boolean completed;
    private final String description;
    private final int id;
    private static int globalId = 1; // Start from 1

    /**
     * Constructor for a Task object
     *
     * @param description The description of the task
     */
    public Task(String description, boolean completed) {
        this.completed = completed;
        this.description = description;
        this.id = globalId++;
    }

    public void markDone() {
        this.completed = true;
    }

    public void markNotDone() {
        this.completed = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public String getStatusIcon() {
        return this.completed ? "X" : " ";
    }

    public int getId() {
        return this.id;
    }

    public String toStorageString() {
        return String.format("TODO|%s|%s", this.getDescription(), this.getCompleted());
    }

    @Override
    public String toString() {
        String formattedString = String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
        return formattedString;
    }
}
