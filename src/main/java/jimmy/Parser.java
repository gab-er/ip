package jimmy;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jimmy.exception.JimmyException;
import jimmy.task.Deadline;
import jimmy.task.Event;
import jimmy.task.Task;
import jimmy.task.TaskList;
import jimmy.task.ToDo;

/**
 * Represents a Parser object.
 */
public class Parser {
    private Ui ui;
    private Storage taskStorage;
    private TaskList taskList;

    /**
     * Constructs a Parser object.
     *
     * @param ui          Ui object
     * @param taskStorage Storage object
     * @param taskList    TaskList of stored tasks.
     */
    public Parser(Ui ui, Storage taskStorage, TaskList taskList) {
        this.ui = ui;
        this.taskStorage = taskStorage;
        this.taskList = taskList;
    }

    /**
     * Handles the input sent by the user and responds accordingly.
     *
     * @param command The string input that is typed by the user.
     * @param scan    The scanner object used by the chatbot.
     */
    public void parse(String command, Scanner scan) {
        // Initialise all necessary regular expression patterns
        String markPattern = "mark \\d+";
        String unmarkPattern = "unmark \\d+";
        String listPattern = "list";
        String byePattern = "bye";
        String toDoPattern = "todo\\s+(.+)"; // to-do, whitespace, any sequence of words
        String deadlinePattern = "deadline\\s+(.+)\\s+(/by (.+))";
        String eventPattern = "event\\s+(.+)\\s+(/from (.+))\\s+(/to (.+))";
        String deletePattern = "delete (\\d+)";
        String findPattern = "find (.+)";

        // Only exit if "bye" is inputted
        while (!command.matches(byePattern)) {
            try {
                // Verify the contents of the command using regular expressions
                if (command.matches(listPattern)) {
                    // List: Display stored text
                    this.ui.displayTaskList(this.taskList);

                } else if (command.matches(markPattern)) {
                    // Mark: mark task as done
                    String[] splitCommand = command.split(" ");
                    try {
                        int parsedInt = Integer.parseInt(splitCommand[1]); // Try to convert the string into an int
                        Task taskToMark = this.taskList.markDone(parsedInt);
                        this.ui.displayMarkDone(taskToMark);

                    } catch (Exception e) {
                        // Catch invalid inputs
                        throw new JimmyException("Oops! Please mark a valid task!");
                    }
                } else if (command.matches(unmarkPattern)) {
                    // Unmark: mark task as not done
                    String[] splitCommand = command.split(" ");
                    try {
                        int parsedInt = Integer.parseInt(splitCommand[1]); // Try to convert the string into an int
                        Task taskToUnmark = this.taskList.markNotDone(parsedInt);
                        this.ui.displayMarkNotDone(taskToUnmark);
                    } catch (Exception e) {
                        // Catch invalid inputs
                        System.out.println(e);
                    }
                } else if (command.matches(toDoPattern)) {
                    Matcher m = Pattern.compile(toDoPattern).matcher(command);
                    if (m.find()) {
                        String commandDescription = m.group(1);
                        ToDo newToDo = new ToDo(commandDescription, false);
                        this.taskList.addTask(newToDo);
                        this.ui.displayAddedTask(newToDo, this.taskList);
                    }
                } else if (command.matches(deadlinePattern)) {
                    Matcher m = Pattern.compile(deadlinePattern).matcher(command);
                    if (m.find()) {
                        String commandDescription = m.group(1);
                        String deadline = m.group(3);
                        Deadline newDeadline = new Deadline(commandDescription, false, deadline);
                        this.taskList.addTask(newDeadline);
                        this.ui.displayAddedTask(newDeadline, this.taskList);
                    }

                } else if (command.matches(eventPattern)) {
                    Matcher m = Pattern.compile(eventPattern).matcher(command);
                    if (m.find()) {
                        String commandDescription = m.group(1);
                        String start = m.group(3);
                        String end = m.group(5);
                        Event newEvent = new Event(commandDescription, false, start, end);
                        this.taskList.addTask(newEvent);
                        this.ui.displayAddedTask(newEvent, this.taskList);
                    }
                } else if (command.matches(deletePattern)) {
                    Matcher m = Pattern.compile(deletePattern).matcher(command);
                    if (m.find()) {
                        int taskNumberToDelete = Integer.parseInt(m.group(1)) - 1;
                        Task taskToDelete = this.taskList.removeTask(taskNumberToDelete);
                        this.ui.displayRemovedTask(taskToDelete, this.taskList);
                    }
                } else if (command.matches(findPattern)) {
                    Matcher m = Pattern.compile(findPattern).matcher(command);
                    if (m.find()) {
                        String keyword = m.group(1);
                        this.ui.displayFoundTasks(this.taskList, keyword);
                    }
                } else {
                    // Throw the appropriate error
                    if (command.toLowerCase().contains("mark")) {
                        throw new JimmyException("Oops! Please mark a valid task!");
                    } else if (command.toLowerCase().contains("todo")) {
                        throw new JimmyException("Oops! Please give a valid todo task!");
                    } else if (command.toLowerCase().contains("deadline")) {
                        throw new JimmyException("Oops! Please give a valid deadline task!");
                    } else if (command.toLowerCase().contains("event")) {
                        throw new JimmyException("Oops! Please give a valid event task!");
                    }
                    throw new JimmyException("Oops! I have no clue what that means!");
                }
            } catch (JimmyException e) {
                this.ui.displayError(e);
            } finally {
                // Save tasks on any input automatically
                taskStorage.saveData(this.taskList);
                command = scan.nextLine();
            }
        }
    }

