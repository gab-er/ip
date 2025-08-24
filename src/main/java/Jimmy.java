import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents the Jimmy chatbot object.
 */
public class Jimmy {
    private final Scanner scan;
    private final String chatBotName;
    private final String horizontalDivider;
    private ArrayList<Task> storedTasks;
    private Storage taskStorage;

    /**
     * Constructor for a Jimmy object.
     */
    public Jimmy() {
        this.scan = new Scanner(System.in);
        this.chatBotName = "Jimmy";
        this.horizontalDivider = "_________________________________________________";
        this.storedTasks = new ArrayList<>(); // Array to store commands given
        this.taskStorage = new Storage("taskStorage.txt");
    }

    /**
     *
     */
    public void run() {
        // Load data into storedTasks
        this.storedTasks = this.taskStorage.loadData();

        // Greetings
        handleGreeting(horizontalDivider, chatBotName);

        // Retrieve user input
        String command = scan.nextLine();

        // Handle commands based on input
        handleCommand(command, scan, horizontalDivider, storedTasks);

        // Exit
        handleExit(horizontalDivider);
    }

    /**
     * Handles the greeting sent by the chatbot.
     *
     * @param horizontalDivider The horizontal line divider used to separate chunks of text
     * @param chatBotName       The name of the chatbot
     */
    public void handleGreeting(String horizontalDivider, String chatBotName) {
        System.out.println(horizontalDivider);
        System.out.println("Hello! I'm " + chatBotName);
        System.out.println("What can I do for you?");
        System.out.println(horizontalDivider + "\n"); // Add newline so user input is on next line
    }

    /**
     * Handles the exit message sent by the chatbot.
     *
     * @param horizontalDivider The horizontal line divider used to separate chunks of text.
     */
    public void handleExit(String horizontalDivider) {
        System.out.println(horizontalDivider);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalDivider);
    }

    /**
     * Handles the input sent by the user and responds accordingly.
     *
     * @param command           The string input that is typed by the user.
     * @param scan              The scanner object used by the chatbot.
     * @param horizontalDivider The horizontal line divider used to separate chunks of text.
     * @param storedTasks       The arraylist used to store the tasks created by the user.
     */
    public void handleCommand(String command, Scanner scan, String horizontalDivider, ArrayList<Task> storedTasks) {
        // Initialise all necessary regular expression patterns
        String markPattern = "mark \\d+";
        String unmarkPattern = "unmark \\d+";
        String listPattern = "list";
        String byePattern = "bye";
        String toDoPattern = "todo\\s+(.+)"; // to-do, whitespace, any sequence of words
        String deadlinePattern = "deadline\\s+(.+)\\s+(/by (.+))";
        String eventPattern = "event\\s+(.+)\\s+(/from (.+))\\s+(/to (.+))";
        String deletePattern = "delete (\\d+)";

        // Only exit if "bye" is inputted
        while (!command.matches(byePattern)) {
            try {
                // Verify the contents of the command using regular expressions
                if (command.matches(listPattern)) {
                    // List: Display stored text
                    System.out.println(horizontalDivider);
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < storedTasks.size(); i++) {
                        Task currentTask = storedTasks.get(i);
                        String formattedString = String.format("%d.%s", i + 1, currentTask);
                        System.out.println(formattedString);
                    }
                    System.out.println(horizontalDivider);
                } else if (command.matches(markPattern)) {
                    // Mark: mark task as done
                    String[] splitCommand = command.split(" ");
                    try {
                        int parsedInt = Integer.parseInt(splitCommand[1]); // Try to convert the string into an int
                        Task taskToMark = storedTasks.get(parsedInt - 1);
                        taskToMark.markDone(); // Mark task as done
                        System.out.println(horizontalDivider);
                        System.out.println(String.format("Nice! I've marked this task as done:\n %s", taskToMark));
                        System.out.println(horizontalDivider);
                    } catch (Exception e) {
                        // Catch invalid inputs
                        throw new JimmyException("Oops! Please mark a valid task!");
                    }
                } else if (command.matches(unmarkPattern)) {
                    // Unmark: mark task as not done
                    String[] splitCommand = command.split(" ");
                    try {
                        int parsedInt = Integer.parseInt(splitCommand[1]); // Try to convert the string into an int
                        Task taskToUnmark = storedTasks.get(parsedInt - 1);
                        taskToUnmark.markNotDone(); // Mark task as not done
                        System.out.println(horizontalDivider);
                        System.out.println(String.format("Ok! I've marked this task as not done yet:\n %s", taskToUnmark));
                        System.out.println(horizontalDivider);
                    } catch (Exception e) {
                        // Catch invalid inputs
                        System.out.println(e);
                    }
                } else if (command.matches(toDoPattern)) {
                    Matcher m = Pattern.compile(toDoPattern).matcher(command);
                    if (m.find()) {
                        String commandDescription = m.group(1);
                        ToDo newToDo = new ToDo(commandDescription, false);
                        storedTasks.add(newToDo); // Store entered text
                        System.out.println(horizontalDivider);
                        System.out.println(String.format("Got it. I've added this task:\n %s", newToDo));
                        System.out.println(String.format("Now you have %d tasks in the list.", storedTasks.size()));
                        System.out.println(horizontalDivider);
                    }
                } else if (command.matches(deadlinePattern)) {
                    Matcher m = Pattern.compile(deadlinePattern).matcher(command);
                    if (m.find()) {
                        String commandDescription = m.group(1);
                        String deadline = m.group(3);
                        Deadline newDeadline = new Deadline(commandDescription, false, deadline);
                        storedTasks.add(newDeadline); // Store entered text
                        System.out.println(horizontalDivider);
                        System.out.println(String.format("Got it. I've added this task:\n %s", newDeadline));
                        System.out.println(String.format("Now you have %d tasks in the list.", storedTasks.size()));
                        System.out.println(horizontalDivider);
                    }

                } else if (command.matches(eventPattern)) {
                    Matcher m = Pattern.compile(eventPattern).matcher(command);
                    if (m.find()) {
                        String commandDescription = m.group(1);
                        String start = m.group(3);
                        String end = m.group(5);
                        Event newEvent = new Event(commandDescription, false, start, end);
                        storedTasks.add(newEvent); // Store entered text
                        System.out.println(horizontalDivider);
                        System.out.println(String.format("Got it. I've added this task:\n %s", newEvent));
                        System.out.println(String.format("Now you have %d tasks in the list.", storedTasks.size()));
                        System.out.println(horizontalDivider);
                    }
                } else if (command.matches(deletePattern)) {
                    Matcher m = Pattern.compile(deletePattern).matcher(command);
                    if (m.find()) {
                        int taskNumberToDelete = Integer.parseInt(m.group(1)) - 1;
                        Task taskToDelete = storedTasks.get(taskNumberToDelete);
                        storedTasks.remove(taskNumberToDelete);
                        System.out.println(horizontalDivider);
                        System.out.println(String.format("Noted. I've removed this task:\n %s", taskToDelete));
                        System.out.println(String.format("Now you have %d tasks in the list.", storedTasks.size()));
                        System.out.println(horizontalDivider);
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
                System.out.println(horizontalDivider);
                System.out.println(e.getMessage());
                System.out.println(horizontalDivider);
            } finally {
                // Save tasks on any input automatically
                taskStorage.saveData(storedTasks);
                command = scan.nextLine();
            }
        }
    }

    public static void main(String[] args) {
        Jimmy chatBot = new Jimmy();
        chatBot.run();
    }
}

