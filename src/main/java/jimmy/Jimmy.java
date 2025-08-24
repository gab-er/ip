package jimmy;

import jimmy.task.Task;
import jimmy.task.TaskList;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents the Jimmy chatbot object.
 */
public class Jimmy {
    private final Scanner scan;
    private ArrayList<Task> storedTasks;
    private Storage taskStorage;
    private Ui ui;
    private Parser parser;
    private final String HORIZONTAL_DIVIDER = "_________________________________________________";
    private TaskList taskList;

    /**
     * Constructor for a Jimmy object.
     */
    public Jimmy() {
        this.scan = new Scanner(System.in);
        this.taskStorage = new Storage("taskStorage.txt");
        this.storedTasks = this.taskStorage.loadData();
        this.ui = new Ui(HORIZONTAL_DIVIDER);
        this.taskList = new TaskList(this.storedTasks);
        this.parser = new Parser(this.ui, this.taskStorage, this.taskList);
    }

    public void run() {
        // Greetings
        this.ui.handleGreeting();

        // Retrieve initial user input
        String command = scan.nextLine();

        // Handle user inputs
        this.parser.parse(command, this.scan);

        // Exit
        this.ui.handleExit();
    }

    public static void main(String[] args) {
        new Jimmy().run();
    }
}

