public class Event extends Task {
    private String start;
    private String end;

    /**
     * Constructor for an Event object
     *
     * @param description The description of the task
     * @param start       The start time of the task
     * @param end         The end time of the task
     */
    public Event(String description, boolean completed, String start, String end) {
        super(description, completed);
        this.start = start;
        this.end = end;
    }

    public String toStorageString() {
        return String.format("EVENT|%s|%s|%s|%s", this.getDescription(), this.getCompleted(), this.start, this.end);
    }

    @Override
    public String toString() {
        String type = "E";
        return String.format("[%s]%s (from: %s to: %s)", type, super.toString(), this.start, this.end);
    }
}

