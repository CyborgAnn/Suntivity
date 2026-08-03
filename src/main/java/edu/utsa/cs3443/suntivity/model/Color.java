package edu.utsa.cs3443.suntivity.model;

import java.io.File;

/**
 * Represents a color customization item for a child's plant.
 * A color item can be purchased and applied to customize the plant.
 */
public class Color extends Item {

    private File image;
    private int rgbHex;


    /**
     * Creates a color customization item.
     *
     * @param cost item cost
     * @param name item name
     * @param image image representing the color
     * @param rgbHex hexadecimal RGB value
     */
    public Color(int cost, String name, File image, int rgbHex) {

        super(cost, name);

        this.image = image;
        this.rgbHex = rgbHex;

    }


    public File getImage(){

        return image;

    }


    public void setImage(File image){

        this.image = image;

    }


    public int getRgbHex(){

        return rgbHex;

    }


    public void setRgbHex(int rgbHex){

        this.rgbHex = rgbHex;

    }

}