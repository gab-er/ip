import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Jimmy {

    public static void main(String[] args) {
        // Initialise variables
        Scanner scan = new Scanner(System.in);
        String chatBotName = "Jimmy";
        String horizontalDivider = "_________________________________________________";
        ArrayList<Task> storedTasks = new ArrayList<>(); // Array to store commands given

        // Greetings
        handleGreeting(horizontalDivider, chatBotName);

        // Retrieve user input
        String command = scan.nextLine();

        // Handle commands based on input
        handleCommand(command, scan, horizontalDivider, storedTasks);

        // Exit
        handleExit(horizontalDivider);
    }

    public static void handleGreeting(String horizontalDivider, String chatBotName) {
        System.out.println(horizontalDivider);
        System.out.println("Hello! I'm " + chatBotName);
        System.out.println("What can I do for you?");
        System.out.println(horizontalDivider + "\n"); // Add newline so user input is on next line
    }

    public static void handleExit(String horizontalDivider) {
        System.out.println(horizontalDivider);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalDivider);
    }

    public static void handleCommand(String command, Scanner scan, String horizontalDivider, ArrayList<Task> storedTasks) {
        String markPattern = "mark \\d+";
        String unmarkPattern = "unmark \\d+";
        String listPattern = "list";
        String byePattern = "bye";
        String toDoPattern = "todo\\s+(.+)"; // to-do, whitespace, any sequence of words
        String deadlinePattern = "deadline\\s+(.+)\\s+(/by (.+))";
        String eventPattern = "event\\s+(.+)\\s+(/from (.+))\\s+(/to (.+))";

        // Only exit if "bye" is inputted
        while (!command.matches(byePattern)) {
            // Verify the contents of the command using regular expressions

            if (command.matches(listPattern)) {
                // List: Display stored text
                for (Task currentTask : storedTasks) {
                    String formattedString = String.format("%d.%s", currentTask.getId(), currentTask);
                    System.out.println(formattedString);
                }
            } else if (command.matches(markPattern)) {
                // Mark: mark task as done
                String[] splitCommand = command.split(" ");
                try {
                    int parsedInt = Integer.parseInt(splitCommand[1]); // Try to convert the string into an int
                    storedTasks.get(parsedInt - 1).markDone(); // Mark task as done
                } catch (Exception e) {
                    // Catch invalid inputs
                    System.out.println("Please input a valid number");
                }
            } else if (command.matches(unmarkPattern)) {
                //Unmark: mark task as not done
                String[] splitCommand = command.split(" ");
                try {
                    int parsedInt = Integer.parseInt(splitCommand[1]); // Try to convert the string into an int
                    storedTasks.get(parsedInt - 1).markNotDone(); // Mark task as done
                } catch (Exception e) {
                    // Catch invalid inputs
                    System.out.println("Please input a valid number");
                }
            } else if (command.matches(toDoPattern)) {
                Matcher m = Pattern.compile(toDoPattern).matcher(command);
                if (m.find()) {
                    String commandDescription = m.group(1);
                    ToDo newToDo = new ToDo(commandDescription);
                    storedTasks.add(newToDo); // Store entered text
                    System.out.println(horizontalDivider);
                    System.out.println(String.format("Got it. I've added this task: \n %s", newToDo));
                    System.out.println(String.format("Now you have %d tasks in the list.", storedTasks.size()));
                    System.out.println(horizontalDivider);
                }
            } else if (command.matches(deadlinePattern)) {
                Matcher m = Pattern.compile(deadlinePattern).matcher(command);
                if (m.find()) {
                    String commandDescription = m.group(1);
                    String deadline = m.group(3);
                    Deadline newDeadline = new Deadline(commandDescription, deadline);
                    storedTasks.add(newDeadline); // Store entered text
                    System.out.println(horizontalDivider);
                    System.out.println(String.format("Got it. I've added this task: \n %s", newDeadline));
                    System.out.println(String.format("Now you have %d tasks in the list.", storedTasks.size()));
                    System.out.println(horizontalDivider);
                }

            } else if (command.matches(eventPattern)) {
                System.out.println("matches event");
                Matcher m = Pattern.compile(eventPattern).matcher(command);
                if (m.find()) {
                    String commandDescription = m.group(1);
                    String start = m.group(3);
                    String end = m.group(5);
                    Event newEvent = new Event(commandDescription, start, end);
                    storedTasks.add(newEvent); // Store entered text
                    System.out.println(horizontalDivider);
                    System.out.println(String.format("Got it. I've added this task: \n %s", newEvent));
                    System.out.println(String.format("Now you have %d tasks in the list.", storedTasks.size()));
                    System.out.println(horizontalDivider);
                }
            } else {
                // Echo: show that the task is added
                Task newTask = new Task(command);
                storedTasks.add(newTask); // Store entered text
                System.out.println(horizontalDivider);
                System.out.println("added: " + command);
                System.out.println(horizontalDivider);
            }
            command = scan.nextLine();
        }
    }
}

