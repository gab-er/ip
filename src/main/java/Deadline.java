public class Deadline extends Task {
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String toString() {
        String type = "D";
        return String.format("[%s]%s (by: %s)", type, super.toString(), this.deadline);
    }
}
