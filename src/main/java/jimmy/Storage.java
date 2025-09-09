package jimmy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import jimmy.exception.JimmyException;
import jimmy.task.Deadline;
import jimmy.task.Event;
import jimmy.task.Task;
import jimmy.task.TaskList;
import jimmy.task.ToDo;

/**
 * Represents a Storage object.
 */
public class Storage {
    private File storageFile;

    /**
     * Constructs a Storage object.
     *
     * @param filename name for the file of the stored tasks.
     */
    public Storage(String filename) {
        try {
            // Create folder if it does not exist
            File folder = new File("data");
            if (!folder.exists()) {
                folder.mkdir();
                System.out.println("Created data folder: " + folder.getAbsolutePath());
            }
            this.storageFile = new File(folder, filename);
            if (this.storageFile.createNewFile()) {
                // Create storageFile file if not already created
                System.out.println(String.format("Storage file created: %s at %s", this.storageFile.getName(),
                        this.storageFile.getAbsolutePath()));
            }
        } catch (IOException e) {
            System.out.println("Failed to create file: " + e.getMessage());
        }
        assert this.storageFile != null;
    }

    /**
     * Returns an arraylist with all the tasks saved in the hard disk.
     *
     * @return ArrayList of stored tasks.
     */
    public TaskList loadData(TaskList taskList) {
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
    }

    /**
     * Returns the appropriate task based on the data parsed.
     *
     * @param dataEntry a line entry in the taskStorage data that represents a task.
     * @return appropriate Task depending on the line entry.
     */
    public Task readData(String dataEntry) throws JimmyException {
        String[] parsedData = dataEntry.split("\\|");
        switch (parsedData[0].toLowerCase()) {
        case ("todo"):
            // TODO|DESCRIPTION|COMPLETED
            assert parsedData.length == 3;
            ToDo newTodo = new ToDo(parsedData[1], parsedData[2].equalsIgnoreCase("true"));
            return newTodo;
        case ("deadline"):
            // DEADLINE|DESCRIPTION|COMPLETED|DEADLINE
            assert parsedData.length == 4;
            Deadline newDeadline = new Deadline(parsedData[1], parsedData[2].equalsIgnoreCase("true"),
                    parsedData[3]);
            return newDeadline;
        case ("event"):
            // EVENT|DESCRIPTION|COMPLETED|START|END|
            assert parsedData.length == 5;
            Event newEvent = new Event(parsedData[1], parsedData[2].equalsIgnoreCase("true"),
                    parsedData[3], parsedData[4]);
            return newEvent;
        default:
            throw new JimmyException("Error in reading data");
        }
    }

    /**
     * Saves the stored tasks in the taskList to the hard disk.
     *
     * @param taskList TaskList of stored tasks.
     */
    public void saveData(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(this.storageFile);
            for (int i = 0; i < taskList.size(); i++) {
                fw.write(taskList.getTask(i + 1).toStorageString()); // Add 1 to account for zero-indexed position
                fw.write(System.lineSeparator()); // Start next task on a new line
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
}
