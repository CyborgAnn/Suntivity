package suntivity_model;

/**
 * Represents a status or growth state of a plant within the Suntivity
 * application.
 *
 * <p>A PlantStatus contains a display name and an image path that can be used
 * to visually represent the current condition or stage of a plant.</p>
 */
public class PlantStatus {

    /** The name of this plant status. */
    private String name;

    /** The file path or resource location of the status image. */
    private String imagePath;

    /**
     * Creates a new PlantStatus with the specified name and image path.
     *
     * @param name the name of the plant status
     * @param imagePath the path to the status image resource
     */
    public PlantStatus(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
    }

    /**
     * Updates the name of this plant status.
     *
     * @param name the new status name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the name of this plant status.
     *
     * @return the status name
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the image path associated with this plant status.
     *
     * @param imagePath the new image path
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * Retrieves the image path associated with this plant status.
     *
     * @return the image path
     */
    public String getImagePath() {
        return imagePath;
    }
}
