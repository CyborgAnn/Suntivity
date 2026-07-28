package suntivity_model;

/**
 * Represents a box/container customization item that can be equipped on a
 * plant within the Suntivity application.
 *
 * <p>A Box is a type of Item that stores an image path used to determine the
 * visual appearance of a plant's container customization.</p>
 */
public class Box extends Item {

    /** The file path or resource location of the box image. */
    private String imagePath;

    /**
     * Creates a new Box item with the specified cost, name, and image path.
     *
     * @param cost the number of points required to purchase the item
     * @param name the name of the box item
     * @param imagePath the path to the box image resource
     */
    public Box(int cost, String name, String imagePath) {
        super(cost, name);
        this.imagePath = imagePath;
    }

    /**
     * Updates the image path for this box item.
     *
     * @param imagePath the new image path
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * Retrieves the image path associated with this box item.
     *
     * @return the image path
     */
    public String getImagePath() {
        return imagePath;
    }
}
