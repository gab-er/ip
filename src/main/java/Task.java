public class Task {
    private boolean completed;
    private final String description;
    private int id;
    private static int globalId = 1; // Start from 1

    public Task(String description) {
        this.completed = false;
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

}
