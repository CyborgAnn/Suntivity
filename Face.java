package suntivity_model;

import java.io.File;

public class Face extends Item {

    private String imagePath;

    public Face(int cost, String name, String imagePath) {
        super(cost, name);
        this.imagePath = imagePath;
    }

    public void setImage(String image){
        this.imagePath = imagePath;
    }

    public String getImage(){
        return imagePath;
    }
}
