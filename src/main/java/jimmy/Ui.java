package jimmy;

import java.util.ArrayList;
import java.util.Scanner;

import jimmy.task.Task;
import jimmy.task.TaskList;

/**
 * Represents an Ui object.
 */
public class Ui {
    private static final String CHATBOT_NAME = "Jimmy";
    private static final String HORIZONTAL_DIVIDER = "___________________________________________";
    private Scanner scan;

    /**
     * Constructs a Ui object.
     */
    public Ui() {
        this.scan = new Scanner(System.in);
    }

    /**
     * Reads the next command using the Scanner.
     *
     * @return Next command entered by the user.
     */
    public String readCommand() {
        return scan.nextLine();
    }

    /**
     * Displays the greeting sent by the chatbot.
     *
     * @return String to be shown on the GUI
     */
    public String displayGreeting() {
        return String.format("%s\n "
                + "Hey! I'm %s\n"
                + "What can I do for you?" + "%s", HORIZONTAL_DIVIDER, CHATBOT_NAME, HORIZONTAL_DIVIDER);
    }

    /**
     * Displays the exit message sent by the chatbot.
     *
     * @return String to be shown on the GUI
     */
    public String displayExit() {
        return String.format("%s\n "
                        + "Bye! Hope to see you again soon!\n"
                        + "%s", HORIZONTAL_DIVIDER, HORIZONTAL_DIVIDER);
    }

    /**
     * Displays the task list to the user.
     *
     * @param taskList TaskList containing the stored tasks to do.
     * @return String to be shown on the GUI
     */
    public String displayTaskList(TaskList taskList) {
        return String.format("%s\nHere are the tasks in your list:\n%s %s", HORIZONTAL_DIVIDER, taskList,
                HORIZONTAL_DIVIDER);

    }

    /**
     * Displays the marked task as done.
     *
     * @param taskToMark Task to mark as done.
     * @return String to be shown on the GUI
     */
    public String displayMarkDone(Task taskToMark) {
        return String.format("%s\nNice! I've marked this task as done:\n %s %s",
                HORIZONTAL_DIVIDER, taskToMark, HORIZONTAL_DIVIDER);
    }

    /**
     * Displays the unmarked task as not done.
     *
     * @param taskToUnmark Task to unmark.
     * @return String to be shown on the GUI
     */
    public String displayMarkNotDone(Task taskToUnmark) {
        return String.format("%s\nNice! I've marked this task as not done:\n %s %s",
                HORIZONTAL_DIVIDER, taskToUnmark, HORIZONTAL_DIVIDER);
    }

    /**
     * Displays the newly added task.
     *
     * @param newTask  Newly added task.
     * @param taskList TaskList of stored tasks.
     * @return String to be shown on the GUI
     */
    public String displayAddedTask(Task newTask, TaskList taskList) {
        return String.format("%s\n"
                        + "Got it. I've added this task:\n %s\n"
                        + "Now you have %d tasks in the list.\n" + "%s",
                HORIZONTAL_DIVIDER, newTask, taskList.size(), HORIZONTAL_DIVIDER);
    }

    /**
     * Displays the task that was just removed.
     *
     * @param removedTask .Task that was just removed.
     * @param taskList    TaskList of stored tasks.
     * @return String to be shown on the GUI
     */
    public String displayRemovedTask(Task removedTask, TaskList taskList) {
        return String.format("%s\n"
                        + "Noted. I've removed this task:\n %s\n"
                        + "Now you have %d tasks in the list."
                        + "%s",
                HORIZONTAL_DIVIDER, removedTask, taskList.size(), HORIZONTAL_DIVIDER);
    }

    /**
     * Displays the error message provided by an Exception.
     *
     * @param e Exception provided.
     * @return String to be shown on the GUI
     */
    public String displayError(Exception e) {
        return String.format("%s\n "
                + "%s\n"
                + "%s", HORIZONTAL_DIVIDER, e.getMessage(), HORIZONTAL_DIVIDER);
    }

    /**
     * Displays the tasks that are found containing the description.
     *
     * @param taskList taskList with stored tasks.
     * @param keyword  Description of the task keyword.
     * @return String to be shown on the GUI
     */
    public String displayFoundTasks(TaskList taskList, String keyword) {
        ArrayList<Task> tasks = taskList.findTasks(keyword);
        assert tasks != null;

        StringBuilder sb = new StringBuilder();

        int count = 1;
        sb.append(String.format("%s\n"
                + "Here are the matching tasks in your list:", HORIZONTAL_DIVIDER));
        for (Task task : tasks) {
            sb.append(String.format("%d.%s", count++, task));
        }
        sb.append(HORIZONTAL_DIVIDER);
        return sb.toString();
    }
}
