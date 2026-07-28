package edu.utsa.cs3443.suntivity.model;

/**
 * Represents a task assigned to a child account.
 * Tasks are created by parent accounts and completed by child accounts
 * to earn points and progress their plant.
 */
public class Task {

    private ChildAccount child;
    private ParentAccount parent;
    private String name;
    private String description;
    private int dueMonth;
    private int dueDay;
    private int dueTime;
    private TaskStatus status;
    private int points;

    /**
     * Creates a task assigned to a child by a parent.
     *
     * @param child child assigned the task
     * @param parent parent who created the task
     * @param name task name
     * @param description task details
     * @param dueMonth task due month
     * @param dueDay task due day
     * @param dueTime task due time
     * @param points points rewarded for completing the task
     */
    public Task(ChildAccount child,
                ParentAccount parent,
                String name,
                String description,
                int dueMonth,
                int dueDay,
                int dueTime,
                int points) {

        this.child = child;
        this.parent = parent;
        this.name = name;
        this.description = description;
        this.dueMonth = dueMonth;
        this.dueDay = dueDay;
        this.dueTime = dueTime;
        this.points = points;
        this.status = TaskStatus.INCOMPLETE;
    }

    public void setChild(ChildAccount child) {
        this.child = child;
    }

    public ChildAccount getChild() {
        return child;
    }

    public void setParent(ParentAccount parent) {
        this.parent = parent;
    }

    public ParentAccount getParent() {
        return parent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDueMonth(int dueMonth) {
        this.dueMonth = dueMonth;
    }

    public int getDueMonth() {
        return dueMonth;
    }

    public void setDueDay(int dueDay) {
        this.dueDay = dueDay;
    }

    public int getDueDay() {
        return dueDay;
    }

    public void setDueTime(int dueTime) {
        this.dueTime = dueTime;
    }

    public int getDueTime() {
        return dueTime;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }
}