    /**
     * Handles the specific input sent by the user and responds accordingly.
     *
     * @param command The string input that is typed by the user.
     * @return String to be shown in the GUI by chatbot
     */
    public String parseCommand(String command) {
        // Initialise all necessary regular expression patterns
        String markPattern = "mark \\d+";
        String unmarkPattern = "unmark \\d+";
        String listPattern = "list";
        String byePattern = "bye";
        String toDoPattern = "todo\\s+(.+)"; // to-do, whitespace, any sequence of words
        String deadlinePattern = "deadline\\s+(.+)\\s+(/by (.+))";
        String eventPattern = "event\\s+(.+)\\s+(/from (.+))\\s+(/to (.+))";
        String deletePattern = "delete (\\d+)";
        String findPattern = "find (.+)";

        // Only exit if "bye" is inputted
        try {
            // Verify the contents of the command using regular expressions
            if (command.matches(byePattern)) {
                return "Goodbye!";

            } else if (command.matches(listPattern)) {
                // List: Display stored text
                return this.ui.displayTaskList(this.taskList);

            } else if (command.matches(markPattern)) {
                // Mark: mark task as done
                String[] splitCommand = command.split(" ");
                try {
                    int parsedInt = Integer.parseInt(splitCommand[1]); // Try to convert the string into an int
                    Task taskToMark = this.taskList.markDone(parsedInt);
                    return this.ui.displayMarkDone(taskToMark);

                } catch (Exception e) {
                    // Catch invalid inputs
                    throw new JimmyException("Oops! Please mark a valid task!");
                }
            } else if (command.matches(unmarkPattern)) {
                // Unmark: mark task as not done
                String[] splitCommand = command.split(" ");
                try {
                    int parsedInt = Integer.parseInt(splitCommand[1]); // Try to convert the string into an int
                    Task taskToUnmark = this.taskList.markNotDone(parsedInt);
                    return this.ui.displayMarkNotDone(taskToUnmark);
                } catch (Exception e) {
                    // Catch invalid inputs
                    throw new JimmyException("Oops! Please unmark a valid task!");
                }
            } else if (command.matches(toDoPattern)) {
                Matcher m = Pattern.compile(toDoPattern).matcher(command);
                if (m.find()) {
                    String commandDescription = m.group(1);
                    ToDo newToDo = new ToDo(commandDescription, false);
                    this.taskList.addTask(newToDo);
                    return this.ui.displayAddedTask(newToDo, this.taskList);
                } else {
                    throw new JimmyException("Oops! Please add a valid todo task!");
                }
            } else if (command.matches(deadlinePattern)) {
                Matcher m = Pattern.compile(deadlinePattern).matcher(command);
                if (m.find()) {
                    String commandDescription = m.group(1);
                    String deadline = m.group(3);
                    Deadline newDeadline = new Deadline(commandDescription, false, deadline);
                    this.taskList.addTask(newDeadline);
                    return this.ui.displayAddedTask(newDeadline, this.taskList);
                } else {
                    throw new JimmyException("Oops! Please add a valid deadline task!");
                }
            } else if (command.matches(eventPattern)) {
                Matcher m = Pattern.compile(eventPattern).matcher(command);
                if (m.find()) {
                    String commandDescription = m.group(1);
                    String start = m.group(3);
                    String end = m.group(5);
                    Event newEvent = new Event(commandDescription, false, start, end);
                    this.taskList.addTask(newEvent);
                    return this.ui.displayAddedTask(newEvent, this.taskList);
                } else {
                    throw new JimmyException("Oops! Please add a valid event task!");
                }
            } else if (command.matches(deletePattern)) {
                Matcher m = Pattern.compile(deletePattern).matcher(command);
                if (m.find()) {
                    int taskNumberToDelete = Integer.parseInt(m.group(1)) - 1;
                    Task taskToDelete = this.taskList.removeTask(taskNumberToDelete);
                    return this.ui.displayRemovedTask(taskToDelete, this.taskList);
                } else {
                    throw new JimmyException("Oops! Please delete a valid task!");
                }
            } else if (command.matches(findPattern)) {
                Matcher m = Pattern.compile(findPattern).matcher(command);
                if (m.find()) {
                    String keyword = m.group(1);
                    return this.ui.displayFoundTasks(this.taskList, keyword);
                } else {
                    throw new JimmyException("Oops! Please find a valid task!");
                }
            } else {
                // Throw the appropriate error
                if (command.toLowerCase().contains("mark")) {
                    throw new JimmyException("Oops! Please mark a valid task!");
                } else if (command.toLowerCase().contains("todo")) {
                    throw new JimmyException("Oops! Please give a valid todo task!");
                } else if (command.toLowerCase().contains("deadline")) {
                    throw new JimmyException("Oops! Please give a valid deadline task!");
                } else if (command.toLowerCase().contains("event")) {
                    throw new JimmyException("Oops! Please give a valid event task!");
                }
                throw new JimmyException("Oops! I have no clue what that means!");
            }
        } catch (JimmyException e) {
            return this.ui.displayError(e);
        } finally {
            // Save tasks on any input automatically
            taskStorage.saveData(this.taskList);
        }
    }
}
