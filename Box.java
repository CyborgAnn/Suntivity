package suntivity_model;

import java.io.File;

public class Box extends Item {

    private String imagePath;

    public Box(int cost, String name, String imagePath) {
        super(cost, name);
        this.imagePath = imagePath;
    }

    public void setImagePath(String imagePath){
        this.imagePath = imagePath;
    }

    public String getImage(){
        return imagePath;
    }
}
