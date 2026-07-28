package edu.utsa.cs3443.suntivity.model;

/**
 * Represents a color customization item for a child's plant.
 * A color item can be purchased and applied to customize the plant.
 */
public class Color extends Item {

    private int rgbHex;

    /**
     * Creates a color customization item.
     *
     * @param cost cost of the item in points
     * @param name item name
     * @param rgbHex hexadecimal RGB color value
     */
    public Color(int cost, String name, int rgbHex) {
        super(cost, name);
        this.rgbHex = rgbHex;
    }

    public void setRgbHex(int rgbHex) {
        this.rgbHex = rgbHex;
    }

    public int getRgbHex() {
        return rgbHex;
    }
}