public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    public String toString() {
        String type = "T";
        return String.format("[%s]%s", type, super.toString());
    }
}
