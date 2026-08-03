package edu.utsa.cs3443.suntivity.model;

import java.time.LocalDate;


/**
 * Represents a task assigned to a child account.
 * Tasks are created by parents and completed by children
 * to earn points and progress their plant.
 */
public class Task {


    private ChildAccount child;
    private ParentAccount parent;


    private String taskName;
    private String description;


    private int dueMonth;
    private int dueDay;
    private int dueTime;

    private LocalDate dueDate;


    private TaskStatus status;


    private int points;


    private boolean repeat;



    /**
     * Creates a task assigned to a child by a parent.
     */
    public Task(
            ChildAccount child,
            ParentAccount parent,
            String taskName,
            String description,
            LocalDate dueDate,
            int dueTime,
            int points,
            boolean repeat
    ) {


        this.child = child;
        this.parent = parent;

        this.taskName = taskName;
        this.description = description;

        this.dueDate = dueDate;

        this.dueMonth = dueDate.getMonthValue();
        this.dueDay = dueDate.getDayOfMonth();

        this.dueTime = dueTime;

        this.points = points;

        this.repeat = repeat;

        this.status = TaskStatus.INCOMPLETE;

    }





    public ChildAccount getChild() {

        return child;

    }


    public void setChild(ChildAccount child) {

        this.child = child;

    }





    public ParentAccount getParent() {

        return parent;

    }


    public void setParent(ParentAccount parent) {

        this.parent = parent;

    }





    public String getTaskName() {

        return taskName;

    }


    public void setTaskName(String taskName) {

        this.taskName = taskName;

    }





    public String getDescription() {

        return description;

    }


    public void setDescription(String description) {

        this.description = description;

    }





    public int getDueMonth() {

        return dueMonth;

    }


    public void setDueMonth(int dueMonth) {

        this.dueMonth = dueMonth;

    }





    public int getDueDay() {

        return dueDay;

    }


    public void setDueDay(int dueDay) {

        this.dueDay = dueDay;

    }





    public LocalDate getDueDate(){

        return dueDate;

    }


    public void setDueDate(LocalDate dueDate){

        this.dueDate = dueDate;


        this.dueMonth =
                dueDate.getMonthValue();


        this.dueDay =
                dueDate.getDayOfMonth();

    }





    public int getDueTime() {

        return dueTime;

    }


    public void setDueTime(int dueTime) {

        this.dueTime = dueTime;

    }





    public TaskStatus getStatus() {

        return status;

    }


    public void setStatus(TaskStatus status) {

        this.status = status;

    }





    public int getPoints() {

        return points;

    }


    public void setPoints(int points) {

        this.points = points;

    }





    public boolean isRepeat() {

        return repeat;

    }


    public void setRepeat(boolean repeat) {

        this.repeat = repeat;

    }





    /**
     * Child submits completed task.
     */
    public void completeTask(){

        status = TaskStatus.PENDING;

    }





    /**
     * Parent approves task.
     */
    public void approveTask(){

        status = TaskStatus.APPROVED;

    }





    /**
     * Parent denies task.
     */
    public void denyTask(){

        status = TaskStatus.DENIED;

    }





    /**
     * Allows tasks to display correctly in ListViews.
     */
    @Override
    public String toString(){

        return taskName;

    }

    public String getName(){

        return taskName;

    }
}