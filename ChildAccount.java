package suntivity_model;

import java.util.ArrayList;

public class ChildAccount extends Account {

    private ParentAccount parent;
    private ArrayList<Task> tasks;
    private ArrayList<Item> items;
    private int points;
    private Plant plant;

    public ChildAccount(String userName, String password, int timeZone) {
        super(userName, password, timeZone);

        tasks = new ArrayList<>();
        items = new ArrayList<>();
        plant = new Plant(this);
    }

    public ParentAccount linkToParent(int code, Model model) {
        ParentAccount parent = model.getParentByLinkingCode(code);

        if (parent != null) {
            this.parent = parent;
            parent.addChild(this);
        }

        return parent;
    }

    public void completeTask(Task task) {
        task.setStatus(TaskStatus.PENDING);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public ArrayList<Task> getIncompleteTasks() {

        ArrayList<Task> incomplete = new ArrayList<>();

        for(Task t : tasks) {
            if(t.getStatus() == TaskStatus.INCOMPLETE)
                incomplete.add(t);
        }

        return incomplete;
    }

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

    public void editPlantStatus(PlantStatus status) {
        plant.setStatus(status);
    }

    public boolean purchaseItem(Item item) {

        if(points >= item.getCost()) {
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

    public void setParent(ParentAccount parent){
        this.parent = parent;
    }
}
