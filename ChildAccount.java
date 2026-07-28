package suntivity_model;

import java.util.ArrayList;

/**
 * Represents a child user's account within the Suntivity application.
 *
 * <p>A ChildAccount extends the base Account class and provides additional
 * functionality for managing tasks, purchased items, points, linking a parent
 * account, and a customizable plant.</p>
 */
public class ChildAccount extends Account {

    /** The parent account linked to this child account. */
    private ParentAccount parent;

    /** The list of tasks assigned to this child. */
    private ArrayList<Task> tasks;

    /** The list of items purchased by this child. */
    private ArrayList<Item> items;

    /** The number of points currently available to the child. */
    private int points;

    /** The plant associated with this child account. */
    private Plant plant;

    /**
     * Creates a new ChildAccount with the specified account information.
     *
     * <p>The account starts with an empty task list, empty inventory,
     * zero points, and a newly created plant.</p>
     *
     * @param userName the username for the account
     * @param password the password for the account
     * @param timeZone the user's time zone
     */
    public ChildAccount(String userName, String password, int timeZone) {
        super(userName, password, timeZone);

        tasks = new ArrayList<>();
        items = new ArrayList<>();
        plant = new Plant(this);
    }

    /**
     * Links this child account to a parent account using a linking code.
     *
     * <p>If a parent account with the provided code exists, the child is added
     * to that parent's list of children.</p>
     *
     * @param code the parent's account linking code
     * @param model the application model used to find the parent account
     * @return the linked parent account, or {@code null} if no matching parent exists
     */
    public ParentAccount linkToParent(int code, Model model) {
        ParentAccount parent = model.getParentByLinkingCode(code);

        if (parent != null) {
            this.parent = parent;
            parent.addChild(this);
        }

        return parent;
    }

    /**
     * Marks a completed task as pending.
     *
     * @param task the task to update
     */
    public void completeTask(Task task) {
        task.setStatus(TaskStatus.PENDING);
    }

    /**
     * Retrieves all tasks assigned to this child.
     *
     * @return a list of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves all tasks that have not been completed.
     *
     * @return a list containing incomplete tasks
     */
    public ArrayList<Task> getIncompleteTasks() {

        ArrayList<Task> incomplete = new ArrayList<>();

        for(Task t : tasks) {
            if(t.getStatus() == TaskStatus.INCOMPLETE)
                incomplete.add(t);
        }

        return incomplete;
    }

    /**
     * Searches for an incomplete task by name.
     *
     * @param name the name of the task to find
     * @return the matching task, or {@code null} if no task is found
     */
    public Task getTask(String name) {
        for (Task task : this.getIncompleteTasks()) {
            if (task.getName().equalsIgnoreCase(name)) {
                return task;
            }
        }
        return null;
    }

    /**
     * Equips an item to customize the child's plant.
     *
     * <p>The item type determines which plant attribute is modified:
     * Color changes the plant color, Face changes the plant face,
     * and Box changes the plant container.</p>
     *
     * @param item the item to equip
     */
    public void equipItem(Item item) {

        if (item instanceof Color) {
            plant.setColor((Color) item);
        }
        else if (item instanceof Face) {
            plant.setFace((Face) item);
        }
        else if (item instanceof Box) {
            plant.setBox((Box) item);
        }
    }

    /**
     * Updates the current status of the child's plant.
     *
     * @param status the new plant status
     */
    public void editPlantStatus(PlantStatus status) {
        plant.setStatus(status);
    }

    /**
     * Attempts to purchase an item using available points.
     *
     * <p>The purchase succeeds only if the child has enough points and
     * does not already own the item.</p>
     *
     * @param item the item to purchase
     * @return {@code true} if the item was purchased, otherwise {@code false}
     */
    public boolean purchaseItem(Item item) {

        if(points >= item.getCost() && !items.contains(item)) {
            points -= item.getCost();
            items.add(item);
            return true;
        }

        return false;
    }

    /**
     * Sets the child's current point balance.
     *
     * @param points the new point value
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Retrieves the child's current points.
     *
     * @return the current point balance
     */
    public int getPoints() {
        return points;
    }

    /**
     * Adds points to the child's balance.
     *
     * @param amount the number of points to add
     */
    public void addPoints(int amount) {
        points += amount;
    }

    /**
     * Retrieves the plant associated with this account.
     *
     * @return the child's plant
     */
    public Plant getPlant() {
        return plant;
    }

    /**
     * Retrieves all items owned by the child.
     *
     * @return a list of purchased items
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Searches for an owned item by name.
     *
     * @param name the name of the item to find
     * @return the matching item, or {@code null} if no item is found
     */
    public Item getItem(String name) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Retrieves the parent account linked to this child account.
     *
     * @return the linked parent account, or {@code null} if no parent is assigned
     */
    public ParentAccount getParent() {
        return parent;
    }

    /**
     * Assigns a parent account to this child account.
     *
     * @param parent the parent account to link
     */
    public void setParent(ParentAccount parent) {
        this.parent = parent;
    }
}
