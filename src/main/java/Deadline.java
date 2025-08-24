public class Deadline extends Task {
    private String deadline;

    /**
     * Constructor for a Deadline object
     *
     * @param description The description of the task
     * @param deadline    The deadline of the task
     */
    public Deadline(String description, boolean completed, String deadline) {
        super(description, completed);
        this.deadline = deadline;
    }

    public String toStorageString() {
        return String.format("DEADLINE|%s|%s|%s", this.getDescription(), this.getCompleted(), this.deadline);
    }

    @Override
    public String toString() {
        String type = "D";
        return String.format("[%s]%s (by: %s)", type, super.toString(), this.deadline);
    }
}
