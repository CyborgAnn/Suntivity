package edu.utsa.cs3443.suntivity.model;

import java.io.File;

/**
 * Represents a face customization item for a child's plant.
 * A face item can be purchased and applied to customize the plant appearance.
 */
public class Face extends Item {

    private File image;

    /**
     * Creates a face customization item.
     *
     * @param cost item cost in points
     * @param name item name
     * @param image image representing the face customization
     */
    public Face(int cost, String name, File image) {
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