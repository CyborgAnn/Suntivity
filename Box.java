package edu.utsa.cs3443.suntivity_model;

import java.io.File;

public class Box extends Item {

    private File image;

    public Box(int cost, String name, File image) {
        super(cost, name);
        this.image = image;
    }

    public void setImage(File image){
        this.image = image;
    }

    public File getImage(){
        return image;
    }
}
