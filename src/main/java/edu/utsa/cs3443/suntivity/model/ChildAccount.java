package edu.utsa.cs3443.suntivity.model;

import java.util.ArrayList;

/**
 * Represents a child account in Suntivity.
 * A child can complete tasks, earn points, customize their plant,
 * and purchase/equip items.
 */
public class ChildAccount extends Account {

    private ParentAccount parent;
    private ArrayList<Task> tasks;
    private ArrayList<Item> items;
    private int points;
    private Plant plant;

    /**
     * Creates a child account.
     *
     * @param userName child's username
     * @param password child's password
     * @param timeZone user's timezone
     */
    public ChildAccount(String userName, String password, int timeZone) {
        super(userName, password, timeZone);

        tasks = new ArrayList<>();
        items = new ArrayList<>();
        points = 0;
        plant = new Plant(this);
    }

    /**
     * Links a child account to a parent account using a linking code.
     *
     * @param code parent linking code
     * @param model application model containing accounts
     * @return linked parent account or null if not found
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
     * Marks a task as completed.
     *
     * @param task task completed by the child
     */
    public void completeTask(Task task) {
        task.setStatus(TaskStatus.PENDING);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns tasks that are not completed.
     *
     * @return list of incomplete tasks
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
     * Equips a customization item to the child's plant.
     *
     * @param item item being equipped
     */
    public void equipItem(Item item) {

        if (item instanceof Color) {
            plant.setColor((Color) item);

        } else if (item instanceof Face) {
            plant.setFace((Face) item);

        } else if (item instanceof Box) {
            plant.setBox((Box) item);
        }
    }

    public void editPlantStatus(PlantStatus status) {
        plant.setStatus(status);
    }

    /**
     * Purchases an item using earned points.
     *
     * @param item item being purchased
     * @return true if purchase succeeds
     */
    public boolean purchaseItem(Item item) {

        if (points >= item.getCost()) {
            points -= item.getCost();
            items.add(item);
            return true;
        }

        return false;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int amount) {
        points += amount;
    }

    public Plant getPlant() {
        return plant;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setParent(ParentAccount parent) {
        this.parent = parent;
    }

    public ParentAccount getParent() {
        return parent;
    }
}