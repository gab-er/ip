package jimmy;

import java.util.ArrayList;

import jimmy.task.Task;
import jimmy.task.TaskList;

/**
 * Represents an Ui object.
 */
public class Ui {
    private static final String CHATBOT_NAME = "Jimmy";
    private static final String HORIZONTAL_DIVIDER = "___________________________________________";

    /**
     * Constructs a Ui object.
     */
    public Ui() {
    }

    /**
     * Handles the greeting sent by the chatbot.
     */
    public void handleGreeting() {
        System.out.println(HORIZONTAL_DIVIDER);
        System.out.println("Hey! I'm " + CHATBOT_NAME);
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_DIVIDER + "\n"); // Add newline so user input is on next line
    }

    /**
     * Handles the exit message sent by the chatbot.
     */
    public void handleExit() {
        System.out.println(HORIZONTAL_DIVIDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_DIVIDER);
    }

    /**
     * Displays the task list to the user.
     *
     * @param taskList TaskList containing the stored tasks to do.
     */
    public String displayTaskList(TaskList taskList) {
        ArrayList<Task> storedTasks = taskList.getStoredTasks();
        System.out.println(HORIZONTAL_DIVIDER);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < storedTasks.size(); i++) {
            Task currentTask = storedTasks.get(i);
            String formattedString = String.format("%d.%s", i + 1, currentTask);
            System.out.println(formattedString);
        }
        System.out.println(HORIZONTAL_DIVIDER);
        return String.format("%s\nHere are the tasks in your list:\n%s %s", HORIZONTAL_DIVIDER, taskList, HORIZONTAL_DIVIDER);

    }

    /**
     * Displays the marked task as done.
     *
     * @param taskToMark Task to mark as done.
     */
    public String displayMarkDone(Task taskToMark) {
        System.out.println(HORIZONTAL_DIVIDER);
        System.out.println(String.format("Nice! I've marked this task as done:\n %s", taskToMark));
        System.out.println(HORIZONTAL_DIVIDER);
        return String.format("%s\nNice! I've marked this task as done:\n %s %s",
                HORIZONTAL_DIVIDER, taskToMark, HORIZONTAL_DIVIDER);
    }

    /**
     * Displays the unmarked task as not done.
     *
     * @param taskToUnmark Task to unmark.
     */
    public String displayMarkNotDone(Task taskToUnmark) {
        System.out.println(HORIZONTAL_DIVIDER);
        System.out.println(String.format("Nice! I've marked this task as not done:\n %s", taskToUnmark));
        System.out.println(HORIZONTAL_DIVIDER);
        return String.format("%s\nNice! I've marked this task as not done:\n %s %s",
                HORIZONTAL_DIVIDER, taskToUnmark, HORIZONTAL_DIVIDER);
    }

    /**
     * Displays the newly added task.
     *
     * @param newTask  Newly added task.
     * @param taskList TaskList of stored tasks.
     */
    public String displayAddedTask(Task newTask, TaskList taskList) {
        System.out.println(HORIZONTAL_DIVIDER);
        System.out.println(String.format("Got it. I've added this task:\n %s", newTask));
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));
        System.out.println(HORIZONTAL_DIVIDER);
        return String.format("%s\n" +
                        "Got it. I've added this task:\n %s\n" +
                        "Now you have %d tasks in the list." +
                        "%s",
                HORIZONTAL_DIVIDER, newTask, taskList.size(), HORIZONTAL_DIVIDER);
    }

    /**
     * Displays the task that was just removed.
     *
     * @param removedTask .Task that was just removed.
     * @param taskList    TaskList of stored tasks.
     */
    public String displayRemovedTask(Task removedTask, TaskList taskList) {
        System.out.println(HORIZONTAL_DIVIDER);
        System.out.println(String.format("Noted. I've removed this task:\n %s", removedTask));
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));
        System.out.println(HORIZONTAL_DIVIDER);
        return String.format("%s\n" +
                        "Noted. I've removed this task:\n %s\n" +
                        "Now you have %d tasks in the list." +
                        "%s",
                HORIZONTAL_DIVIDER, removedTask, taskList.size(), HORIZONTAL_DIVIDER);
    }

    /**
     * Displays the error message provided by an Exception.
     *
     * @param e Exception provided.
     */
    public String displayError(Exception e) {
        System.out.println(HORIZONTAL_DIVIDER);
        System.out.println(e.getMessage());
        System.out.println(HORIZONTAL_DIVIDER);
        return String.format("%s\n " +
                "%s\n" +
                "%s", HORIZONTAL_DIVIDER, e.getMessage(), HORIZONTAL_DIVIDER);
    }

    /**
     * Displays the tasks that are found containing the description.
     *
     * @param taskList taskList with stored tasks.
     * @param keyword  Description of the task keyword.
     */
    public String displayFoundTasks(TaskList taskList, String keyword) {
        ArrayList<Task> tasks = taskList.findTasks(keyword);
        StringBuilder sb = new StringBuilder();

        int count = 1;
        System.out.println(HORIZONTAL_DIVIDER);
        System.out.println("Here are the matching tasks in your list:");
        sb.append(String.format("%s\n" +
                "Here are the matching tasks in your list:", HORIZONTAL_DIVIDER));
        for (Task task : tasks) {
            System.out.println(String.format("%d.%s", count++, task));
            sb.append(String.format("%d.%s", count++, task));
        }
        System.out.println(HORIZONTAL_DIVIDER);
        sb.append(HORIZONTAL_DIVIDER);
        return sb.toString();
    }
}
