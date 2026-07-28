package edu.utsa.cs3443.suntivity.model;

import java.io.File;

/**
 * Represents a box customization item for a child's plant.
 * A box item can be purchased and applied to customize the plant container.
 */
public class Box extends Item {

    private File image;

    /**
     * Creates a box customization item.
     *
     * @param cost item cost in points
     * @param name item name
     * @param image image representing the box customization
     */
    public Box(int cost, String name, File image) {
        super(cost, name);
        this.image = image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public File getImage() {
        return image;
    }
}
