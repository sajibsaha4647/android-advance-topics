package com.example.myapplication.model;

public class Task {
    private int id;
    private String task;
    private int priority;
    private String dueDate;
    private int isDone;

    public Task(int id, String task, int priority, String dueDate, int isDone) {
        this.id = id;
        this.task = task;
        this.priority = priority;
        this.dueDate = dueDate;
        this.isDone = isDone;
    }



    // Getters and setters
    public int getId() { return id; }
    public String getTask() { return task; }
    public int getPriority() { return priority; }
    public String getDueDate() { return dueDate; }
    public int getIsDone() { return isDone; }

    public void setTask(String task) { this.task = task; }
    public void setPriority(int priority) { this.priority = priority; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }
    public void setIsDone(int isDone) { this.isDone = isDone; }
}
