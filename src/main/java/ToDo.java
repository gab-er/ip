public class ToDo extends Task {

    /**
     * Constructor for a ToDo object
     *
     * @param description The description of the task
     */
    public ToDo(String description) {
        super(description);
    }

    public String toString() {
        String type = "T";
        return String.format("[%s]%s", type, super.toString());
    }
}
