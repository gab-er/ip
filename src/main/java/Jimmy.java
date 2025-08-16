import java.util.Scanner;
import java.util.ArrayList;

public class Jimmy {
    public static void main(String[] args) {
        // Initialise variables
        Scanner scan = new Scanner(System.in);
        String chatBotName = "Jimmy";
        String horizontalDivider = "_________________________________________________";
        ArrayList<String> storedText = new ArrayList<>(); // Array to store commands given

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
                for (int i = 0; i < storedText.size(); i++) {
                    int id = i + 1; // Text ID starting from 1
                    System.out.println(id + ". " + storedText.get(i));
                }
            } else {
                // Echo
                storedText.add(command); // Store entered text
                System.out.println(horizontalDivider);
                System.out.println("added:" + command);
                System.out.println(horizontalDivider);
            }
            command = scan.nextLine();
        }

        // Exit
        System.out.println(horizontalDivider);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalDivider);
    }
}
