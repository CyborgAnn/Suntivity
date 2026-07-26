package edu.utsa.cs3443.suntivity_model;

public class Color extends Item {

    private int rgbHex;

    public Color(int cost, String name, int rgbHex) {
        super(cost, name);
        this.rgbHex = rgbHex;
    }

    public void setRgbHex(int rgbHex){
        this.rgbHex = rgbHex;
    }

    public int getRgbHex(){
        return rgbHex;
    }
}
