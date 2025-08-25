package jimmy.task;

import jimmy.exception.JimmyException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructor for a Deadline object.
     *
     * @param description The description of the task.
     * @param deadline    The deadline of the task in the format yyyy-mm-dd.
     */
    public Deadline(String description, boolean completed, String deadline) throws JimmyException {
        super(description, completed);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            LocalDateTime d1 = LocalDateTime.parse(deadline, dateTimeFormatter);
            this.deadline = d1;
        } catch (DateTimeParseException e) {
            try {
                // If missing time, set it to 00:00 as default
                LocalDateTime d1 = LocalDate.parse(deadline).atStartOfDay();
                this.deadline = d1;
            } catch (DateTimeParseException e2) {
                throw new JimmyException("Please add a valid date and time!");
            }
        }
    }

    /**
     * Returns the formatted string to be used for storing in the hard disk.
     *
     * @return Formatted string.
     */
    public String toStorageString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return String.format("DEADLINE|%s|%s|%s", this.getDescription(), this.getCompleted(),
                this.deadline.format(dateTimeFormatter));
    }

    /**
     * Returns the string representation of this task.
     *
     * @return String representation of this task.
     */
    @Override
    public String toString() {
        String type = "D";
        return String.format("[%s]%s (by: %s)", type, super.toString(),
                this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")));
    }
}
