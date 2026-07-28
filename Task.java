package suntivity_model;

/**
 * Represents a task assigned to a child account by a parent account.
 *
 * <p>A Task contains information about the assigned child, the parent who
 * created it, task details, due date information, completion status, and
 * the number of points awarded when completed.</p>
 */
public class Task {

    /** The child account assigned to this task. */
    private ChildAccount child;

    /** The parent account that created this task. */
    private ParentAccount parent;

    /** The name of the task. */
    private String name;

    /** A description providing additional details about the task. */
    private String description;

    /** The month the task is due. */
    private int dueMonth;

    /** The day of the month the task is due. */
    private int dueDay;

    /** The time the task is due. */
    private int dueTime;

    /** The current completion status of the task. */
    private TaskStatus status;

    /** The number of points awarded when the task is completed. */
    private int points;

    /**
     * Creates a new task with the specified information.
     *
     * <p>New tasks are automatically assigned an initial status of
     * {@link TaskStatus#INCOMPLETE}.</p>
     *
     * @param child the child account assigned to the task
     * @param parent the parent account creating the task
     * @param name the task name
     * @param description the task description
     * @param dueMonth the month the task is due
     * @param dueDay the day the task is due
     * @param dueTime the time the task is due
     * @param points the number of points awarded upon completion
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
        status = TaskStatus.INCOMPLETE;
    }

    /**
     * Sets the child account assigned to this task.
     *
     * @param child the child account
     */
    public void setChild(ChildAccount child) {
        this.child = child;
    }

    /**
     * Retrieves the child account assigned to this task.
     *
     * @return the assigned child account
     */
    public ChildAccount getChild() {
        return child;
    }

    /**
     * Sets the parent account that owns this task.
     *
     * @param parent the parent account
     */
    public void setParent(ParentAccount parent) {
        this.parent = parent;
    }

    /**
     * Retrieves the parent account that created this task.
     *
     * @return the parent account
     */
    public ParentAccount getParent() {
        return parent;
    }

    /**
     * Updates the task name.
     *
     * @param name the new task name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the task name.
     *
     * @return the task name
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the task description.
     *
     * @param description the new task description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the task description.
     *
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Updates the due month of the task.
     *
     * @param dueMonth the new due month
     */
    public void setDueMonth(int dueMonth) {
        this.dueMonth = dueMonth;
    }

    /**
     * Retrieves the task due month.
     *
     * @return the due month
     */
    public int getDueMonth() {
        return dueMonth;
    }

    /**
     * Updates the due day of the task.
     *
     * @param dueDay the new due day
     */
    public void setDueDay(int dueDay) {
        this.dueDay = dueDay;
    }

    /**
     * Retrieves the task due day.
     *
     * @return the due day
     */
    public int getDueDay() {
        return dueDay;
    }

    /**
     * Updates the due time of the task.
     *
     * @param dueTime the new due time
     */
    public void setDueTime(int dueTime) {
        this.dueTime = dueTime;
    }

    /**
     * Retrieves the task due time.
     *
     * @return the due time
     */
    public int getDueTime() {
        return dueTime;
    }

    /**
     * Updates the current task status.
     *
     * @param status the new task status
     */
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    /**
     * Retrieves the current task status.
     *
     * @return the task status
     */
    public TaskStatus getStatus() {
        return status;
    }

    /**
     * Updates the number of points awarded for completing the task.
     *
     * @param points the new point value
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Retrieves the number of points awarded for completing the task.
     *
     * @return the task point value
     */
    public int getPoints() {
        return points;
    }
}
