package suntivity_model;

import java.util.ArrayList;

/**
 * Represents a parent user's account within the Suntivity application.
 *
 * <p>A ParentAccount extends the base Account class and provides functionality
 * for managing linked child accounts, creating and reviewing tasks, and
 * approving or rejecting completed tasks.</p>
 */
public class ParentAccount extends Account {

    /** The list of child accounts linked to this parent account. */
    private ArrayList<ChildAccount> children;

    /** The list of tasks created by this parent account. */
    private ArrayList<Task> tasks;

    /** The unique code used by children to link to this parent account. */
    private final int linkingCode;

    /** The application model used to access account information. */
    private Model model;

    /**
     * Creates a new ParentAccount with the specified account information.
     *
     * <p>A unique linking code is generated for the parent account so that
     * child accounts can connect to it.</p>
     *
     * @param userName the username for the account
     * @param password the password for the account
     * @param timeZone the user's time zone
     * @param model the application model containing account data
     */
    public ParentAccount(String userName, String password, int timeZone, Model model) {
        super(userName, password, timeZone);

        children = new ArrayList<>();
        tasks = new ArrayList<>();
        this.model = model;
        linkingCode = generateLinkingCode(model);
    }

    /**
     * Generates a unique five-digit linking code for this parent account.
     *
     * @param model the application model used to verify code uniqueness
     * @return a unique linking code
     */
    private int generateLinkingCode(Model model) {

        int code;

        do {
            code = (int)(Math.random() * 90000) + 10000;
        }
        while(model.getParentByLinkingCode(code) != null);

        return code;
    }

    /**
     * Retrieves the linking code associated with this parent account.
     *
     * @return the parent account linking code
     */
    public int getLinkingCode() {
        return linkingCode;
    }

    /**
     * Adds a child account to this parent's account.
     *
     * @param child the child account to add
     */
    public void addChild(ChildAccount child) {
        children.add(child);
    }

    /**
     * Removes a child account from this parent's account.
     *
     * <p>The child's parent reference is cleared before removal.</p>
     *
     * @param child the child account to remove
     */
    public void removeChild(ChildAccount child) {
        child.setParent(null);
        children.remove(child);
    }

    /**
     * Creates a new task assigned to a child account.
     *
     * <p>The task is added to both the parent's task list and the child's
     * task list.</p>
     *
     * @param child the child account assigned the task
     * @param name the task name
     * @param description the task description
     * @param month the task due month
     * @param day the task due day
     * @param time the task due time
     * @param points the points awarded when the task is completed
     * @return the newly created task
     */
    public Task createTask(ChildAccount child, String name,
                           String description, int month,
                           int day, int time, int points) {

        Task task = new Task(child, this, name, description,
                month, day, time, points);

        tasks.add(task);
        child.getTasks().add(task);

        return task;
    }

    /**
     * Removes a task created by this parent account.
     *
     * @param task the task to remove
     */
    public void removeTask(Task task) {
        tasks.remove(task);
    }

    /**
     * Reviews a pending task and either approves or rejects it.
     *
     * <p>If approved, the child receives the task's point reward and the task
     * status is changed to COMPLETE. If rejected, the task status returns to
     * INCOMPLETE.</p>
     *
     * @param task the task being reviewed
     * @param approved whether the task should be approved
     */
    public void answerPendingTask(Task task, boolean approved) {

        if (approved) {
            task.getChild().addPoints(task.getPoints());
            task.setStatus(TaskStatus.COMPLETE);
        } else {
            task.setStatus(TaskStatus.INCOMPLETE);
        }
    }

    /**
     * Retrieves all tasks created by this parent account.
     *
     * @return a list of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves all tasks waiting for parent approval.
     *
     * @return a list of pending tasks
     */
    public ArrayList<Task> getPendingTasks() {

        ArrayList<Task> pending = new ArrayList<>();

        for (Task t : tasks) {
            if (t.getStatus() == TaskStatus.PENDING)
                pending.add(t);
        }

        return pending;
    }

    /**
     * Retrieves all incomplete tasks assigned by this parent account.
     *
     * @return a list of incomplete tasks
     */
    public ArrayList<Task> getIncompleteTasks() {

        ArrayList<Task> incomplete = new ArrayList<>();

        for (Task t : tasks) {
            if (t.getStatus() == TaskStatus.INCOMPLETE)
                incomplete.add(t);
        }

        return incomplete;
    }

    /**
     * Retrieves all tasks assigned to a specific child account.
     *
     * @param child the child account whose tasks should be retrieved
     * @return a list of tasks assigned to the child
     */
    public ArrayList<Task> getTasksForChild(ChildAccount child) {

        ArrayList<Task> result = new ArrayList<>();

        for (Task t : tasks) {
            if (t.getChild() == child)
                result.add(t);
        }

        return result;
    }

    /**
     * Retrieves all child accounts linked to this parent account.
     *
     * @return a list of child accounts
     */
    public ArrayList<ChildAccount> getChildren() {
        return children;
    }
}
