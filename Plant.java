package suntivity_model;

/**
 * Represents a customizable plant associated with a child account in the
 * Suntivity application.
 *
 * <p>A Plant stores its owner, current status, and customizable appearance
 * attributes including color, face, and container type.</p>
 */
public class Plant {

    /** The child account that owns this plant. */
    private ChildAccount child;

    /** The current status or condition of the plant. */
    private PlantStatus status;

    /** The color customization applied to the plant. */
    private Color color;

    /** The face customization applied to the plant. */
    private Face face;

    /** The box/container customization applied to the plant. */
    private Box box;

    /**
     * Creates a new plant associated with a child account.
     *
     * @param child the child account that owns the plant
     */
    public Plant(ChildAccount child) {
        this.child = child;
    }

    /**
     * Updates the child account that owns this plant.
     *
     * @param child the new child account owner
     */
    public void setChild(ChildAccount child) {
        this.child = child;
    }

    /**
     * Retrieves the child account that owns this plant.
     *
     * @return the child account associated with the plant
     */
    public ChildAccount getChild() {
        return child;
    }

    /**
     * Updates the current status of the plant.
     *
     * @param status the new plant status
     */
    public void setStatus(PlantStatus status) {
        this.status = status;
    }

    /**
     * Retrieves the current status of the plant.
     *
     * @return the current plant status
     */
    public PlantStatus getStatus() {
        return status;
    }

    /**
     * Updates the plant's color customization.
     *
     * @param color the new plant color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Retrieves the current plant color customization.
     *
     * @return the plant color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Updates the plant's face customization.
     *
     * @param face the new plant face
     */
    public void setFace(Face face) {
        this.face = face;
    }

    /**
     * Retrieves the current plant face customization.
     *
     * @return the plant face
     */
    public Face getFace() {
        return face;
    }

    /**
     * Updates the plant's container customization.
     *
     * @param box the new plant container
     */
    public void setBox(Box box) {
        this.box = box;
    }

    /**
     * Retrieves the current plant container customization.
     *
     * @return the plant container
     */
    public Box getBox() {
        return box;
    }
}
