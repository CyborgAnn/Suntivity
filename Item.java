package suntivity_model;

/**
 * Represents an item that can be purchased and used within the Suntivity
 * application.
 *
 * <p>This abstract class provides common properties shared by all item types,
 * including a name and the number of points required to purchase the item.
 * Specific item types should extend this class and add their own functionality.</p>
 */
public abstract class Item {

    /** The number of points required to purchase this item. */
    private int cost;

    /** The name of the item. */
    private String name;

    /**
     * Creates a new item with the specified cost and name.
     *
     * @param cost the number of points required to purchase the item
     * @param name the item name
     */
    public Item(int cost, String name) {
        this.cost = cost;
        this.name = name;
    }

    /**
     * Updates the purchase cost of this item.
     *
     * @param cost the new item cost
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * Retrieves the number of points required to purchase this item.
     *
     * @return the item cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * Updates the name of this item.
     *
     * @param name the new item name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the name of this item.
     *
     * @return the item name
     */
    public String getName() {
        return name;
    }
}
