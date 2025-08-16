import java.util.Scanner;

public class Jimmy {
    public static void main(String[] args) {
        // Initialise variables
        Scanner scan = new Scanner(System.in);
        String chatBotName = "Jimmy";
        String horizontalDivider = "_________________________________________________";

        // Greetings
        System.out.println(horizontalDivider);
        System.out.println("Hello! I'm " + chatBotName);
        System.out.println("What can I do for you?");
        System.out.println(horizontalDivider + "\n"); // Add newline so user input is on next line

        // Retrieve user input
        String command = scan.next();

        // Only exit if "bye" is inputted
        while (!command.equals("bye")) {
            // Echo
            System.out.println(horizontalDivider);
            System.out.println(command);
            System.out.println(horizontalDivider);
            command = scan.next();
        }

        // Exit
        System.out.println(horizontalDivider);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalDivider);
    }
}
