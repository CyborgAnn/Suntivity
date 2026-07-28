package edu.utsa.cs3443.suntivity.model;

/**
 * Represents a child's virtual plant in Suntivity.
 * The plant grows as the child completes tasks and can be customized
 * with different colors, faces, and boxes.
 */
public class Plant {

    private ChildAccount child;
    private PlantStatus status;
    private Color color;
    private Face face;
    private Box box;

    /**
     * Creates a plant associated with a child account.
     *
     * @param child child who owns the plant
     */
    public Plant(ChildAccount child) {
        this.child = child;
    }

    public void setChild(ChildAccount child) {
        this.child = child;
    }

    public ChildAccount getChild() {
        return child;
    }

    public void setStatus(PlantStatus status) {
        this.status = status;
    }

    public PlantStatus getStatus() {
        return status;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setFace(Face face) {
        this.face = face;
    }

    public Face getFace() {
        return face;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public Box getBox() {
        return box;
    }
}
