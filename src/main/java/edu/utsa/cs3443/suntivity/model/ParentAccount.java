package edu.utsa.cs3443.suntivity.model;

import java.util.ArrayList;

/**
 * Represents a parent account in Suntivity.
 * A parent can manage children, create tasks, and approve completed tasks.
 */
public class ParentAccount extends Account {

    private ArrayList<ChildAccount> children;
    private ArrayList<Task> tasks;
    private final int linkingCode;
    private Model model;

    /**
     * Creates a parent account.
     *
     * @param userName parent's username
     * @param password parent's password
     * @param timeZone user's timezone
     * @param model application model containing account data
     */
    public ParentAccount(String userName, String password, int timeZone, Model model) {

        super(userName, password, timeZone);

        children = new ArrayList<>();
        tasks = new ArrayList<>();

        this.model = model;
        linkingCode = generateLinkingCode(model);
    }

    /**
     * Generates a unique five-digit linking code for connecting child accounts.
     *
     * @param model application model used to verify unique codes
     * @return unique linking code
     */
    private int generateLinkingCode(Model model) {

        int code;

        do {
            code = (int) (Math.random() * 90000) + 10000;
        }
        while (model.getParentByLinkingCode(code) != null);

        return code;
    }

    /**
     * Returns the parent's child account linking code.
     *
     * @return linking code
     */
    public int getLinkingCode() {
        return linkingCode;
    }

    /**
     * Adds a child account to this parent.
     *
     * @param child child account to add
     */
    public void addChild(ChildAccount child) {
        children.add(child);
    }

    /**
     * Removes a child account from this parent.
     *
     * @param child child account to remove
     */
    public void removeChild(ChildAccount child) {

        child.setParent(null);
        children.remove(child);
    }

    /**
     * Creates a task assigned to a child.
     *
     * @return newly created task
     */
    public Task createTask(ChildAccount child,
                           String name,
                           String description,
                           int month,
                           int day,
                           int time,
                           int points) {

        Task task = new Task(
                child,
                this,
                name,
                description,
                month,
                day,
                time,
                points
        );

        tasks.add(task);
        child.getTasks().add(task);

        return task;
    }

    /**
     * Removes a task from the parent's task list.
     *
     * @param task task to remove
     */
    public void removeTask(Task task) {
        tasks.remove(task);
    }

    /**
     * Approves or rejects a child's completed task.
     *
     * @param task task being reviewed
     * @param approved whether the task is approved
     */
    public void answerPendingTask(Task task, boolean approved) {

        if (approved) {

            task.getChild().addPoints(task.getPoints());
            task.setStatus(TaskStatus.COMPLETE);

        } else {

            task.setStatus(TaskStatus.INCOMPLETE);
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets tasks waiting for parent approval.
     *
     * @return pending tasks
     */
    public ArrayList<Task> getPendingTasks() {

        ArrayList<Task> pending = new ArrayList<>();

        for (Task task : tasks) {

            if (task.getStatus() == TaskStatus.PENDING) {
                pending.add(task);
            }
        }

        return pending;
    }

    /**
     * Gets incomplete tasks.
     *
     * @return incomplete tasks
     */
    public ArrayList<Task> getIncompleteTasks() {

        ArrayList<Task> incomplete = new ArrayList<>();

        for (Task task : tasks) {

            if (task.getStatus() == TaskStatus.INCOMPLETE) {
                incomplete.add(task);
            }
        }

        return incomplete;
    }

    /**
     * Gets tasks assigned to a specific child.
     *
     * @param child child account
     * @return child's tasks
     */
    public ArrayList<Task> getTasksForChild(ChildAccount child) {

        ArrayList<Task> result = new ArrayList<>();

        for (Task task : tasks) {

            if (task.getChild() == child) {
                result.add(task);
            }
        }

        return result;
    }

    public ArrayList<ChildAccount> getChildren() {
        return children;
    }
}