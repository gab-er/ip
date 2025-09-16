# Jimmy User Guide

![](docs/Ui.png)

Jimmy is a fast and efficient task management application that helps you organize your todos, deadlines, and events through a clean command-line interface. Perfect for users who prefer keyboard-driven productivity.

## 🚀 Quick Start
``` 
    Download the latest jimmy.jar file

    Copy the file to your preferred folder

    Open a terminal in that folder and run: java -jar jimmy.jar

    Type commands to manage your tasks

    Type exit to save your tasks and close the application
```
## 📋 Adding todos

Add a simple task without any date/time! \
Example:
```
todo work
```
Expected Output:

```
Got it. I've added this task:
[T][] work
Now you have 1 task in the list.
```

## ⏰ Adding deadlines
Add a task with a specific due date!\
Example:

```
deadline work /by 2025-08-08 1400
```
Expected ouput:
```
Got it. I've added this task:
[D][] work (by: Aug 8 2025 14:00)
Now you have 1 task in the list.
```

## 🎯 Adding events
Add a task with a specific due date and end date!\
Example:

```
event work /from 2025-08-08 1400 /to 2025-08-08 1500
```
Expected ouput:
```
Got it. I've added this task:
[E][] work (from: Aug 8 2025 14:00 to Aug 8 2025 15:00)
Now you have 1 task in the list.
```

## ✅ Mark your tasks as done!
Use this command when you're finished with a task!
```
mark 1 // Mark the first task as done
```

## 🔄 Unmark your tasks as done!
Use this command when you want to unmark a task as completed!
```
unmark 1 // Unmark the first task as done
```

## 🗑️ Delete your tasks!
Use this command to delete a task
```
delete 1 // Deletes the first task 
```

## 📜 Display your task list!

Use this command to display all your current tasks!
```
list
```


## 📌 Tag your tasks!

Tag your tasks with custom tags!\
Example:
```
tag 1 homework // Tags the first task as homework
```

## 🔍 Find your tasks!

Find your tasks with keywords!\
Example:
```
find work // Shows all tasks with the keyword "work"
```

## 🚪 Leaving?
Use this command to exit the chatbot
```
bye
```