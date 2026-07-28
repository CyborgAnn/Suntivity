package edu.utsa.cs3443.suntivity.model;

import java.io.File;

/**
 * Represents a plant growth status in Suntivity.
 * Each status contains a name and an associated image.
 */
public class PlantStatus {

    private String name;
    private File image;

    /**
     * Creates a plant status.
     *
     * @param name name of the growth stage
     * @param image image representing the growth stage
     */
    public PlantStatus(String name, File image) {
        this.name = name;
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public File getImage() {
        return image;
    }
}