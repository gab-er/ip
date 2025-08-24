package duke.task;

import duke.exception.JimmyException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructor for an Event object
     *
     * @param description The description of the task
     * @param start       The start time of the task
     * @param end         The end time of the task
     */
    public Event(String description, boolean completed, String start, String end) throws JimmyException {
        super(description, completed);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        // Parse start date and time
        try {
            LocalDateTime d1 = LocalDateTime.parse(start, dateTimeFormatter);
            this.start = d1;
        } catch (DateTimeParseException e) {
            try {
                // If missing time, set it to 00:00 as default
                LocalDateTime d1 = LocalDate.parse(start).atStartOfDay();
                this.start = d1;
            } catch (DateTimeParseException e2) {
                throw new JimmyException("Please add a valid start date and time!");
            }
        }

        // Parse end date and time
        try {
            LocalDateTime d1 = LocalDateTime.parse(end, dateTimeFormatter);
            this.end = d1;
        } catch (DateTimeParseException e) {
            try {
                // If missing time, set it to 00:00 as default
                LocalDateTime d1 = LocalDate.parse(end).atStartOfDay();
                if (this.start.isAfter(d1)) {
                    throw new JimmyException("Error: Start date is after end date!");
                }
                this.end = d1;
            } catch (DateTimeParseException e2) {
                throw new JimmyException("Please add a valid end date and time!");
            }
        }
    }

    public String toStorageString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return String.format("EVENT|%s|%s|%s|%s", this.getDescription(), this.getCompleted(),
                this.start.format(dateTimeFormatter), this.end.format(dateTimeFormatter));
    }

    @Override
    public String toString() {
        String type = "E";
        return String.format("[%s]%s (from: %s to: %s)", type, super.toString(),
                this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")),
                this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")));
    }
}

