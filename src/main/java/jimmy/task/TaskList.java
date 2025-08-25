package jimmy.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> storedTasks;

    /**
     * Constructor for a TaskList object.
     */
    public TaskList() {
        this.storedTasks = new ArrayList<Task>();
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
     * @param taskNumber Position of the task in the list to delete and already accounts for zero-indexed positions.
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

    public int size() {
        return this.storedTasks.size();
    }

    public Task getTask(int num) {
        return this.storedTasks.get(num - 1);
    }

    /**
     * Returns an ArrayList of tasks that contain the keyword.
     *
     * @param keyword String keyword to be found.
     * @return ArrayList of tasks that contain the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        for (Task task: this.storedTasks) {
            if (task.getDescription().contains(keyword)) {
                tasks.add(task);
            }
        }
        return tasks;
    }
}
