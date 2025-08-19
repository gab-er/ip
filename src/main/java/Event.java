public class Event extends Task{
    private String start;
    private String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public String toString() {
        String type = "E";
        return String.format("[%s]%s (from: %s to: %s)", type, super.toString(), this.start, this.end);
    }
}

