package jimmy;

import java.util.ArrayList;
import java.util.Scanner;

import jimmy.task.Task;
import jimmy.task.TaskList;

/**
 * Represents a Jimmy chatbot object.
 */
public class Jimmy {
    private final Scanner scan;
    private ArrayList<Task> storedTasks;
    private Storage taskStorage;
    private Ui ui;
    private Parser parser;
    private TaskList taskList;

    /**
     * Constructor for a Jimmy object.
     */
    public Jimmy() {
        this.scan = new Scanner(System.in);
        this.taskStorage = new Storage("taskStorage.txt");
        this.taskList = new TaskList();
        this.taskList = this.taskStorage.loadData(this.taskList);
        this.ui = new Ui();
        this.parser = new Parser(this.ui, this.taskStorage, this.taskList);
    }

    /**
     * Runs the chatbot program.
     */
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

