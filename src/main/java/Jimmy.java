import java.util.Scanner;
import java.util.ArrayList;

public class Jimmy {
    public static void main(String[] args) {
        // Initialise variables
        Scanner scan = new Scanner(System.in);
        String chatBotName = "Jimmy";
        String horizontalDivider = "_________________________________________________";
        ArrayList<Task> storedTasks = new ArrayList<>(); // Array to store commands given

        // Greetings
        System.out.println(horizontalDivider);
        System.out.println("Hello! I'm " + chatBotName);
        System.out.println("What can I do for you?");
        System.out.println(horizontalDivider + "\n"); // Add newline so user input is on next line

        // Retrieve user input
        String command = scan.nextLine();

        // Only exit if "bye" is inputted
        while (!command.equals("bye")) {
            // Display stored text
            if (command.equals("list")) {
                for (int i = 0; i < storedTasks.size(); i++) {
                    Task currentTask = storedTasks.get(i);
                    String formattedString = String.format("%d.[%s] %s", currentTask.getId(), currentTask.getStatusIcon(), currentTask.getDescription());
                    System.out.println(formattedString);
                }
            } else {
                // Check for "mark" command
                String[] splitCommand = command.split(" ");
                if (splitCommand.length == 2 && splitCommand[0].equals("mark") || splitCommand[0].equals("unmark")) {
                    switch (splitCommand[0]) {
                        case "mark":
                            try {
                                int parsedInt = Integer.parseInt(splitCommand[1]); // Try to convert the string into an int
                                storedTasks.get(parsedInt - 1).markDone(); // Mark task as done
                            } catch (Exception e) {
                                // Catch invalid inputs
                                System.out.println("Please input a valid number");
                            }
                            break;
                        case "unmark":
                            try {
                                int parsedInt = Integer.parseInt(splitCommand[1]); // Try to convert the string into an int
                                storedTasks.get(parsedInt - 1).markNotDone(); // Mark task as done
                            } catch (Exception e) {
                                // Catch invalid inputs
                                System.out.println("Please input a valid number");
                            }
                            break;
                    }
                } else {
                    // Echo
                    Task newTask = new Task(command);
                    storedTasks.add(newTask); // Store entered text
                    System.out.println(horizontalDivider);
                    System.out.println("added: " + command);
                    System.out.println(horizontalDivider);
                }
            }
            command = scan.nextLine();
        }

        // Exit
        System.out.println(horizontalDivider);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalDivider);
    }
}
