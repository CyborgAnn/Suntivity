package suntivity_model;

public class Color extends Item {

    private String rgbHex;

    public Color(int cost, String name, String rgbHex) {
        super(cost, name);
        this.rgbHex = rgbHex;
    }

    public void setRgbHex(String rgbHex){
        this.rgbHex = rgbHex;
    }

    public String getRgbHex(){
        return rgbHex;
    }
}
