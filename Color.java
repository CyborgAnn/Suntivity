package suntivity_model;

/**
 * Represents a color customization item that can be equipped on a plant within
 * the Suntivity application.
 *
 * <p>A Color is a type of Item that stores a hexadecimal RGB value used to
 * determine the appearance of a plant's color customization.</p>
 */
public class Color extends Item {

    /** The RGB hexadecimal value representing this color. */
    private String rgbHex;

    /**
     * Creates a new Color item with the specified cost, name, and RGB value.
     *
     * @param cost the number of points required to purchase the item
     * @param name the name of the color item
     * @param rgbHex the hexadecimal RGB color value
     */
    public Color(int cost, String name, String rgbHex) {
        super(cost, name);
        this.rgbHex = rgbHex;
    }

    /**
     * Updates the hexadecimal RGB value of this color.
     *
     * @param rgbHex the new hexadecimal RGB value
     */
    public void setRgbHex(String rgbHex) {
        this.rgbHex = rgbHex;
    }

    /**
     * Retrieves the hexadecimal RGB value of this color.
     *
     * @return the RGB hexadecimal color value
     */
    public String getRgbHex() {
        return rgbHex;
    }
}
