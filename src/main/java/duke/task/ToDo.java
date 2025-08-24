package duke.task;

public class ToDo extends Task {

    /**
     * Constructor for a ToDo object
     *
     * @param description The description of the task.
     */
    public ToDo(String description, boolean completed) {
        super(description, completed);
    }

    public String toStorageString() {
        return String.format("TODO|%s|%s", this.getDescription(), this.getCompleted());
    }

    @Override
    public String toString() {
        String type = "T";
        return String.format("[%s]%s", type, super.toString());
    }
}
