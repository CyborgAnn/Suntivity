package edu.utsa.cs3443.suntivity.model;

/**
 * Represents a customizable item that can be purchased
 * and used within Suntivity.
 *
 * Items can include plant customizations such as colors,
 * faces, and boxes.
 */
public abstract class Item {

    private int cost;
    private String name;

    /**
     * Creates an item.
     *
     * @param cost item cost in points
     * @param name item name
     */
    public Item(int cost, String name) {
        this.cost = cost;
        this.name = name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}