package jimmy;

import jimmy.task.Task;
import jimmy.task.TaskList;

import java.util.ArrayList;

public class Ui {
    private final String CHATBOT_NAME;
    private final String HORIZONTAL_DIVIDER;

    /**
     * Constructor for a Ui object.
     */
    public Ui(String HORIZONTAL_DIVIDER) {
        this.CHATBOT_NAME = "Jimmy";
        this.HORIZONTAL_DIVIDER = "_________________________________________________";
    }

    /**
     * Handles the greeting sent by the chatbot.
     */
    public void handleGreeting() {
        System.out.println(this.HORIZONTAL_DIVIDER);
        System.out.println("Hello! I'm " + this.CHATBOT_NAME);
        System.out.println("What can I do for you?");
        System.out.println(this.HORIZONTAL_DIVIDER + "\n"); // Add newline so user input is on next line
    }

    /**
     * Handles the exit message sent by the chatbot.
     */
    public void handleExit() {
        System.out.println(this.HORIZONTAL_DIVIDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(this.HORIZONTAL_DIVIDER);
    }

    /**
     * Displays the task list to the user.
     * @param taskList TaskList containing the stored tasks to do.
     */
    public void displayTaskList(TaskList taskList) {
        ArrayList<Task> storedTasks = taskList.getStoredTasks();
        System.out.println(HORIZONTAL_DIVIDER);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < storedTasks.size(); i++) {
            Task currentTask = storedTasks.get(i);
            String formattedString = String.format("%d.%s", i + 1, currentTask);
            System.out.println(formattedString);
        }
        System.out.println(HORIZONTAL_DIVIDER);
    }

    /**
     * Displays the marked task as done.
     * @param taskToMark Task to mark as done.
     */
    public void displayMarkDone(Task taskToMark) {
        System.out.println(HORIZONTAL_DIVIDER);
        System.out.println(String.format("Nice! I've marked this task as done:\n %s", taskToMark));
        System.out.println(HORIZONTAL_DIVIDER);
    }

    /**
     * Displays the unmarked task as not done.
     * @param taskToUnmark Task to unmark.
     */
    public void displayMarkNotDone(Task taskToUnmark) {
        System.out.println(HORIZONTAL_DIVIDER);
        System.out.println(String.format("Nice! I've marked this task as done:\n %s", taskToUnmark));
        System.out.println(HORIZONTAL_DIVIDER);
    }

    /**
     * Displays the newly added task.
     * @param newTask Newly added task.
     * @param taskList TaskList of stored tasks.
     */
    public void displayAddedTask(Task newTask, TaskList taskList) {
        ArrayList<Task> storedTasks = taskList.getStoredTasks();
        System.out.println(HORIZONTAL_DIVIDER);
        System.out.println(String.format("Got it. I've added this task:\n %s", newTask));
        System.out.println(String.format("Now you have %d tasks in the list.", storedTasks.size()));
        System.out.println(HORIZONTAL_DIVIDER);
    }

    /**
     * Displays the task that was just removed.
     * @param removedTask .Task that was just removed.
     * @param taskList TaskList of stored tasks.
     */
    public void displayRemovedTask(Task removedTask, TaskList taskList) {
        ArrayList<Task> storedTasks = taskList.getStoredTasks();
        System.out.println(HORIZONTAL_DIVIDER);
        System.out.println(String.format("Noted. I've removed this task:\n %s", removedTask));
        System.out.println(String.format("Now you have %d tasks in the list.", storedTasks.size()));
        System.out.println(HORIZONTAL_DIVIDER);
    }

    public void displayError(Exception e) {
        System.out.println(this.HORIZONTAL_DIVIDER);
        System.out.println(e.getMessage());
        System.out.println(this.HORIZONTAL_DIVIDER);
    }

    /**
     * Displays the tasks that are found containing the description.
     *
     * @param taskList taskList with stored tasks.
     * @param keyword Description of the task keyword.
     */
    public void displayFoundTasks(TaskList taskList, String keyword) {
        ArrayList<Task> tasks = taskList.findTasks(keyword);
        int count = 1;
        System.out.println(this.HORIZONTAL_DIVIDER);
        System.out.println("Here are the matching tasks in your list:");
        for (Task task: tasks) {
            System.out.println(String.format("%d.%s", count++, task));
        }
        System.out.println(this.HORIZONTAL_DIVIDER);
    }
}
