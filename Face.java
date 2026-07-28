package suntivity_model;

/**
 * Represents a face customization item that can be equipped on a plant.
 *
 * <p>A Face is a type of Item that contains an image path used to determine
 * the visual appearance of a plant's face customization.</p>
 */
public class Face extends Item {

    /** The file path or resource location of the face image. */
    private String imagePath;

    /**
     * Creates a new Face item with the specified cost, name, and image path.
     *
     * @param cost the number of points required to purchase the item
     * @param name the name of the face item
     * @param imagePath the path to the face image resource
     */
    public Face(int cost, String name, String imagePath) {
        super(cost, name);
        this.imagePath = imagePath;
    }

    /**
     * Updates the image path for this face item.
     *
     * @param imagePath the new image path
     */
    public void setImage(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * Retrieves the image path associated with this face item.
     *
     * @return the image path
     */
    public String getImage() {
        return imagePath;
    }
}
