package com.example.myapplication.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "tasks_room")
public class TaskModelRoom {

 @PrimaryKey(autoGenerate = true)
    private int id;

    private String task;
    private int priority;
    private String dueDate;
    private boolean isDone;

    public TaskModelRoom(String task, int priority, String dueDate, boolean isDone) {
        this.task = task;
        this.priority = priority;
        this.dueDate = dueDate;
        this.isDone = isDone;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTask() { return task; }
    public void setTask(String task) { this.task = task; }

    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }

    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }

    public boolean isDone() { return isDone; }
    public void setDone(boolean done) { isDone = done; }

}
