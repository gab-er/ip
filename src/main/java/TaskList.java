import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> storedTasks;

    /**
     * Constructor for a TaskList object.
     * @param storedTasks ArrayList of stored tasks.
     */
    public TaskList(ArrayList<Task> storedTasks) {
        this.storedTasks = storedTasks;
    }

    /**
     * Returns the marked task.
     * @param parsedInt The position of the marked task in the list.
     * @return Marked task.
     */
    public Task markDone(int parsedInt) {
        Task taskToMark = storedTasks.get(parsedInt - 1);
        taskToMark.markDone(); // Mark task as done
        return taskToMark;
    }

    /**
     * Returns the unmarked task.
     * @param parsedInt The position of the unmarked task in the list.
     * @return Unmarked task.
     */
    public Task markNotDone(int parsedInt) {
        Task taskToUnmark = storedTasks.get(parsedInt - 1);
        taskToUnmark.markNotDone(); // Mark task as done
        return taskToUnmark;
    }

    /**
     * Adds the given task to the list of tasks.
     * @param task Task to add to the list.
     */
    public void addTask(Task task) {
        this.storedTasks.add(task);
    }

    /**
     * Returns the task that was deleted.
     * @param taskNumber Position of the task in the list to delete.
     * @return Task that was deleted.
     */
    public Task removeTask(int taskNumber) {
        Task taskToDelete = storedTasks.get(taskNumber);
        this.storedTasks.remove(taskNumber);
        return taskToDelete;
    }

    public ArrayList<Task> getStoredTasks() {
        return this.storedTasks;
    }
}
