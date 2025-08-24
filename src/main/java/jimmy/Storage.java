package jimmy;

import jimmy.exception.JimmyException;
import jimmy.task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Storage {
    private final File storageFile;

    public Storage(String filename) {
        this.storageFile = new File(filename);
        try {
            if (this.storageFile.createNewFile()) {
                // Create storageFile file if not already created
                System.out.println(String.format("Storage file created: %s at %s", this.storageFile.getName(),
                        this.storageFile.getAbsolutePath()));
            }
        } catch (IOException e) {
            System.out.println("Failed to create file: " + e.getMessage());
        }
    }

    /**
     * Returns an arraylist with all the tasks saved in the hard disk.
     *
     * @return Arraylist of tasks.
     */
    public TaskList loadData(TaskList taskList) {
        ArrayList<Task> storedTasks = new ArrayList<>();

        try {
            // Scanner to read the file
            Scanner s = new Scanner(storageFile);
            // Update the arraylist to store the tasks
            while (s.hasNext()) {
                Task newTask = readData(s.nextLine());
                taskList.addTask(newTask);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (JimmyException e) {
            System.out.println(e);
        }
        return taskList;
    };

    /**
     * Returns the appropriate Task based on the data parsed.
     *
     * @param dataEntry a line entry in the taskStorage data that represents a task.
     * @return appropriate Task depending on the line entry.
     */
    public Task readData(String dataEntry) throws JimmyException {
        // File format:
        // TODO|DESCRIPTION|COMPLETED
        // DEADLINE|DESCRIPTION|COMPLETED|DEADLINE
        // EVENT|DESCRIPTION|COMPLETED|START|END|
        String[] parsedData = dataEntry.split("\\|");
        switch (parsedData[0].toLowerCase()) {
        case ("todo"):
            ToDo newTodo = new ToDo(parsedData[1], parsedData[2].equalsIgnoreCase("true"));
            return newTodo;
        case ("deadline"):
            Deadline newDeadline = new Deadline(parsedData[1], parsedData[2].equalsIgnoreCase("true"),
                    parsedData[3]);
            return newDeadline;
        case ("event"):
            Event newEvent = new Event(parsedData[1], parsedData[2].equalsIgnoreCase("true"),
                    parsedData[3], parsedData[4]);
            return newEvent;
        default:
            throw new JimmyException("Error in reading data");
        }
    }

    /**
     * Saves the stored tasks in storedTasks to the hard disk.
     *
     * @param taskList TaskList of stored tasks.
     */
    public void saveData(TaskList taskList) {
        ArrayList<Task> storedTasks = taskList.getStoredTasks();
        try {
            FileWriter fw = new FileWriter(this.storageFile);
            for (int i = 0; i < storedTasks.size(); i++) {
                fw.write(storedTasks.get(i).toStorageString());
                fw.write(System.lineSeparator()); // Start next task on a new line
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
}
